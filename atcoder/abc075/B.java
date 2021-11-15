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
                                ), 1<<28).start();
  }

  public void run() {
    int h = in.nextInt();
    int w = in.nextInt();

    String[] input = new String[h];
    for (int i = 0; i < h; i++) {
      input[i] = in.next();
    }

    String[] result = getResult(input);
    for (var string : result) {
      out.println(string);
    }
    
    in.close();
    out.close();
  }

  private static String[] getResult(String[] input) {
    String[] result = new String[input.length];
    for (int r = 0; r < input.length; r++) {
      result[r] = getRow(input, r);
    }
    return result;
  }

  private static String getRow(String[] input, int row) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < input[row].length(); i++) {
      if (input[row].charAt(i) == '#') {
        sb.append('#');
      } else {
        sb.append(getCount(input, row, i));
      }
    }
    return sb.toString();
  }

  private static int getCount(String[] input, int row, int col) {
    int[][] adjacent = new int[][] {
      {row-1, col-1},
      {row-1, col},
      {row-1, col+1},
      {row, col-1},
      {row, col},
      {row, col+1},
      {row+1, col-1},
      {row+1, col},
      {row+1, col+1}
    };

    int count = 0;
    for (var pos : adjacent) {
      if (pos[0] >= 0 && pos[1] >= 0 && pos[0] != input.length && pos[1] != input[row].length() && input[pos[0]].charAt(pos[1]) == '#') {
        count++;
      }
    }
    return count;
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
