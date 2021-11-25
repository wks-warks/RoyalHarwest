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
    int d = in.nextInt();
    int[][] a = new int[h][w];
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        a[i][j] = in.nextInt();
      }
    }

    int[] scores = computeScores(a, d);

    int q = in.nextInt();
    while (q-->0) {
      int left = in.nextInt();
      int right = in.nextInt();
      int score = getScore(scores, left, right);
      out.println(score);
    }
    
    in.close();
    out.close();
  }

  private static int[] computeScores(int[][] a, int d) {
    int[][] pos = new int[a.length*a[0].length][2];
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[i].length; j++) {
        int val = a[i][j]-1;
        pos[val][0] = i;
        pos[val][1] = j;
      }
    }

    int[] scores = new int[pos.length];
    for (int i = scores.length-1; i >= 0; i--) {
      if (i+d < scores.length) {
        scores[i] = scores[i+d];
        scores[i] += Math.abs(pos[i][0] - pos[i+d][0]) + Math.abs(pos[i][1] - pos[i+d][1]);
      }
    }
    return scores;
  }

  private static int getScore(int[] scores, int left, int right) {
    int leftScore = getScore(scores, left);
    int rightScore = getScore(scores, right);
    return leftScore - rightScore;
  }

  private static int getScore(int[] scores, int idx) {
    return scores[idx-1];
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
