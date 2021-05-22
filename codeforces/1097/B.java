// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main implements Runnable {
  static Input in = new Input();
  static Output out = new Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), "RegalBeast", 1<<27).start();
  }

  public void run() {
    int rotations = in.nextInt();
    Set<Integer> reachableAngles = new HashSet<Integer>();
    reachableAngles.add(0);

    for (int r = 0; r < rotations; r++) {
      int angle = in.nextInt();
      Set<Integer> newReachable = new HashSet<Integer>();
      for (var prev : reachableAngles) {
        newReachable.add(modulo(prev+angle, 360));
        newReachable.add(modulo(prev-angle, 360));
      }
      reachableAngles = newReachable;
    }

    out.println(reachableAngles.contains(0) ? "Yes" : "No");
    in.close();
    out.close();
  }

  static int modulo(int num, int mod) {
    return (num + mod) % mod;
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

class Output {
  PrintWriter pw;
  public Output() {
    pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  }

  public Output(String fileName) {
    try {
      pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void print(String s) {
    pw.print(s);
  }

  public void println(String s) {
    pw.println(s);
  }

  public void close() {
    pw.close();
  }
}