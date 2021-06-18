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
                                ), 1<<25).start();
  }

  public void run() {
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      int mountains = in.nextInt();
      int[] heights = new int[mountains];
      for (int m = 0; m < mountains; m++) {
        heights[m] = in.nextInt();
      }

      int[] order = getOrder(heights);
      for (var height : order) {
        out.print(height + " ");
      }
      out.println();
    }

    in.close();
    out.close();
  }

  static int[] getOrder(int[] heights) {
    Sorter.sort(heights);
    int minIdx = 0;
    for (int i = 1; i < heights.length-1; i++) {
      int candidateDiff = heights[i+1] - heights[i];
      int prevDiff = heights[minIdx+1] - heights[minIdx];

      if (candidateDiff < prevDiff) {
        minIdx = i;
      }
    }

    int[] order = new int[heights.length];
    order[order.length-1] = heights[minIdx+1];
    order[0] = heights[minIdx];
    for (int i = 0; i < minIdx; i++) {
      order[order.length-1-minIdx+i] = heights[i];
    }
    for (int i = minIdx+2; i < heights.length; i++) {
      order[i-minIdx-1] = heights[i];
    }
    return order;
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
    if (st.hasMoreElements()) {
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