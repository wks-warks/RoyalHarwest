// Author : RegalBeast
// Jay Tanay Baba Ki

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
    int end = n-1;
    int[] c = new int[end];
    int[] s = new int[end];
    int[] f = new int[end];
    
    for (int i = 0; i < end; i++) {
      c[i] = in.nextInt();
      s[i] = in.nextInt();
      f[i] = in.nextInt();
    }

    int[] timeRequired = getTimeRequired(n, c, s, f);
    for (var tr : timeRequired) {
      out.println(tr);
    }
    
    in.close();
    out.close();
  }

  private static int[] getTimeRequired(int n, int[] c, int[] s, int[] f) {
    int[] timeRequired = new int[n];
    for (int i = 0; i < n; i++) {
      timeRequired[i] = getTimeRequired(i, 0, c, s, f);
    }
    return timeRequired;
  }

  private static int getTimeRequired(int i, int t, int[] c, int[] s, int[] f) {
    if (i == c.length) {
      return t;
    }
    if (t <= s[i]) {
      return getTimeRequired(i+1, s[i]+c[i], c, s, f);
    }
    int additional = t - s[i];
    int mul = lig(additional, f[i]);
    return getTimeRequired(i+1, s[i]+mul*f[i]+c[i], c, s, f);
  }

  private static int lig(int num, int den) {
    return (num + den - 1) / den;
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
