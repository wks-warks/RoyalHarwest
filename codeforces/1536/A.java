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
      int n = in.nextInt();

      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = in.nextInt();
      }

      List<Integer> goodList = getGoodListFast(arr);
      if (goodList == null) {
        out.println("No");
      } else {
        out.println("Yes");
        out.println(goodList.size());

        for (var num : goodList) {
          out.print(num + " ");
        }
        out.println();
      }
    }
    in.close();
    out.close();
  }

  static List<Integer> getGoodListFast(int[] arr) {
    for (var num : arr) {
      if (num < 0) {
        return null;
      }
    }
    
    List<Integer> goodList = new ArrayList<Integer>(101);
    for (int i = 0; i <= 100; i++) {
      goodList.add(i);
    }

    return goodList;
  }

  static List<Integer> getAltGoodList(int[] arr) {
    elements = new HashSet<Integer>();
    List<Integer> list = new ArrayList<Integer>();
    for (var num : arr) {
      if (num < 0) {
        return null;
      }

      list.add(num);
      elements.add(num);
    }

    int max = arr[0];
    int min = arr[0];
    int g = arr[0];
    for (var num : arr) {
      g = gcd(g, num);
      min = Math.min(min, num);
      max = Math.max(max, num);
    }

    for (int val = g; val < max; val += g) {
      if (!elements.contains(val)) {
        list.add(val);
      }
    }
    return list;
  }

  static int gcd(int num1, int num2) {
    if (num1 == 0) {
      return num2;
    }
    return gcd(num2 % num1, num1);
  }


  static Set<Integer> elements;
  static List<Integer> getGoodList(int[] arr) {
    elements = new HashSet<Integer>();
    List<Integer> list = new ArrayList<Integer>();
    for (var num : arr) {
      list.add(num);
      elements.add(num);
    }

    return getGoodList(0, list);
  }

  static List<Integer> getGoodList(int pos, List<Integer> list) {
    if (pos > 300) {
      return null;
    }
    if (pos == list.size()) {
      return list;
    }

    for (int i = 0; i < pos; i++) {
      int diff = Math.abs(list.get(pos) - list.get(i));
      if (!elements.contains(diff)) {
        elements.add(diff);
        list.add(diff);
      }
    }

    return getGoodList(pos+1, list);
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