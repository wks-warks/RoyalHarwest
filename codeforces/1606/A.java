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
      String s = in.next();
      String res = getRes(s);
      out.println(res);
    }
    
    in.close();
    out.close();
  }

  private static String getRes(String s) {
    int aIdx = -1;
    int abCount = 0;
    int bIdx = -1;
    int baCount = 0;
    
    for (int i = 0; i < s.length()-1; i++) {
      if (s.charAt(i) != s.charAt(i+1)) {
        if (s.charAt(i) == 'a') {
          abCount++;
        } else {
          baCount++;
        }
      }
    }

    boolean hasB = false;
    boolean hasA = false;
    for (int i = s.length()-1; i >= 0; i--) {
      if (s.charAt(i) == 'a') {
        hasA = true;
      } else {
        hasB = true;
      }

      if (hasA && s.charAt(i) == 'b') {
        bIdx = i;
      } else if (hasB && s.charAt(i) == 'a') {
        aIdx = i;
      }
    }

    if (abCount > baCount) {
      return s.substring(0, aIdx) + "b" + s.substring(aIdx+1);
      // return s.substring(0, abIdx) + "b" + s.substring(abIdx+1);
    } else if (abCount == baCount) {
      return s;
    } else {
      return s.substring(0, bIdx) + "a" + s.substring(bIdx+1);
      // return s.substring(0, baIdx) + "a" + s.substring(baIdx+1);
    }
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
