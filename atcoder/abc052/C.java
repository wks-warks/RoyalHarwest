import java.io.*;
import java.util.*;
 
public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));
  
  static int MOD = 1_000_000_007;
  static Set<Integer> primes = new TreeSet<Integer>();
  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    // tests = FR.nextInt();
    setPrimes(1000);
    for (int t = 0; t < tests; ++t) {
      int factorialOf = FR.nextInt();
      int divisors = getDivisorCount(factorialOf);
      solution.append(divisors + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static void setPrimes(int upto) {
    primes.add(2);
    boolean[] isComposite = new boolean[upto+1];
    for (int candidate = 3; candidate < upto; candidate += 2) {
      if (isComposite[candidate]) continue;
      primes.add(candidate);
      for (int assign = candidate*candidate; assign <= upto; assign += candidate) {
        isComposite[assign] = true;
      }
    }
  }
 
  static int getDivisorCount(int factorialOf) {
    Map<Integer, Integer> factorPowers = new HashMap<Integer, Integer>();

    for (int i = 2; i <= factorialOf; ++i) {
      int candidate = i;
      for (var prime : primes) {
        if (prime > candidate) break;
        while (candidate % prime == 0) {
          factorPowers.put(prime, factorPowers.containsKey(prime)? factorPowers.get(prime)+1 : 1);
          candidate /= prime;
        }
      }
      if (candidate > 1) factorPowers.put(candidate, factorPowers.containsKey(candidate)? factorPowers.get(candidate)+1 : 1);
    }
    int divisors = 1;
    for (var element : factorPowers.entrySet()) {
      int power = element.getValue();
      divisors = (int) ((long)divisors * (power+1) % MOD);      
    }
    return divisors;
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;
  
    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }
  
    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException  e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }
  
    int nextInt() {
      return Integer.parseInt(next());
    }
  
    long nextLong() {
      return Long.parseLong(next());
    }
  
    double nextDouble() {
      return Double.parseDouble(next());
    }
 
    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e)  {
        e.printStackTrace();
      }
      return str;
    }
  }
}