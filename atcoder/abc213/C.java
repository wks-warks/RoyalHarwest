// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main implements Runnable {
  static Input in = new Input();
  static PrintWriter out = Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), "King", 1<<25).start();
  }

  public void run() {
    int rows = in.nextInt();
    int cols = in.nextInt();
    int cards = in.nextInt();
    
    int[][] values = new int[cards][2];
    for (int c = 0; c < cards; c++) {
      values[c][0] = in.nextInt();
      values[c][1] = in.nextInt();
    }

    int[][] pos = getPos(values);
    for (var cardPos : pos) {
      out.println(String.format("%d %d", cardPos[0], cardPos[1]));
    }

    in.close();
    out.close();
  }

  static int[][] getPos(int[][] values) {
    Set<Integer> rows = new TreeSet<Integer>();
    Set<Integer> cols = new TreeSet<Integer>();
    for (var pos : values) {
      rows.add(pos[0]);
      cols.add(pos[1]);
    }

    Map<Integer, Integer> newRows = new HashMap<Integer, Integer>();
    int newRow = 1;
    for (var row : rows) {
      newRows.put(row, newRow++);
    }

    Map<Integer, Integer> newCols = new HashMap<Integer, Integer>();
    int newCol = 1;
    for (var col : cols) {
      newCols.put(col, newCol++);
    }

    int[][] pos = new int[values.length][2];
    for (int i = 0; i < values.length; i++) {
      pos[i][0] = newRows.get(values[i][0]);
      pos[i][1] = newCols.get(values[i][1]);
    }
    return pos;
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
