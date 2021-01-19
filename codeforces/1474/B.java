import java.io.*;
import java.util.*;
 
public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));
  
  static Set<Integer> primes = new TreeSet<Integer>();
  public static void main(String[] args) {
    primes.add(2);
    boolean[] isComposite = new boolean[30_000];
    for (int candidate = 3; candidate < 30_000; candidate += 2) {
      if (isComposite[candidate]) continue;
      else {
        primes.add(candidate);
        for (int i = candidate*candidate; i < 30_000; i += candidate) {
          isComposite[i] = true;
        }
      }
    }
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int diff = FR.nextInt();
      int prev = 1;
      int cand1 = 0;
      int cand2 = 0;
      for (var element : primes) {
        if (element - prev >= diff) {
          if (prev == 1) {
            cand1 = element;
          } else {
            cand2 = element;
            break;
          }
          prev = element;
        }
      }
      solution.append((long)cand1*cand2 + "\n");
    }

    // for (int i = 1; i <= 28; i += 1) {
    //   System.out.print("NUM : " + i + " Div: ");
    //   for (int j = 1; j < i; j += 1) {
    //     if (i%j == 0) System.out.print(j + " ");
    //   }
    //   System.out.println();
    // }
    
		PW.print(solution.toString());
    PW.close();
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