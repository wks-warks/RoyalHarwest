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
    int k = in.nextInt();
    Point[] points = new Point[n];

    List<Integer> xcoordinates = new ArrayList<Integer>(n);
    List<Integer> ycoordinates = new ArrayList<Integer>(n);
    for (int i = 0; i < n; i++) {
      points[i] = new Point(in.nextInt(), in.nextInt());
      xcoordinates.add(points[i].x);
      ycoordinates.add(points[i].y);
    }
    Collections.sort(xcoordinates);
    Collections.sort(ycoordinates);

    long area = Long.MAX_VALUE;
    for (int x0 = 0; x0 < n; x0++) {
      for (int x1 = x0+1; x1 < n; x1++) {
        for (int y0 = 0; y0 < n; y0++) {
          for (int y1 = y0+1; y1 < n; y1++) {
            Point first = new Point(xcoordinates.get(x0), ycoordinates.get(y0));
            Point second = new Point(xcoordinates.get(x1), ycoordinates.get(y1));
            int interior = countInterior(first, second, points);
            if (interior >= k) {
              area = Math.min(area, (long) (second.y - first.y) * (second.x - first.x));
            }
          }
        }
      }
    }
    out.println(area);
    
    in.close();
    out.close();
  }

  private static int countInterior(Point first, Point second, Point[] points) {
    int interior = 0;

    for (var pt : points) {
      if (first.x <= pt.x && pt.x <= second.x && first.y <= pt.y && pt.y <= second.y) {
        interior++;
      }
    }
    return interior;
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
