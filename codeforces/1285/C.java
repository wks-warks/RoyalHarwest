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
    long lcmValue = in.nextLong();
    // long minMaxValue = getMinMaxVal(lcmValue);
    // out.println(minMaxValue + " " + (lcmValue / minMaxValue));

    printAnswer(lcmValue);
    in.close();
    out.close();
  }

  static void printAnswer(long lcmValue) {
    List<Long> primes = new ArrayList<Long>();
    int upto = (int) Math.sqrt(lcmValue) + 1;
    boolean[] isComposite = new boolean[upto];
    for (int i = 2; i < upto; i++) {
      if (isComposite[i]) {
        continue;
      }
      primes.add((long) i);
      for (long j = (long) i*i; j < upto; j += i) {
        isComposite[(int) j] = true;
      }
    }

    List<Long> factorList = new ArrayList<Long>();
    Map<Long, Integer> primeFactors = new HashMap<Long, Integer>();
    for (var prime : primes) {
      while (lcmValue % prime == 0) {
        lcmValue /= prime;
        if (primeFactors.containsKey(prime)) {
          primeFactors.put(prime, primeFactors.get(prime) + 1);
        } else {
          primeFactors.put(prime, 1);
          factorList.add(prime);
        }
      }
    }

    if (lcmValue > 1) {
      factorList.add(lcmValue);
      primeFactors.put(lcmValue, 1);
    }

    long[] optimumPair = new long[] {Long.MAX_VALUE, Long.MAX_VALUE};
    setOptimumPair(0, factorList, primeFactors, optimumPair);
    out.println(optimumPair[0] + " " + optimumPair[1]);
  }

  static void setOptimumPair(int mask, List<Long> factorList, Map<Long, Integer> primeFactors, long[] optimumPair) {
    if (mask == 1<<factorList.size()) {
      return;
    }

    long firstNum = 1;
    long secondNum = 1;

    int bit = 1;
    for (int i = 0; i < factorList.size(); i++) {
      if ((bit & mask) > 0) {
        firstNum *= binExp(factorList.get(i), primeFactors.get(factorList.get(i)));
      } else {
        secondNum *= binExp(factorList.get(i), primeFactors.get(factorList.get(i)));
      }

      bit <<= 1;
    }

    if (Math.max(firstNum, secondNum) < Math.max(optimumPair[0], optimumPair[1])) {
      optimumPair[0] = firstNum;
      optimumPair[1] = secondNum;
    }
    setOptimumPair(mask+1, factorList, primeFactors, optimumPair);
  }

  static long binExp(long base, long pow) {
    long res = 1;
    long mul = base;

    while (pow > 0) {
      if ((pow & 1) == 1) {
        res *= mul;
      }
      mul *= mul;
      pow >>= 1;
    }
    return res;
  }

  static long getMinMaxVal(long lcmValue) {
    long minMaxValue = lcmValue;

    for (long candidate = 1; candidate * candidate <= lcmValue; candidate++) {
      if (lcmValue % candidate > 0) {
        continue;
      }
      
      long partner = lcmValue / candidate;
      if (lcm(candidate, partner) != lcmValue) {
        continue;
      }
      minMaxValue = Math.min(minMaxValue, partner);
    }

    return minMaxValue;
  }

  static long lcm(long num1, long num2) {
    return num1 / gcd(num1, num2) * num2;
  }

  static long gcd(long num1, long num2) {
    if (num2 == 0) {
      return num1;
    }
    return gcd(num2, num1 % num2);
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