// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.Point;

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
    int n = in.nextInt();
    /*
      Algorithm:
      Keeping other values constant, change last value that is yet to be found
      and query perm while changing value over that idx.

      after finding last element
      find N, N-1, ... till Last element
      then find
      1, 2, ... till last element-1
    */
    int[] perm = new int[n];
    int lastElement = getLastElement(n);
    perm[n-1] = lastElement;

    for (int element = 1; element <= n; element++) {
      if (element == lastElement) {
        continue;
      }
      out.print("? ");
      for (int i = 1; i < n; i++) {
        out.print(lastElement + " ");
      }
      out.println(element);
      out.flush();

      perm[in.nextInt()-1] = element;
    }
    out.print("! ");
    for (var num : perm) {
      out.print(num + " ");
    }
    out.flush();

    in.close();
    out.close();
  }

  static int getLastElement(int n) {
    for (int i = 1; i < n; i++) {
      int response = lastQuery(i, n, n);
      if (response > 0) {
        return i;
      }
    }
    return n;
  }

  static int lastQuery(int all, int n, int last) {
    out.print("? ");
    for (int i = 1; i < n; i++) {
      out.print(all + " ");
    }
    out.println(last);
    out.flush();

    int response = in.nextInt();
    return response;
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
