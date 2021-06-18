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

  static List<Integer> primes = getPrimes((int) Math.sqrt(1_000_000_000));
  public void run() {
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      int firstNum = in.nextInt();
      int secondNum = in.nextInt();
      int moves = in.nextInt();

      boolean achievable = isAchievable(firstNum, secondNum, moves);
      out.println(achievable ? "YES" : "NO");
    }

    in.close();
    out.close();
  }

  static boolean isAchievable(int firstNum, int secondNum, int moves) {
    int common = gcd(firstNum, secondNum);
    int first = firstNum / common;
    int second = secondNum / common;

    List<Integer> firstFactors = getPrimeFactors(first);
    List<Integer> secondFactors = getPrimeFactors(second);
    List<Integer> commonFactors = getPrimeFactors(common);

    if (moves > firstFactors.size() + secondFactors.size() + (commonFactors.size()<<1)) {
      return false;
    }

    int[] firstReducible = new int[firstFactors.size()];
    for (int i = 1; i <= firstFactors.size(); i++) {
      firstReducible[i-1] = i;
    }
    if (firstReducible.length == 0) {
      firstReducible = new int[]{0};
    }

    int[] secondReducible = new int[secondFactors.size()];
    for (int i = 1; i <= secondFactors.size(); i++) {
      secondReducible[i-1] = i;
    }
    if (secondReducible.length == 0) {
      secondReducible = new int[]{0};
    }

    int[] commonReducible = new int[Math.max((commonFactors.size()<<1), 1)];
    for (int i = 1; i < commonReducible.length; i++) {
      commonReducible[i] = i+1;
    }

    if (firstNum != secondNum) {
      if (common == Math.min(firstNum, secondNum)) {
        return moves >= 1;
      } else {
        return moves >= 2;
      }
    } else {
      return moves > 1;
    }
  }

  static int gcd(int num1, int num2) {
    if (num2 == 0) {
      return num1;
    }
    return gcd(num2, num1 % num2);
  }

  static List<Integer> getPrimeFactors(int num) {
    List<Integer> primeFactors = new ArrayList<Integer>();
    for (var prime : primes) {
      if (prime > num) {
        break;
      }

      while (num % prime == 0) {
        num /= prime;
        primeFactors.add(prime);
      } 
    }

    if (num > 1) {
      primeFactors.add(num);
    }
    return primeFactors;
  }

  static List<Integer> getPrimes(int upto) {
    List<Integer> primes = new ArrayList<Integer>();
    primes.add(2);

    boolean[] isComposite = new boolean[upto+1];
    for (int candidate = 3; candidate <= upto; candidate += 2) {
      if (isComposite[candidate]) {
        continue;
      }
      primes.add(candidate);
      for (long multiple = (long) candidate * candidate; multiple <= upto; multiple += candidate) {
        isComposite[(int) multiple] = true;
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