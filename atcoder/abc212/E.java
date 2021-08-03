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
    int cities, unusableCount, tripDuration;
    cities = in.nextInt();
    unusableCount = in.nextInt();
    tripDuration = in.nextInt();

    Graph graph = new Graph(cities, unusableCount, tripDuration);
    for (int i = 0; i < unusableCount; i++) {
      graph.addUnusable(in.nextInt()-1, in.nextInt()-1);
    }

    out.println(graph.fetchAnswer());
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

class Graph {
  static int mod = 998_244_353;
  private int cities, unusableCount, urPtr, tripDuration;
  private int[][] unusableRoads, dp;
  
  public Graph(int cities, int unusableCount, int tripDuration) {
    this.cities = cities;
    this.unusableCount = unusableCount;
    this.tripDuration = tripDuration;

    dp = new int[tripDuration+1][cities];    
    unusableRoads = new int[unusableCount][2];
    urPtr = 0;
  }

  public void addUnusable(int first, int second) {
    unusableRoads[urPtr][0] = first;
    unusableRoads[urPtr][1] = second;
    urPtr++;
  }

  public int fetchAnswer() {
    dp[0][0] = 1;
    int total = 1;

    for (int i = 0; i < dp.length-1; i++) {
      int newTotal = 0;
      for (int j = 0; j < cities; j++) {
        dp[i+1][j] = (int) (((long) total - dp[i][j] + mod) % mod);
        newTotal = (newTotal + dp[i+1][j]) % mod;
      }

      for (var road : unusableRoads) {
        int first = road[0];
        int second = road[1];
        dp[i+1][first] = (int) (((long) dp[i+1][first] - dp[i][second] + mod) % mod);
        dp[i+1][second] = (int) (((long) dp[i+1][second] - dp[i][first] + mod) % mod);

        newTotal = (int) (((long) newTotal + mod + mod - dp[i][first] - dp[i][second]) % mod);
      }

      total = newTotal;
    }

    return dp[dp.length-1][0];
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
