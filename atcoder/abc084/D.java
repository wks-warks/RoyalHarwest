// Author : RegalBeast
// Jay Tanay Baba Ki

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

  boolean[] similar = getSimilar();
  public void run() {
    int[] prefixSum = getPrefixSum(similar);

    int q = in.nextInt();
    while (q-- > 0) {
      int li = in.nextInt();
      int ri = in.nextInt();
      int similarCount = getSimilarCount(prefixSum, li, ri);
      out.println(similarCount);
    }

    in.close();
    out.close();
  }

  private static int getSimilarCount(int[] prefixSum, int li, int ri) {
    return prefixSum[ri] - prefixSum[li-1];
  }

  private static int[] getPrefixSum(boolean[] similar) {
    int[] prefixSum = new int[similar.length];
    for (int i = 1; i < prefixSum.length; i++) {
      prefixSum[i] = prefixSum[i-1] + (similar[i] ? 1 : 0);
    }
    return prefixSum;
  }

  private static final int LIMIT = 100_001;
  private boolean[] getSimilar() {
    boolean[] similar = new boolean[LIMIT];
    Set<Integer> primes = getPrimes(LIMIT);

    for (int i = 1; i < LIMIT; i += 2) {
      similar[i] = primes.contains(i) && primes.contains((i+1)>>1);
    }

    return similar;
  }

  private static Set<Integer> getPrimes(int upto) {
    Set<Integer> primes = new HashSet<Integer>();

    int sqrt = 1 + (int) Math.sqrt(upto);
    BitSet isComposite = new BitSet(upto+1);
    for (int i = 2; i <= upto; i++) {
      if (!isComposite.get(i) && i <= sqrt) {
        for (int j = i * i; j <= upto; j += i) {
          isComposite.set(j);
        }
      }
    }
    for (int i = 2; i <= upto; i++) {
      if (!isComposite.get(i)) {
        primes.add(i);
      }
    }
    return primes;
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
