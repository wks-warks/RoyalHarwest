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
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }

    List<Point> moves = getMoves(a);
    out.println(moves.size());
    for (var move : moves) {
      out.println(move.x + " " + move.y);
    }
    
    in.close();
    out.close();
  }

  private static List<Point> getMoves(int[] a) {
    Integer negIdx = null;
    Integer posIdx = null;
    for (int i = 0; i < a.length; i++) {
      if (a[i] > 0) {
        if (posIdx == null) {
          posIdx = i;
        } else if (a[i] > a[posIdx]) {
          posIdx = i;
        }
      } else {
        if (negIdx == null) {
          negIdx = i;
        } else if (a[i] < a[negIdx]) {
          negIdx = i;
        }
      }
    }

    if (negIdx == null) {
      return posMoves(posIdx, a.length);
    } else if (posIdx == null) {
      return negMoves(negIdx, a.length);
    } else if (a[posIdx] + a[negIdx] >= 0) {
      return posMoves(posIdx, a.length);
    } else {
      return negMoves(negIdx, a.length);
    }
  }

  private static List<Point> posMoves(int posIdx, int len) {
    posIdx += 1;
    List<Point> moves = new ArrayList<Point>();
    for (int i = 1; i <= len; i++) {
      moves.add(new Point(posIdx, i));
    }
    for (int i = 1; i < len; i++) {
      moves.add(new Point(i, i+1));
    }
    return moves;
  }

  private static List<Point> negMoves(int negIdx, int len) {
    negIdx += 1;
    List<Point> moves = new ArrayList<Point>();
    for (int i = 1; i <= len; i++) {
      moves.add(new Point(negIdx, i));
    }
    for (int i = len; i > 1; i--) {
      moves.add(new Point(i, i-1));
    }
    return moves;
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
