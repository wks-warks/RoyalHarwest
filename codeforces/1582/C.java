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
      in.next();
      String s = in.next();

      int erased = findMinimumErased(s);
      out.println(erased);
    }
    
    in.close();
    out.close();
  }
  
  private static int findMinimumErased(String s) {
    int leftPtr = 0;
    int rightPtr = s.length()-1;

    while (leftPtr < rightPtr && s.charAt(leftPtr) == s.charAt(rightPtr)) {
      leftPtr++;
      rightPtr--;
    }
    if (leftPtr >= rightPtr) {
      return 0;
    }

    int candidate1 = findMinimumErased(s.substring(leftPtr, rightPtr+1), s.charAt(leftPtr));
    int candidate2 = findMinimumErased(s.substring(leftPtr, rightPtr+1), s.charAt(rightPtr));
    if (Math.max(candidate1, candidate2) == -1) {
      return -1;
    } else if (Math.min(candidate1, candidate2) == -1) {
      return Math.max(candidate1, candidate2);
    } else {
      return Math.min(candidate1, candidate2);
    }
  }

  private static int findMinimumErased(String s, char skippable) {
    int leftPtr = 0;
    int rightPtr = s.length()-1;
    
    int skipped = 0;
    while (leftPtr < rightPtr) {
      char left = s.charAt(leftPtr);
      char right = s.charAt(rightPtr);

      if (left == right) {
        leftPtr++;
        rightPtr--;
        continue;
      } else if (left == skippable) {
        leftPtr++;
        skipped++;
      } else if (right == skippable) {
        rightPtr--;
        skipped++;
      } else {
        return -1;
      }
    }

    return skipped;
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
