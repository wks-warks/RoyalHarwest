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
    int cities = in.nextInt();
    int[][] dist = new int[cities][cities];
    for (int c1 = 0; c1 < cities; c1++) {
      for (int c2 = 0; c2 < cities; c2++) {
        dist[c1][c2] = in.nextInt();
      }
    }

    long roadLength = getRoadLength(cities, dist);
    out.println(roadLength);
    
    in.close();
    out.close();
  }

  static int limit;
  private static long getRoadLength(int cities, int[][] dist) {
    limit = cities;
    Integer[][] distances = new Integer[cities*cities][3];
    for (int c = 0; c < cities; c++) {
      for (int c1 = 0; c1 < cities; c1++) {
        int idx = c*cities+c1;
        distances[idx][0] = c;
        distances[idx][1] = c1;
        distances[idx][2] = dist[c][c1];
      }
    }
    Arrays.sort(distances, (a, b) -> a[2] - b[2]);

    long roadLength = 0;
    BitSet resolved = new BitSet(cities*cities);
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < distances.length; i++) {
      int computedDist = Integer.MAX_VALUE;
      int first = distances[i][0];
      int second = distances[i][1];

      for (int intermediate = 0; intermediate < cities; intermediate++) {
        min = Math.min(first, intermediate);
        max = first + intermediate - min;
        if (!resolved.get(getBitMask(min, max))) {
          continue;
        }
        min = Math.min(intermediate, second);
        max = intermediate + second - min;
        if (!resolved.get(getBitMask(min, max))) {
          continue;
        }
        computedDist = Math.min(computedDist, dist[first][intermediate] + dist[intermediate][second]);
      }

      if (computedDist < dist[first][second]) {
        return -1L;
      } else if (computedDist != dist[first][second]) {
        roadLength += dist[first][second];
      }
      min = Math.min(first, second);
      max = first + second - min;
      resolved.set(getBitMask(min, max));
    }
    return roadLength;
  }

  private static int getBitMask(int min, int max) {
    return min * limit + max;
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