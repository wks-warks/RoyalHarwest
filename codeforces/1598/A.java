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
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      int rows = 2;
      int cols = in.nextInt();
      
      String[] grid = new String[rows];
      for (int r = 0; r < rows; r++) {
        grid[r] = in.next();
      }

      boolean completable = isCompletable(grid);
      out.println(completable ? "YES" : "NO");
    }
    
    in.close();
    out.close();
  }

  static boolean isCompletable(String[] grid) {
    boolean[][] reachable = new boolean[grid.length][grid[0].length()];
    reachable[0][0] = true;

    Queue<Point> queue = new LinkedList<Point>();
    queue.add(new Point(0, 0));

    while (!queue.isEmpty()) {
      Point curr = queue.poll();
      Point[] neighbours = new Point[] {
        new Point(curr.x-1, curr.y-1),
        new Point(curr.x-1, curr.y),
        new Point(curr.x-1, curr.y+1),
        new Point(curr.x, curr.y-1),
        new Point(curr.x, curr.y+1),
        new Point(curr.x+1, curr.y-1),
        new Point(curr.x+1, curr.y),
        new Point(curr.x+1, curr.y+1)
      };

      for (var nbr : neighbours) {
        int x = nbr.x;
        int y = nbr.y;
        if (x < 0 || y < 0 || x == grid.length || y == grid[0].length()) {
          continue;
        }

        if (grid[x].charAt(y) == '1' || reachable[x][y]) {
          continue;
        }

        reachable[x][y] = true;
        queue.add(nbr);
      }
    }

    return reachable[reachable.length-1][reachable[0].length-1];
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
