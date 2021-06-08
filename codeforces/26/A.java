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

  public void run() {
    int n = in.nextInt();
    List<Integer> primes = getPrimes(n);

    int almostPrimes = countAlmostPrimes(n, primes);
    out.println(almostPrimes);

    in.close();
    out.close();
  }

  static int countAlmostPrimes(int n, List<Integer> primes) {
    int almostPrimes = 0;

    for (int num = 1; num <= n; num++) {
      int primeDivisorCount = getPrimeDivisorCount(num, primes);
      if (primeDivisorCount == 2) {
        almostPrimes++;
      }
    }

    return almostPrimes;
  }

  static int getPrimeDivisorCount(int num, List<Integer> primes) {
    int primeDivisorCount = 0;

    for (int prime : primes) {
      if (prime > num) {
        break;
      }

      if (num % prime == 0) {
        primeDivisorCount++;
        while (num % prime == 0) {
          num /= prime;
        }
      }
    }

    return primeDivisorCount;
  }

  static List<Integer> getPrimes(int upto) {
    List<Integer> primes = new ArrayList<Integer>();
    boolean[] isComposite = new boolean[upto+1];
    primes.add(2);

    for (int candidate = 3; candidate <= upto; candidate += 2) {
      if (isComposite[candidate]) {
        continue;
      }
      primes.add(candidate);

      for (int multiple = candidate*candidate; multiple <= upto; multiple += candidate) {
        isComposite[multiple] = true;
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