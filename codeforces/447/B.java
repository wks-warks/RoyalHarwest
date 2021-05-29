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
    String s = in.next();
    int k = in.nextInt();

    int[] costs = new int[26];
    for (int i = 0; i < 26; i++) {
      costs[i] = in.nextInt();
    }

    int maxValue = getMaxValue(s, k, costs);
    out.println(Integer.toString(maxValue));

    in.close();
    out.close();
  }

  static int getMaxValue(String s, int k, int[] costs) {
    int maxCost = 0;
    for (int i = 0; i < 26; i++) {
      maxCost = Math.max(maxCost, costs[i]);
    }

    int value = 0;
    for (int i = 0; i < s.length(); i++) {
      value += (i+1) * costs[s.charAt(i) - 'a'];
    }
    int maxValue = value + maxCost * (((s.length() + k) * (s.length() + k + 1) >> 1) - (s.length() * (s.length() + 1) >> 1));
    
    return maxValue;
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