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
    int x = in.nextInt();
    int y = in.nextInt();

    long a = in.nextLong();
    long b = in.nextLong();
    long c = in.nextLong();

    boolean possible = false;
    possible |= isPossible(x, y, a, b, c);
    possible |= isPossible(x, y, a, c, b);
    possible |= isPossible(x, y, b, a, c);
    possible |= isPossible(x, y, b, c, a);
    possible |= isPossible(x, y, c, a, b);
    possible |= isPossible(x, y, c, b, a);
    
    out.println(possible ? "Yes" : "No");

    in.close();
    out.close();
  }

  static boolean isPossible(int x, int y, long a, long b, long c) {
    long maxArea = (long) x * y;
    if (maxArea < a + b + c) {
      return false;
    }
    
    // All vertical
    long length = llg(a, y) + llg(b, y) + llg(c, y);
    if (length <= x) {
      return true;
    }

    long height = llg(a, x) + llg(b, x) + llg(c, x);
    if (height <= y) {
      return true;
    }

    long remainingHeight = y - llg(a, x);
    if (remainingHeight <= 0) {
      return false;
    }
    long requiredLength = llg(b, remainingHeight) + llg(c, remainingHeight);
    if (requiredLength <= x) {
      return true;
    }

    long remainingLength = x - llg(a, y);
    if (remainingLength <= 0) {
      return false;
    }
    long requiredHeight = llg(b, remainingLength) + llg(c, remainingLength);
    if (requiredHeight <= y) {
      return true;
    }

    return false;
  }

  static long llg(long numerator, long denominator) {
    return (numerator + denominator - 1) / denominator;
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


/*
  All horizontal
  All vertical
  2 in one direction.

*/