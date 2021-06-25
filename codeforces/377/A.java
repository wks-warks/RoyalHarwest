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
    int height = in.nextInt();
    int width = in.nextInt();
    int wallsToAdd = in.nextInt();
    Maze maze = new Maze(height, width);
    for (int h = 0; h < height; h++) {
      char[] row = in.next().toCharArray();
      maze.add(row);
    }

    // int height = 500;
    // int width = 500;
    // int wallsToAdd = 0;
    // Maze maze = new Maze(height, width);
    // for (int h = 0; h < height; h++) {
    //   char[] row = new char[width];
    //   for (int w = 0; w < width; w++) {
    //     row[w] = '.';
    //   }
    //   maze.add(row);
    // }

    maze.transform(wallsToAdd);
    maze.print();

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

class Maze {
  int height, width, rowNum;
  char[][] grid;

  public Maze(int height, int width) {
    this.height = height;
    this.width = width;
    rowNum = 0;
    grid = new char[height][];
  }

  public void add(char[] row) {
    grid[rowNum++] = row;
  }

  public void transform(int wallsToAdd) {

    int freeCells = 0;
    Cell start = null;
    Cell[][] cellGrid = new Cell[height][width];    
    for (int h = 0; h < height; h++) {
      for (int w = 0; w < width; w++) {
        cellGrid[h][w] = new Cell(h, w);
        if (grid[h][w] == '#') {
          continue;
        }
        freeCells++;
        if (start == null) {
          start = cellGrid[h][w];
        }
        int outgoing = 0;
        if (h > 0 && grid[h-1][w] == '.') {
          outgoing++;
        }
        if (h < height-1 && grid[h+1][w] == '.') {
          outgoing++;
        }
        if (w > 0 && grid[h][w-1] == '.') {
          outgoing++;
        }
        if (w < width-1 && grid[h][w+1] == '.') {
          outgoing++;
        }

        cellGrid[h][w].outgoing = outgoing;
      }
    }

    SortedSet<Cell> inQueue = new TreeSet<Cell>((a, b) -> (a.outgoing - b.outgoing)*1_000_000 + (a.h - b.h)*1_000 + (a.w-b.w)); // Was not functioning with only a.outgoing - b.outgoing (khud socho, usko kaise pata hota aage kidhar jaana hai tree mein)
    Queue<Cell> cellQueue = new LinkedList<Cell>();
    cellQueue.add(start);
    SortedSet<Cell> inCellQueue = new TreeSet<Cell>((a, b) -> (a.outgoing - b.outgoing)*1_000_000 + (a.h - b.h)*1_000 + (a.w-b.w));

    while (inQueue.size() < freeCells-wallsToAdd) {
      Cell top = cellQueue.poll();
      inQueue.add(top);
      int h = top.h;
      int w = top.w;
      
      if (h > 0 && grid[h-1][w] == '.' && !inCellQueue.contains(cellGrid[h-1][w])) {
        cellQueue.add(cellGrid[h-1][w]);
        inCellQueue.add(cellGrid[h-1][w]);
      }
      if (h < height-1 && grid[h+1][w] == '.' && !inCellQueue.contains(cellGrid[h+1][w])) {
        cellQueue.add(cellGrid[h+1][w]);
        inCellQueue.add(cellGrid[h+1][w]);
      }
      if (w > 0 && grid[h][w-1] == '.' && !inCellQueue.contains(cellGrid[h][w-1])) {
        cellQueue.add(cellGrid[h][w-1]);
        inCellQueue.add(cellGrid[h][w-1]);
      }
      if (w < width-1 && grid[h][w+1] == '.' && !inCellQueue.contains(cellGrid[h][w+1])) {
        cellQueue.add(cellGrid[h][w+1]);
        inCellQueue.add(cellGrid[h][w+1]);
      }
    }

    for (int h = 0; h < height; h++) {
      for (int w = 0; w < width; w++) {
        if (grid[h][w] == '.' && !inQueue.contains(cellGrid[h][w])) {
          grid[h][w] = 'X';
        }
      }
    }
  }

  public void print() {
    for (var row : grid) {
      Main.out.println(new String(row));
    }
  }
}

class Cell {
  int h, w, outgoing;
  public Cell(int h, int w) {
    this(h, w, 0);
  }
  public Cell(int h, int w, int outgoing) {
    this.h = h;
    this.w = w;
    this.outgoing = outgoing;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o instanceof Cell) {
      return ((Cell)o).h == this.h && ((Cell)o).w == this.w;
    }
    return false;
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