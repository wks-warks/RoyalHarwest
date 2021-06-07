// Author : RegalBeast

import java.io.*;
import java.util.*;

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

  static String first, second;
  public void run() {
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      first = in.next();
      second = in.next();

      int deletions = countDeletions();
      out.println(deletions);
    }

    in.close();
    out.close();
  }

  static int countDeletions() {
    return first.length() + second.length() - (getLongestCommon() << 1);
  }

  static List<List<Integer>> memo;
  static int getLongestCommon() {
    int len1 = first.length();
    int len2 = second.length();

    memo = new ArrayList<List<Integer>>(len1);
    for (int i = 0; i < len1; i++) {
      memo.add(new ArrayList<Integer>(Collections.nCopies(len2, null)));
    }

    int commonLen = 0;
    for (int i = 0; i < len1; i++) {
      for (int j = 0; j < len2; j++) {
        commonLen = Math.max(commonLen, getCommonLen(i, j));
      }
    }
    return commonLen;
  }

  static int getCommonLen(int i, int j) {
    if (i == first.length() || j == second.length()) {
      return 0;
    }
    if (memo.get(i).get(j) != null) {
      return memo.get(i).get(j);
    }

    if (first.charAt(i) == (second.charAt(j))) {
      memo.get(i).set(j, 1 + getCommonLen(i+1, j+1));
    } else {
      memo.get(i).set(j, 0);
    }

    return memo.get(i).get(j);
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