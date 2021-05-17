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
    for (int t = 0; t < tests; t++) {
      int cupcakes = in.nextInt();

      int[] tastiness = new int[cupcakes];
      for (int c = 0; c < cupcakes; c++) {
        tastiness[c] = in.nextInt();
      }

      boolean happyYasser = isYasserHappy(cupcakes, tastiness);
      out.print(happyYasser ? "YES\n" : "NO\n");
    }

    in.close();
    out.close();
  }

  static boolean isYasserHappy(int cupcakes, int[] tastiness) {
    long[] tastinessPFSum = new long[cupcakes+1];
    for (int c = 1; c <= cupcakes; c++) {
      tastinessPFSum[c] = tastinessPFSum[c-1] + tastiness[c-1];
    }

    long totalTastinessYasser = tastinessPFSum[cupcakes];
    long optimumTastinessAdel = Long.MIN_VALUE;

    int minPFIdx = 0;
    int minPFIdx2 = -1;
    for (int c  = 1; c <= cupcakes; c++) {
      int effectiveMinIdx = (c == cupcakes && minPFIdx == 0) ? minPFIdx2 : minPFIdx;
      long tastinessAdel = tastinessPFSum[c] - tastinessPFSum[effectiveMinIdx];
      optimumTastinessAdel = Math.max(optimumTastinessAdel, tastinessAdel);

      if (tastinessPFSum[minPFIdx] > tastinessPFSum[c]) {
        minPFIdx2 = minPFIdx;
        minPFIdx = c;
      } else if (minPFIdx2 == -1 || (tastinessPFSum[minPFIdx2] > tastinessPFSum[c])) {
        minPFIdx2 = c;
      }
    }
    return totalTastinessYasser > optimumTastinessAdel;
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