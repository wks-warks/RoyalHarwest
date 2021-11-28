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
                                ), 1<<28).start();
  }

  public void run() {
    int h = in.nextInt();
    int w = in.nextInt();
    int c = in.nextInt();
    int q = in.nextInt();
    int[][] queries = new int[q][3]; // reversed in order
    while (--q >= 0) {
      queries[q][0] = in.nextInt();
      queries[q][1] = in.nextInt();
      queries[q][2] = in.nextInt()-1;
    }

    long[] count = getCount(queries, c, h, w);
    for (var num : count) {
      out.print(num + " ");
    }
    
    in.close();
    out.close();
  }

  private static long[] getCount(int[][] queries, int c, int h, int w) {
    long[] count = new long[c];
    Set<Integer> rowsPainted = new HashSet<Integer>();
    Set<Integer> colsPainted = new HashSet<Integer>();
    for (var query : queries) {
      int type = query[0];
      int index = query[1];
      int color = query[2];
      if (type == 1) { // row
        if (!rowsPainted.contains(index)) {
          rowsPainted.add(index);
          count[color] += w - colsPainted.size();
        }
      } else { // col
        if (!colsPainted.contains(index)) {
          colsPainted.add(index);
          count[color] += h - rowsPainted.size();
        }
      }

      // for (var num : count) {
      //   out.print(num + " ");
      // }
      // out.println();
    }
    return count;
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
