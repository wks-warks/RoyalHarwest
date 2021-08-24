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

  public void run() {
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      int caves = in.nextInt();
      int[][] armorLevels = new int[caves][];
      for (int c = 0; c < caves; c++) {
        int monsters = in.nextInt();

        armorLevels[c] = new int[monsters];
        for (int m = 0; m < monsters; m++) {
          armorLevels[c][m] = in.nextInt(); 
        }
      }

      int minStart = getMinStart(caves, armorLevels);
      out.println(minStart);
    }

    in.close();
    out.close();
  }

  static int getMinStart(int caves, int[][] armorLevels) {
    int[] minPerCave = new int[caves];
    for (int c = 0; c < caves; c++) {
      for (int i = 0; i < armorLevels[c].length; i++) {
        minPerCave[c] = Math.max(minPerCave[c], armorLevels[c][i] + 1 - i);
      }
    }

    TreeMap<Integer, Integer> requiredAndBonus = new TreeMap<Integer, Integer>();
    for (int c = 0; c < caves; c++) {
      if (!requiredAndBonus.containsKey(minPerCave[c])) {
        requiredAndBonus.put(minPerCave[c], 0);
      }
      requiredAndBonus.put(minPerCave[c], requiredAndBonus.get(minPerCave[c]) + armorLevels[c].length);
    }

    int minStart = 0;
    int curr = 0;
    while (requiredAndBonus.size() > 0) {
      int leastRequired = requiredAndBonus.firstKey();
      if (leastRequired > curr) {
        minStart += leastRequired - curr;
        curr = leastRequired;
      }

      curr += requiredAndBonus.get(leastRequired);
      requiredAndBonus.remove(leastRequired);
    }

    return minStart;
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
