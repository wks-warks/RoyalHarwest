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

  static final int DAYS = 5;
  public void run() {
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      int students = in.nextInt();
      int[][] availableCount = new int[DAYS][DAYS];

      for (int s = 0; s < students; s++) {
        int[] preference = new int[DAYS];
        for (int d = 0; d < DAYS; d++) {
          preference[d] = in.nextInt();
        }

        for (int d1 = 0; d1 < DAYS; d1++) {
          for (int d2 = 0; d2 < DAYS; d2++) {
            if ((preference[d1] & preference[d2]) > 0) {
              availableCount[d1][d2]++;
            }
          }
        }
      }

      boolean possible = false;
      if ((students & 1) == 0) {
        outer:
        for (int d1 = 0; d1 < DAYS; d1++) {
          for (int d2 = 0; d2 < DAYS; d2++) {
            if (d1 == d2) {
              continue;
            }
            possible = isPossible(students, d1, d2, availableCount);
            
            if (possible) {
              break outer;
            }
          }
        }
      }

      out.println(possible ? "YES" : "NO");
    }
    
    in.close();
    out.close();
  }

  static boolean isPossible(int students, int d1, int d2, int[][] availableCount) {
    int onlyD1 = availableCount[d1][d1] - availableCount[d1][d2];
    int onlyD2 = availableCount[d2][d2] - availableCount[d2][d1];
    int both = availableCount[d1][d2];

    if (onlyD1 + onlyD2 + both < students) {
      return false;
    }

    int half = students>>1;    
    // if (d1 == 1 && d2 == 3) {
      // out.println(onlyD1 + " " + onlyD2 + " " + both + " " + half);
    // }
    boolean res = onlyD1 <= half && onlyD2 <= half;
    // if (res == true) {
    //   out.println(d1 + " " + d2);
    // }
    return res;
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
