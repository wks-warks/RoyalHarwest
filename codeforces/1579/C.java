// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main implements Runnable {
  static Input in = new Input();
  static PrintWriter out = Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), String.join(
                                  "All that is gold does not glitter,",
                                  "Not all those who wander are lost;",
                                  "The old that is strong does not wither,",
                                  "Deep roots are not reached by the frost.",
                                  
                                  "From the ashes a fire shall be woken,",
                                  "A light from the shadows shall spring;",
                                  "Renewed shall be blade that was broken,",
                                  "The crownless again shall be king."
                                ), 1<<25).start();
  }

  public void run() {
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      int n = in.nextInt();
      int m = in.nextInt();
      int k = in.nextInt();

      String[] field = new String[n];
      for (int i = 0; i < n; i++) {
        field[i] = in.next();
      }

      boolean claim = checkClaim(field, k);
      out.println(claim ? "Yes" : "No");
    }

    in.close();
    out.close();
  }

  static boolean checkClaim(String[] field, int k) {
    int[][] optimalField = getOptimalField(field);
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < optimalField.length; i++) {
      for (int j = 0; j < optimalField[i].length; j++) {
        min = Math.min(min, optimalField[i][j]);
      }
    }

    return min >= k;
  }

  static int[][] getOptimalField(String[] field) {
    int[][] optimalField = new int[field.length][field[0].length()];
    for (int i = 0; i < optimalField.length; i++) {
      for (int j = 0; j < optimalField[i].length; j++) {
        optimalField[i][j] = field[i].charAt(j) == '.' ? Integer.MAX_VALUE : 0;
      }
    }
    
    for (int i = 0; i < optimalField.length; i++) {
      for (int j = 0; j < optimalField[i].length; j++) {
        if (field[i].charAt(j) == '*') {
          int tickLength = getTickLength(field, i, j);
          
          for (int k = 0; k <= tickLength; k++) {
            optimalField[i-k][j-k] = Math.max(optimalField[i-k][j-k], tickLength);
            optimalField[i-k][j+k] = Math.max(optimalField[i-k][j+k], tickLength);
          }
        }
      }
    }

    return optimalField;
  }

  static int getTickLength(String[] field, int i, int j) {
    int tickLength = 0;
    while (true) {
      boolean increase = true;
      increase &= isValid(field, i-tickLength-1, j-tickLength-1) && field[i-tickLength-1].charAt(j-tickLength-1) == '*';
      increase &= isValid(field, i-tickLength-1, j+tickLength+1) && field[i-tickLength-1].charAt(j+tickLength+1) == '*';

      if (increase) {
        tickLength++;
      } else {
        return tickLength;
      }
    }
  }

  static boolean isValid(String[] field, int i, int j) {
    return (i >= 0 && j >= 0 && j < field[i].length());
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