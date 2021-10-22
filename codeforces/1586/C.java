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
    int n = in.nextInt();
    int m = in.nextInt();
    char[][] grid = new char[n][];
    for (int r = 0; r < n; r++) {
      grid[r] = in.next().toCharArray();
    }

    int[] memo = getMemo(grid);

    int q = in.nextInt();
    while (q-->0) {
      int left = in.nextInt();
      int right = in.nextInt();

      boolean determinable = true;
      int diff = right > left ? memo[right] - memo[left] : 0;
      if (diff > 0) {
        determinable = false;
      }

      out.println(determinable ? "Yes" : "No");
    }

    
    in.close();
    out.close();
  }

  static int[] getMemo(char[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;

    int[] memo = new int[cols+1];

    for (int r = 1; r < rows; r++) {
      for (int c = 1; c < cols; c++) {
        if (grid[r-1][c] == 'X' && grid[r][c-1] == 'X') {
          memo[c+1] = 1;
        }
      }
    }

    return getPrefixSum(memo);
  }

  static int[] getPrefixSum(int[] arr) {
    int[] prefixSum = new int[arr.length];
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
