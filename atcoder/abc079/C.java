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
    String inp = in.next();
    int a = inp.charAt(0)-'0';
    int b = inp.charAt(1)-'0';
    int c = inp.charAt(2)-'0';
    int d = inp.charAt(3)-'0';

    for (int i = 0; ; i++) {
      if (printExpression(a, b, c, d, i)) {
        break;
      }
    }
    
    in.close();
    out.close();
  }

  private static boolean printExpression(int a, int b, int c, int d, int bitmask) {
    int sum = 0;
    sum += a;
    boolean bpos = (bitmask & 1) > 0;
    sum += bpos? b : -b;
    boolean cpos = (bitmask & 2) > 0;
    sum += cpos? c : -c;
    boolean dpos = (bitmask & 4) > 0;
    sum += dpos? d : -d;

    if (sum == 7) {
      StringBuilder expression = new StringBuilder();
      expression.append(a);
      expression.append(bpos ? '+' : '-');
      expression.append(b);
      expression.append(cpos ? '+' : '-');
      expression.append(c);
      expression.append(dpos ? '+' : '-');
      expression.append(d);
      expression.append("=7");
      out.println(expression);

      return true;
    }
    return false;
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
