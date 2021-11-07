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

  public static final int MOD = 998_244_353;
  public void run() {
    int vertices = in.nextInt();
    int edges = in.nextInt();
    if (vertices == edges) {
      int[] edgeCount = new int[vertices];
      Point[] edgeData = new Point[edges];
      for (int e = 0; e < edges; e++) {
        edgeData[e] = new Point(in.nextInt()-1, in.nextInt()-1);
        edgeCount[edgeData[e].x]++;
        edgeCount[edgeData[e].y]++;
      }

      int min = Integer.MAX_VALUE;
      for (var count : edgeCount) {
        min = Math.min(min, count);
      }
      
      if (min == 0) {
        out.println(0);
      } else {
        int ways = getWays(vertices, edges, edgeCount, edgeData);
        out.println(ways);
      }
    } else {
      out.println(0);
    }
    
    in.close();
    out.close();
  }

  private static int getWays(int vertices, int edges, int[] edgeCount, Point[] edgeData) {
    PriorityQueue<Point> pq = new PriorityQueue<Point>((a, b) -> a.y - b.y);
    for (int v = 0; v < vertices; v++) {
      pq.add(new Point(v, edgeCount[v]));
    }

    List<Set<Integer>> connections = new ArrayList<Set<Integer>>();
    for (int v = 0; v < vertices; v++) {
      connections.add(new HashSet<Integer>());
    }

    for (var edgeInfo : edgeData) {
      connections.get(edgeInfo.x).add(edgeInfo.y);
      connections.get(edgeInfo.y).add(edgeInfo.x);
    }

    BitSet visited = new BitSet(vertices);
    int visitedCount = 0;
    while (!pq.isEmpty()) {
      Point top = pq.poll();
      int vertex = top.x;
      if (visited.get(vertex)) {
        continue;
      }
      if (top.y == 0) {
        return 0;
      }
      if (top.y == 2) {
        break;
      }
      visited.set(vertex);
      visitedCount++;

      for (var nbr : connections.get(vertex)) {
        if (!visited.get(nbr)) {
          edgeCount[nbr]--;
          edgeCount[vertex]--;
          connections.get(nbr).remove(vertex);
          connections.get(vertex).remove(nbr);
          pq.add(new Point(nbr, edgeCount[nbr]));
        }
      }
    }

    int remainingSets = 0;
    for (int v = 0; v < vertices; v++) {
      if (!visited.get(v)) {
        dfs(v, connections, visited);
        remainingSets++;
      }
    }
    int ans = 1;
    while (remainingSets --> 0) {
      ans <<= 1;
      if (ans > MOD) {
        ans -= MOD;
      }
    }
    return ans;
  }

  private static void dfs(int from, List<Set<Integer>> connections, BitSet visited) {
    visited.set(from);
    for (var nbr : connections.get(from)) {
      if (!visited.get(nbr)) {
        dfs(nbr, connections, visited);
      }
    }
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