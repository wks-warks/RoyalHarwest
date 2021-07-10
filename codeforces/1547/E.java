// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.*;

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
      int k = in.nextInt();
      int[][] ac = new int[k][2];
      for (int i = 0; i < k; i++) {
        ac[i][0] = in.nextInt()-1;
      }
      for (int i = 0; i < k; i++) {
        ac[i][1] = in.nextInt();
      }

      int[] temperatures = getTemperatures(n, ac);
      for (var temp : temperatures) {
        out.print(temp + " ");
      }
      out.println();
    }

    in.close();
    out.close();
  }

  static int[] getTemperatures(int n, int[][] ac) {
    int[] temperatures = new int[n];
    for (int i = 0; i < n; i++) {
      temperatures[i] = Integer.MAX_VALUE;
    }
    for (var unit : ac) {
      temperatures[unit[0]] = unit[1];
    }

    boolean[] settled = new boolean[n];
    PriorityQueue<Point> pq = new PriorityQueue<Point>(n, (a, b) -> a.y - b.y);
    for (var unit : ac) {
      pq.add(new Point(unit[0], unit[1]));
    }

    while (pq.size() > 0) {
      Point topPoint = pq.poll();
      int top = topPoint.x;
      if (settled[top]) {
        continue;
      }
      settled[top] = true;
      int topTemp = temperatures[top];

      if (top > 0 && temperatures[top-1] > topTemp+1) {
        temperatures[top-1] = topTemp+1;
        pq.add(new Point(top-1, topTemp+1));
      }
      if (top < n-1 && temperatures[top+1] > topTemp+1) {
        temperatures[top+1] = topTemp+1;
        pq.add(new Point(top+1, topTemp+1));
      }
    }
    return temperatures;
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