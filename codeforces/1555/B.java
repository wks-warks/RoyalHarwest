// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.Point;

public class Main implements Runnable {
  static Input in = new Input();
  static PrintWriter out = Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), "King", 1<<28).start();
  }

  public void run() {
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      int width = in.nextInt();
      int height = in.nextInt();
      Point lowerLeft = new Point(in.nextInt(), in.nextInt());
      Point upperRight = new Point(in.nextInt(), in.nextInt());
      int secondWidth = in.nextInt();
      int secondHeight = in.nextInt();

      int move = computeDistance(width, height, lowerLeft, upperRight, secondWidth, secondHeight);
      out.println(move);
    }

    in.close();
    out.close();
  }

  static int computeDistance(int width, int height, Point lowerLeft, Point upperRight, int secondWidth, int secondHeight) {
    int answer = Integer.MAX_VALUE;
    // System.out.println(answer);
    // System.out.println(upperRight.y+" "+secondHeight);
    if (upperRight.y-lowerLeft.y+secondHeight <= height) {
      answer = Math.min(answer, computeAdditional(height - upperRight.y, secondHeight));
      // System.out.println(answer);
      answer = Math.min(answer, computeAdditional(lowerLeft.y, secondHeight));
      // System.out.println(answer);
    }

    if (upperRight.x-lowerLeft.x+secondWidth <= width) {
      answer = Math.min(answer, computeAdditional(width - upperRight.x, secondWidth));
      // System.out.println(answer);
      answer = Math.min(answer, computeAdditional(lowerLeft.x, secondWidth));
      // System.out.println(answer);
    }
    return answer != Integer.MAX_VALUE ? answer : -1;
  }

  static int computeAdditional(int presentSpace, int needed) {
    if (presentSpace >= needed) {
      return 0;
    } else {
      return needed - presentSpace;
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
