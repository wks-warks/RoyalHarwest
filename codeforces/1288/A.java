// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main implements Runnable {
  static Input in = new Input();
  static PrintWriter out = Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), "RegalBeast", 1<<27).start();
  }

  public void run() {
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      int dueDate = in.nextInt();
      int runTime = in.nextInt();

      boolean deliverable = isDeliverable(dueDate, runTime);
      out.println(deliverable ? "Yes" : "No");
    }

    in.close();
    out.close();
  }

  static boolean isDeliverable(int dueDate, int runTime) {
    return isDeliverable(dueDate, runTime, 0, dueDate-1);
  }

  static boolean isDeliverable(int dueDate, int runTime, int minOpt, int maxOpt) {
    if (minOpt > maxOpt) {
      return false;
    }

    int midOpt = (minOpt + maxOpt) >> 1;
    if (midOpt + (double) runTime / (midOpt+1) <= dueDate) {
      return true;
    }

    double leftCandidate = Double.MAX_VALUE;
    double rightCandidate = Double.MAX_VALUE;

    if (midOpt > 0) {
      leftCandidate = runTime / midOpt;
    }
    if (midOpt + 1 < dueDate) {
      rightCandidate = runTime / (midOpt+2);
    }

    
    if (leftCandidate < rightCandidate) {
      maxOpt = midOpt - 1;
    } else {
      minOpt = midOpt + 1;
    }
    return isDeliverable(dueDate, runTime, minOpt, maxOpt);
  }

  static int LIG(double denominator) {
    if (denominator % 1.00 != 0.0) {
      denominator += 1.0;
    }
    return (int) (denominator);
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