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
      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = in.nextInt();
      }
      String colors = in.next();
    
      boolean permutable = isPermutable(a, colors);
      out.println(permutable ? "Yes" : "No");
    }
    
    in.close();
    out.close();
  }

  private static boolean isPermutable(int[] a, String colors) {
    int[][] freq = new int[a.length+1][2];
    for (int i = 0; i < a.length; i++) {
      boolean blue = colors.charAt(i) == 'B';
      int num = a[i];
   
      if (num < 1) {
        if (blue) {
          return false;
        }
        num = 1;
      }
      if (num > a.length) {
        if (!blue) {
          return false;
        }
        num = a.length;
      }
      
      freq[num][blue ? 0 : 1]++;
    }

    List<Integer> blues = new ArrayList<Integer>();
    List<Integer> reds = new ArrayList<Integer>();
    for (int i = 1; i <= a.length; i++) {
      while (freq[i][0]-- > 0) {
        blues.add(i);
      }
      while (freq[i][1]-- > 0) {
        reds.add(i);
      }
    }

    int bluePtr = 0;
    int redPtr = 0;
    for (int i = 1; i <= a.length; i++) {
      while (bluePtr < blues.size() && blues.get(bluePtr) < i) {
        bluePtr++;
      }
      if (bluePtr < blues.size()) {
        bluePtr++;
      } else if (redPtr < reds.size()) {
        if (reds.get(redPtr) > i) {
          return false;
        } else {
          redPtr++;
        }
      } else {
        return false;
      }
    }
    return true;
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
