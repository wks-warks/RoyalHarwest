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
    int points = in.nextInt();
    Point[] pointData = new Point[points];
    for (int p = 0; p < points; p++) {
      int x = in.nextInt();
      int y = in.nextInt();
      pointData[p] = new Point(x, y);
    }

    int superCentralPoints = countSuperCentralPoints(pointData);
    out.println(superCentralPoints);
    in.close();
    out.close();
  }

  static int countSuperCentralPoints(Point[] pointData) {
    int superCentralPoints = 0;
    for (var point : pointData) {
      if (isSuperCentral(point, pointData)) {
        superCentralPoints++;
      }
    }
    return superCentralPoints;
  }

  static boolean isSuperCentral(Point point, Point[] pointData) {
    boolean rightNeighbour = false;
    boolean leftNeighbour = false;
    boolean lowerNeighbour = false;
    boolean upperNeighbour = false;

    for (var check : pointData) {
      if (check == point) {
        continue;
      }

      if (check.y == point.y) {
        rightNeighbour |= check.x > point.x;
        leftNeighbour |= check.x < point.x;
      }

      if (check.x == point.x) {
        lowerNeighbour |= check.y < point.y;
        upperNeighbour |= check.y > point.y;
      }
    }

    return rightNeighbour && leftNeighbour && lowerNeighbour && upperNeighbour;
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

class Point {
  int x;
  int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
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
    if (st.hasMoreElements()) {
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