// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.Point;

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
    int n = in.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }

    int maxGCD = getMaxGCD(a);
    out.println(maxGCD);
    
    in.close();
    out.close();
  }

  private static int getMaxGCD(int[] a) {
    int[] prefixGCD = getPrefixGCD(a);
    int[] suffixGCD = getSuffixGCD(a);
    
    int answer = Math.max(
      suffixGCD[1],
      prefixGCD[prefixGCD.length-2]
    );

    int start = 1;
    int end = a.length-2;
    for (int i = start; i <= end; i++) {
      answer = Math.max(
        answer,
        gcd(prefixGCD[i-1], suffixGCD[i+1])
      );
    }

    return answer;
  }

  private static int gcd(int num1, int num2) {
    return num1 == 0 ? num2 : gcd(num2 % num1, num1);
  }

  private static int[] getPrefixGCD(int[] a) {
    int[] prefixGCD = getGCDArray(0, a.length, 1, a);
    return prefixGCD;
  }

  private static int[] getSuffixGCD(int[] a) {
    int[] suffixGCD = getGCDArray(a.length-1, -1, -1, a);
    return suffixGCD;
  }

  private static int[] getGCDArray(int start, int end, int inc, int[] a) {
    int[] gcdArray = new int[a.length];
    gcdArray[start] = a[start];
    for (int i = start + inc; i != end; i += inc) {
      gcdArray[i] = gcd(gcdArray[i-inc], a[i]);
    }
    return gcdArray;
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
