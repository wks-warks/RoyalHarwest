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

  static long mod = 1_000_000_007;
  static long pow(long base, long exp) {
    long res = 1;
    long mul = base;
    
    while (exp > 0) {
      if ((exp & 1) == 1) {
        res = res * mul % mod;
      }

      exp >>= 1;
      mul = mul * mul % mod;
    }

    return res;
  }

  static long inv2;
  static long pow2_n;
  public void run() {
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      long n = in.nextInt();
      pow2_n = pow(2, n);
      inv2 = pow(2, mod-2);

      long k = in.nextInt();
      long ans = getAns(n, k-1);
      out.println(ans);
    }
    in.close();
    out.close();
  }

  static long getAns(long n, long k) {
    if (k < 0) {
      return 1;
    }

    long and1 = 1;
    // long xor1 = pow2_n >> 1;
    long xor1 = pow2_n * inv2 % mod;
    long greater = (n & 1) == 0 ? 1 : 0;
    long xor0 = xor1;
    long equal = greater == 0 ? xor0 + 1 : xor0 - 1;

    greater = greater * pow(pow(2, k), n) % mod;
    return (greater +  equal * getAns(n, k-1) % mod) % mod;
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