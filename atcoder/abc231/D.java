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
    int n = in.nextInt();
    int m = in.nextInt();
    
    int[] neighbourCount = new int[n];
    List<List<Integer>> neighbours = new ArrayList<List<Integer>>(n);
    for (int i = 0; i < n; i++) {
      neighbours.add(new ArrayList<Integer>());
    }

    for (int i = 0; i < m; i++) {
      int a = in.nextInt()-1;
      int b = in.nextInt()-1;

      neighbours.get(a).add(b);
      neighbourCount[a]++;
      if (neighbourCount[a] == 3) {
        out.println("No");
        out.close();
        return;
      }

      neighbours.get(b).add(a);
      neighbourCount[b]++;
      if (neighbourCount[b] == 3) {
        out.println("No");
        out.close();
        return;
      }
    }

    boolean possible = isPossible(n, m, neighbours);
    out.println(possible ? "Yes" : "No");
    
    in.close();
    out.close();
  }

  private boolean isPossible(int n, int m, List<List<Integer>> neighbours) {
    if (m >= n) {
      return false;
    }

    BitSet visited = new BitSet(n);
    for (int i = 0; i < n; i++) {
      if (!visited.get(i)) {
        if (!dfs(neighbours, i, visited, i)) {
          return false;
        }
      }
    }

    return true;
  }

  private static boolean dfs(List<List<Integer>> neighbours, int idx, BitSet visited, int par) {
    visited.set(idx);
    for (var nbr : neighbours.get(idx)) {
      if (nbr != par) {
        if (visited.get(nbr)) {
          return false;
        }
        dfs(neighbours, nbr, visited, idx);
      }
    }
    return true;
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
