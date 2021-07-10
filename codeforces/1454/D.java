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
    List<Integer> primes = getPrimes(100_000);

    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      long num = in.nextLong();
      List<Long> seq = getSeq(num, primes);

      out.println(seq.size());
      for (var ai : seq) {
        out.print(ai + " ");
      }
      out.println();
    }

    in.close();
    out.close();
  }

  static List<Long> getSeq(long num, List<Integer> primes) {
    List<Pair<Long, Integer>> factors = new ArrayList<Pair<Long, Integer>>();
    
    for (long prime : primes) {
      if (num % prime == 0) {
        int count = 0;
        while (num % prime == 0) {
          num /= prime;
          count += 1;
        }

        factors.add(new Pair<Long, Integer>(prime, count));
      }
    }
    if (num > 1) {
      factors.add(new Pair<Long, Integer>(num, 1));
    }

    Pair<Long, Integer> ai = new Pair<Long, Integer>(0L, 0);
    for (var fac : factors) {
      if (fac.getSecond().compareTo(ai.getSecond()) > 0) {
        ai = fac;
      }
    }
    
    List<Long> seq = new ArrayList<Long>(Collections.nCopies(ai.getSecond(), ai.getFirst()));
    for (var fac : factors) {
      if (fac.getFirst() != ai.getFirst()) {
        for (int count = 0; count < fac.getSecond(); count++) {
          seq.set(seq.size()-1, seq.get(seq.size()-1) * fac.getFirst());
        }
      }
    }
    return seq;
  }

  static List<Integer> getPrimes(int upto) {
    List<Integer> primes = new ArrayList<Integer>();

    boolean[] isComposite = new boolean[upto+1];
    int breakPoint = upto+1;
    for (int i = 2; i <= upto; i++) {
      if (!isComposite[i]) {
        primes.add(i);
        if ((long) i * i > upto) {
          breakPoint = i;
          break;
        }
        
        for (int j = i * i; j <= upto; j += i) {
          isComposite[j] = true;
        }
      }
    }

    for (int i = breakPoint + 1; i <= upto; i++) {
      if (!isComposite[i]) {
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

class Pair<T1, T2> {
  private T1 first;
  private T2 second;
  public Pair(T1 first, T2 second) {
    setFirst(first);
    setSecond(second);
  }

  public T1 getFirst() {
    return first;
  }
  public T2 getSecond() {
    return second;
  }

  public void setFirst(T1 first) {
    this.first = first;
  }
  public void setSecond(T2 second) {
    this.second = second;
  }

  @Override
  public int hashCode() {
    final int prime = 7_243;
    int hash = first.hashCode() % prime;
    hash = (int) ((long) hash * second.hashCode() % prime);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Pair) {
      Pair<?, ?> other = (Pair<?, ?>) obj;
      return first.equals(other.getFirst()) && second.equals(other.getSecond());
    }
    return false;
  }
}