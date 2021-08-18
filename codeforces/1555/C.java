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
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      int columns = in.nextInt();
      int[][] coins = new int[2][columns];
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < columns; j++) {
          coins[i][j] = in.nextInt();
        }
      }

      int score = getScore(coins, columns);
      out.println(score);
    }
    
    in.close();
    out.close();
  }

  static int getScore(int[][] coins, int columns) {
    int score = Integer.MAX_VALUE;
    int[] upperPrefixSum = getPrefixSum(coins[0]);
    int[] lowerPrefixSum = getPrefixSum(coins[1]);
    int sum = upperPrefixSum[columns-1] + lowerPrefixSum[columns-1];

    for (int i = 0; i < columns; i++) {
      int upper = upperPrefixSum[i];
      int lower = lowerPrefixSum[columns-1] - lowerPrefixSum[i] + coins[1][i];
      int alice = upper + lower;
      int bob = Math.max(upperPrefixSum[columns-1] - upper, lowerPrefixSum[columns-1] - lower);
      score = Math.min(score, bob);
    }

    return score;
  }

  static int[] getPrefixSum(int[] arr) {
    int[] prefixSum = new int[arr.length];
    prefixSum[0] = arr[0];
    for (int i = 1; i < arr.length; i++) {
      prefixSum[i] = prefixSum[i-1] + arr[i];
    }
    return prefixSum;
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
