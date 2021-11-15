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
    int a = 100*in.nextInt();
    int b = 100*in.nextInt();
    int c = in.nextInt();
    int d = in.nextInt();
    int e = in.nextInt();
    int f = in.nextInt();

    int[] answer = getAnswer(a, b, c, d, e, f);
    out.println(answer[0] + " " + answer[1]);
    
    in.close();
    out.close();
  }

  private static int[] getAnswer(int a, int b, int c, int d, int e, int f) {
    TreeSet<Integer> sugarContent = new TreeSet<Integer>();
    TreeSet<Integer> waterContent = new TreeSet<Integer>();
    sugarContent.add(0);
    waterContent.add(0);

    for (int i = 0; i < f; i++) {
      if (sugarContent.contains(i)) {
        if (i + c <= f) {
          sugarContent.add(i+c);
        }
        if (i + d <= f) {
          sugarContent.add(i+d);
        }
      }
      if (waterContent.contains(i)) {
        if (i + a <= f) {
          waterContent.add(i+a);
        }
        if (i + b <= f) {
          waterContent.add(i+b);
        }
      }
    }

    int sugar = 0;
    int water = a;
    for (var sugarQuantity : sugarContent) {
      int waterThreshold = getMinWater(sugarQuantity, e);
      Integer minWater = waterContent.ceiling(waterThreshold);
      // out.println(sugarQuantity + " " + waterThreshold + " " + minWater);
      if (minWater != null && minWater + sugarQuantity <= f && sugar * minWater < sugarQuantity * water) {
        sugar = sugarQuantity;
        water = minWater;
      }
    }
    
    return new int[] {sugar+water, sugar};
  }

  private static int getMinWater(int sugarQuantity, int e) {
    int num = 100 * sugarQuantity * (100 + e);
    int den = 100 * e;
    int effective = lig(num, den);
    return effective - sugarQuantity;
  }

  private static int lig(int num, int den) {
    return (num + den - 1) / den;
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
