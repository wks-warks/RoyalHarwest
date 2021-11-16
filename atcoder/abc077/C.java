// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main implements Runnable {
  static Input in = new Input();
  static PrintWriter out = Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), String.join(
                                  "All that is gold does not glitter,",
                                  "Not all those who wander are lost;",
                                  "The old that is strong does not wither,",
                                  "Deep roots are not reached by the frost.",
                                  
                                  "From the ashes a fire shall be woken,",
                                  "A light from the shadows shall spring;",
                                  "Renewed shall be blade that was broken,",
                                  "The crownless again shall be king."
                                ), 1<<28).start();
  }

  public void run() {
    int n = in.nextInt();
    int[] a = new int[n];
    int[] b = new int[n];
    int[] c = new int[n];
    // reversed nomenclature

    for (int i = 0; i < n; i++) {
      c[i] = in.nextInt();
    }
    for (int i = 0; i < n; i++) {
      b[i] = in.nextInt();
    }
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }
    Sorter.reverseSort(a);
    Sorter.reverseSort(b);

    TreeMap<Integer, Integer> upperSuffix = new TreeMap<Integer, Integer>();
    upperSuffix.put(Integer.MAX_VALUE, 0);
    for (int i = 0; i < n; i++) {
      int ceil = upperSuffix.ceilingKey(a[i]);
      upperSuffix.put(a[i], upperSuffix.get(ceil) + 1);
    }

    TreeMap<Integer, Long> middleSSuffix = new TreeMap<Integer, Long>();
    middleSSuffix.put(Integer.MAX_VALUE, 0L);
    for (int i = 0; i < n; i++) {
      int ceil = middleSSuffix.ceilingKey(b[i]);
      int higher = upperSuffix.higherKey(b[i]);
      middleSSuffix.put(b[i], middleSSuffix.get(ceil) + upperSuffix.get(higher));
    }

    long altars = 0L;
    for (int i = 0; i < n; i++) {
      int higher = middleSSuffix.higherKey(c[i]);
      altars += middleSSuffix.get(higher);
    }
    out.println(altars);
    
    in.close();
    out.close();
  }
  
  static PrintWriter Output() {
    return new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  }
  
  static PrintWriter Output(String fileName) {
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return pw;
  }
}

class Input {
  BufferedReader br;
  StringTokenizer st;
  public Input() {
    br = new BufferedReader(new InputStreamReader(System.in));
  }

  public Input(String fileName) {
    try {
      br = new BufferedReader(new FileReader(fileName));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public String next() {
    while (st == null || !st.hasMoreElements()) {
      try {
        st = new StringTokenizer(br.readLine());
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return st.nextToken();
  }

  public int nextInt() {
    return Integer.parseInt(next());
  }

  public long nextLong() {
    return Long.parseLong(next());
  }

  public Float nextFloat() {
    return Float.parseFloat(next());
  }

  public Double nextDouble() {
    return Double.parseDouble(next());
  }

  public String nextLine() {
    if (st != null && st.hasMoreElements()) {
      StringBuilder sb = new StringBuilder();
      while (st.hasMoreElements()) {
        sb.append(next());
      }
      return sb.toString();
    }

    String str = null;
    try {
      str = br.readLine();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return str;
  }

  public void close() {
    try {
      br.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}


class Sorter {
  static void sort(int[] arr) {
    ThreadLocalRandom rand = ThreadLocalRandom.current();
 
    for (int i = 0; i < arr.length-1; i++) {
      int newPos = rand.nextInt(i, arr.length-1);
      int temp = arr[i];
      arr[i] = arr[newPos];
      arr[newPos] = temp;
    }
 
    Arrays.sort(arr);
  }

  static void reverseSort(int[] arr) {
    sort(arr);
    int mid = arr.length >> 1;

    for (int left = 0, right = arr.length-1; left < mid; left++, right--) {
      int temp = arr[left];
      arr[left] = arr[right];
      arr[right] = temp;
    }
  }
}