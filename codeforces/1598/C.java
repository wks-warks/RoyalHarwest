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
      Map<Integer, Integer> freq = new HashMap<Integer, Integer>();

      long sum = 0;
      for (int i = 0; i < n; i++) {
        int ai = in.nextInt();
        sum += ai;

        if (!freq.containsKey(ai)) {
          freq.put(ai, 0);
        }
        freq.put(ai, freq.get(ai)+1);
      }

      long pairs = countPairs(n, sum, freq);
      out.println(pairs);
    }
    
    in.close();
    out.close();
  }

  static long countPairs(int n, long sum, Map<Integer, Integer> freq) {
    long pairs = 0;

    for (var me : freq.entrySet()) {
      int a = me.getKey();
      int b = getB(a, n, sum);
      // out.println(b);
      if (b == -1) {
        continue;
      }

      int aCount = me.getValue();
      Integer bCount = freq.get(b);
      if (bCount == null) {
        continue;
      }

      if (a == b) {
        pairs += (long) aCount * (aCount-1) / 2;
      } else {
        pairs += (long) aCount * bCount;
        freq.put(b, 0);
      }
      freq.put(a, 0);
    }

    return pairs;
  }

  static int getB(int a, int n, long sum) {
    // out.println(a + " " + n + " s" + sum);
    long rem = 2 * sum - (long) n * a;
    if (rem < 0 || rem % n > 0) {
      return -1;
    }

    return (int) (rem / n);
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
