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
    char[][] grid = new char[h][];
    for (int i = 0; i < h; i++) {
      grid[i] = in.next().toCharArray();
    }

    int score = getScore(grid);
    out.println(score);
    
    in.close();
    out.close();
  }

  private static int getScore(char[][] grid) {
    int whites = 0;
    for (var row : grid) {
      for (var ch : row) {
        if (ch == '.') {
          whites++;
        }
      }
    }


    int[][] dist = new int[grid.length][grid[0].length];
    for (int i = 0; i < dist.length; i++) {
      for (int j = 0; j < dist[i].length; j++) {
        dist[i][j] = Integer.MAX_VALUE;
      }
    }

    PriorityQueue<Integer[]> pq = new PriorityQueue<Integer[]>((a, b) -> a[2] - b[2]);
    pq.add(new Integer[]{0, 0, 1});
    while (!pq.isEmpty()) {
      Integer[] top = pq.poll();
      dist[top[0]][top[1]] = top[2];
      int[][] nbrs = new int[][] {
        {top[0]-1, top[1]},
        {top[0]+1, top[1]},
        {top[0], top[1]-1},
        {top[0], top[1]+1}
      };

      for (var nbr : nbrs) {
        if (nbr[0] < 0 || nbr[0] == grid.length || nbr[1] < 0 || nbr[1] == grid[0].length || grid[nbr[0]][nbr[1]] == '#') {
          continue;
        }
        int prev = dist[nbr[0]][nbr[1]];
        int curr = dist[top[0]][top[1]]+1;
        if (curr < prev) {
          dist[nbr[0]][nbr[1]] = curr;
          pq.add(new Integer[]{nbr[0], nbr[1], curr});
        }
      }
    }

    int min = dist[dist.length-1][dist[0].length-1];
    return min == Integer.MAX_VALUE ? -1 : whites - min; 
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
