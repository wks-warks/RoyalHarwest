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
    int days = in.nextInt();
    int sumTime = in.nextInt();

    int[][] dailyPrepLimits = new int[days][2];
    for (int d = 0; d < days; d++) {
      dailyPrepLimits[d][0] = in.nextInt();
      dailyPrepLimits[d][1] = in.nextInt();
    }

    Solution solution = Solution.getResponse(days, sumTime, dailyPrepLimits);
    out.println(solution.toString());

    in.close();
    out.close();
  }
}

class Solution {
  boolean possible;
  int[] prepTime;

  public Solution(boolean possible, int[] prepTime) {
    this.possible = possible;
    this.prepTime = prepTime;
  }

  static Solution getResponse(int days, int sumTime, int[][] dailyPrepLimits) {
    int minPrep = 0;
    int maxPrep = 0;

    for (var prepLimit : dailyPrepLimits) {
      minPrep += prepLimit[0];
      maxPrep += prepLimit[1];
    }

    if (sumTime < minPrep || sumTime > maxPrep) {
      return new Solution(false, null);
    } else {
      Solution solution = new Solution(true, new int[days]);
      
      int timeStudied = 0;
      for (int d = 0; d < days; d++) {
        solution.prepTime[d] = getPrepTime(timeStudied, minPrep, maxPrep, dailyPrepLimits[d], sumTime);
        timeStudied += solution.prepTime[d];
        minPrep -= dailyPrepLimits[d][0];
        maxPrep -= dailyPrepLimits[d][0];
      }

      return solution;
    }
  }

  static int getPrepTime(int timeStudied, int minPrep, int maxPrep, int[] limits, int sumTime) {
    int nextMin = minPrep - limits[0];
    
    if (timeStudied + limits[1] + nextMin > sumTime) {
      return sumTime - timeStudied - nextMin;
    } else {
      return limits[1];
    }
  }

  @Override
  public String toString() {
    if (!possible) {
      return "NO";
    } else {
      StringBuilder sb = new StringBuilder();

      sb.append("YES\n");
      for (var hours : prepTime) {
        sb.append(hours + " ");
      }

      return sb.toString();
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