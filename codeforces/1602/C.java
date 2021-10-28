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

      List<Integer> k = getK(a);
      for (var num : k) {
        out.print(num + " ");
      }
      out.println();
    }
    
    in.close();
    out.close();
  }

  private static List<Integer> getK(int[] a) {
    List<Integer> k = new ArrayList<Integer>();

    int or = 0;
    for (var num : a) {
      or |= num;
    }
    if (or == 0) {
      for (int i = 1; i <= a.length; i++) {
        k.add(i);
      }
      return k;
    }

    int[] freq = new int[30];
    for (var num : a) {
      for (int i = 0; i < 30; i++) {
        // out.println(num + " " +  (i<<2));
        if ((num & (1<<i)) > 0) {
          freq[i]++;
        }
      }
    }

    int g = 0;
    for (var count : freq) {
      // out.println(count);
      g = gcd(g, count);
    }

    Set<Integer> set = new TreeSet<Integer>();
    for (int i = 1; i * i <= g; i++) {
      if (g % i == 0) {
        set.add(i);
        set.add(g/i);
      }
    }
    for (var num : set) {
      k.add(num);
    }
    return k;
  }

  private static int gcd(int num1, int num2) {
    if (num1 == 0) {
      return num2;
    }
    return gcd(num2 % num1, num1);
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
