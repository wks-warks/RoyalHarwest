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
    int tests = in.nextInt();
    for (int t = 0; t  < tests; t++) {
      int sLen = in.nextInt();
      String s = in.next();

      boolean functional = checkFunctional(s);
      out.println((functional ? "YES" : "NO"));
    }

    in.close();
    out.close();
  }

  static boolean checkFunctional(String s) {
    int subseqs = s.length() / 3;
    int tCount = 0;
    int mCount = 0;

    for (var ch : s.toCharArray()) {
      if (ch == 'T') {
        tCount++;
      } else {
        mCount++;
      }

      if (mCount > tCount) {
        return false;
      }
    }

    s = (new StringBuilder(s)).reverse().toString();
    tCount = 0;
    mCount = 0;
    for (var ch : s.toCharArray()) {
      if (ch  == 'T') {
        tCount++;
      } else {
        mCount++;
      }

      if (mCount > tCount) {
        return false;
      }
    }

    return mCount == subseqs;
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