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
    int n = in.nextInt();
    int p1 = in.nextInt();
    int p2 = in.nextInt();
    int p3 = in.nextInt();
    int t1 = in.nextInt();
    int t2 = in.nextInt();

    int[][] work = new int[n][2];
    for (int i = 0; i < n; i++) {
      work[i][0] = in.nextInt();
      work[i][1] = in.nextInt();
    }
    
    int consumption = findConsumption(p1, p2, p3, t1, t2, work);
    out.println(consumption);

    in.close();
    out.close();
  }

  static int findConsumption(int p1, int p2, int p3, int t1, int t2, int[][] work) {
    int consumption = 0;
    for (int i = 0; i < work.length; i++) {
      consumption += (work[i][1] - work[i][0]) * p1;
      if (i + 1 != work.length) {
        consumption += findConsumption(p1, p2, p3, t1, t2, work[i+1][0] - work[i][1]);
      }
    }
    return consumption;
  }

  static int findConsumption(int p1, int p2, int p3, int t1, int t2, int interval) {
    int first = Math.min(t1, interval);
    interval -= first;

    int second = Math.min(t2, interval);
    interval -= second;

    int third = interval;
    return p1 * first + p2 * second + p3 * third;
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
