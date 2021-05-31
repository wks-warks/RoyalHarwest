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
      int n = in.nextInt();
      int[][] operations = getOperations(n);
      
      out.println(Integer.toString(2));
      for (var op : operations) {
        out.println(String.format("%d %d", op[0], op[1]));
      }
    }

    in.close();
    out.close();
  }

  static int[][] getOperations(int n) {
    TreeMap<Integer, Integer> frequency = new TreeMap<Integer, Integer>();
    for (int i = 1; i <= n; i++) {
      frequency.put(i, 1);
    }

    int[][] operations = new int[n-1][2];
    int op = 0;

    for (int i = 1; i < n; i++) {
      int num1 = getLargest(frequency);
      int num2 = getLargest(frequency);
      operations[op][0] = num1;
      operations[op++][1] = num2;

      updateFreq(frequency, ((num1 + num2 + 1) >> 1));
    }
    return operations;
  }

  static int getLargest(TreeMap<Integer, Integer> frequency) {
    int num = frequency.lastKey();
    int newCount = frequency.get(num) - 1;
    
    if (newCount == 0) {
      frequency.remove(num);
    } else {
      frequency.put(num, newCount);
    }
    return num;
  }

  static void updateFreq(TreeMap<Integer, Integer> frequency, int num) {
    if (frequency.containsKey(num)) {
      frequency.put(num, frequency.get(num) + 1);
    } else {
      frequency.put(num, 1);
    }
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