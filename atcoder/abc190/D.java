import java.io.*;
import java.util.*;
 
public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));
 
  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    // tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      long sum = FR.nextLong();
      int progressions = countProgressions(sum);
      solution.append(progressions + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int countProgressions(long sum) {
    long twiceSum = sum << 1;
    long root = sqrt(twiceSum);

    int count = 0;
    long firstFactor, secondFactor;
    for (long i = 1; i <= root; i += 1) {
      if (twiceSum % i == 0) {
        firstFactor = i;
        if (satisfactoryN(firstFactor, twiceSum)) count += 1;
        secondFactor = twiceSum / i;
        if (firstFactor != secondFactor && satisfactoryN(secondFactor, twiceSum)) count += 1;
      }
    }
    return count;
  }

  static boolean satisfactoryN(long n, long twiceSum) {
    long otherFactor = twiceSum / n;
    return isEven(otherFactor - n + 1);
  }

  static boolean isEven(long num) {
    return (num&1) == 0;
  }
  
  static long sqrt(long num) {
    long root = 1L;
    long lowerBound = 1L;
    long upperBound = 2_000_000L; // Setting this to num will cause long overflow and thus result in unexpected output
    while (lowerBound <= upperBound) {
      long mid = (lowerBound + upperBound) >> 1;
      if (mid * mid <= num) {
        root = mid;
        lowerBound = mid + 1;
      } else {
        upperBound = mid - 1;
      }
    }
    return root;
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