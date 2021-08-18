// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.Point;

public class Main implements Runnable {
  static Input in = new Input();
  static PrintWriter out = Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), "King", 1<<25).start();
  }

  static int h, w;
  static char[][] grid;
  public void run() {
    h = in.nextInt();
    w = in.nextInt();
    grid = new char[h][];
    for (int i = 0; i < h; i++) {
      grid[i] = in.next().toCharArray();
    }

    int ans = getAns();

    out.println(ans);

    in.close();
    out.close();
  }

  static PriorityQueue<Cell> pq;
  static int[][] dist;
  static int getAns() {
    dist = new int[h][w];
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        dist[i][j] = Integer.MAX_VALUE;
      }
    }

    pq = new PriorityQueue<Cell>(h*w, new Cell());
    pq.add(new Cell(new Point(0, 0), 0));

    while (pq.size() > 0) {
      Cell top = pq.poll();
      int x = top.pos.x;
      int y = top.pos.y;

      boolean thisWall = grid[x][y] == '#';
      boolean punchedPrev = (top.distance & 1) == 1;
      boolean punch = !punchedPrev && thisWall;
      int here = top.distance;
      if (punchedPrev || punch) {
        here++;
      }

      if (dist[x][y] <= here) {
        continue;
      }
      dist[x][y] = here;

      int[][] neighbours = new int[][]{
        {x-1, y},
        {x+1, y},
        {x, y-1},
        {x, y+1}
      };

      for (var neighbour : neighbours) {
        if (neighbour[0] < 0 || neighbour[1] < 0 || neighbour[0] == h || neighbour[1] == w) {
          continue;
        }

        if (here < dist[neighbour[0]][neighbour[1]]) {
          pq.add(new Cell(new Point(neighbour[0], neighbour[1]), here));
        }
      }

      if (punch) {
        int[][] diagonalNeighbours = new int[][] {
          {x-1, y-1},
          {x-1, y+1},
          {x+1, y-1},
          {x+1, y+1}
        };

        for (var neighbour : diagonalNeighbours) {
          if (neighbour[0] < 0 || neighbour[1] < 0 || neighbour[0] == h || neighbour[1] == w) {
            continue;
          }
          if (here < dist[neighbour[0]][neighbour[1]]) {
            pq.add(new Cell(new Point(neighbour[0], neighbour[1]), here));
          }
        }
      }
    }

    return (dist[h-1][w-1] + 1) >> 1;
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

class Cell implements Comparator<Cell> {
  Point pos;
  int distance;

  public Cell() {

  }
  public Cell(Point pos, int distance) {
    this.pos = pos;
    this.distance = distance;
  }

  @Override
  public int compare(Cell first, Cell second) {
    return first.distance - second.distance;
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
