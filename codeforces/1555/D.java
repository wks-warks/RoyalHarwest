// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main implements Runnable {
  static Input in = new Input();
  static PrintWriter out = Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), "King", 1<<28).start();
  }

  public void run() {
    String[] cases = new String[] {
      "abc",
      "acb",
      "bac",
      "bca",
      "cab",
      "cba"
    };

    int sLen = in.nextInt();
    int queries = in.nextInt();
    String s = in.next();

    DP[] dps = new DP[cases.length];
    for (int i = 0; i < cases.length; i++) {
      dps[i] = new DP(cases[i].toCharArray(), s);
    }

    for (int q = 0; q < queries; q++) {
      int left = in.nextInt();
      int right = in.nextInt();
      int ans = Integer.MAX_VALUE;

      for (var dp : dps) {
        ans = Math.min(ans, dp.getAns(left, right));
      }
      out.println(ans);
    }

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

class DP {
  int[] cost;
  
  DP(char[] triplet, String s) {
    cost = new int[s.length()+1];

    for (int i = 0; i < s.length(); i++) {
      cost[i+1] = cost[i] + (s.charAt(i) != triplet[i%3] ? 1 : 0);
    }
  }

  int getAns(int left, int right) {
    return cost[right] - cost[left-1];
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
