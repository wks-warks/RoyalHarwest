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
    char[] s = in.next().toCharArray();
    char[] t = in.next().toCharArray();

    String result = getResult(s, t);
    out.println(result);
    
    in.close();
    out.close();
  }

  private static String getResult(char[] s, char[] t) {
    String result = null;

    for (int i = 0; i < s.length; i++) {
      if (i + t.length > s.length) {
        break;
      }

      char[] candidate = new char[s.length];
      for (int j = 0; j < s.length; j++) {
        candidate[j] = s[j];
      }
      for (int j = 0; j < t.length; j++) {
        candidate[i+j] = t[j];
      }

      if (isPossible(s, candidate)) {
        for (int idx = 0; idx < candidate.length; idx++) {
          if (candidate[idx] == '?') {
            candidate[idx] = 'a';
          }
        }
        String candidateString = String.valueOf(candidate);
        if (result == null) {
          result = candidateString;
        } else {
          if (result.compareTo(candidateString) > 0) {
            result = candidateString;
          }
        }
      }
    }

    return result == null ? "UNRESTORABLE" : result;
  }

  private static boolean isPossible(char[] first, char[] second) {
    for (int i = 0; i < first.length; i++) {
      if (first[i] != '?' && first[i] != second[i]) {
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
