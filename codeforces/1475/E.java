import java.io.*;
import java.util.*;
 
public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  static long[] fac;
  static int mod = 1_000_000_007;
  public static void main(String[] args) {
    setFactorials();
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int bloggerCount = FR.nextInt();
      int contracts = FR.nextInt();
      List<Integer> followerCounts = new ArrayList<Integer>(bloggerCount);
      for (int i = 0; i < bloggerCount; i += 1) {
        followerCounts.add(FR.nextInt());
      }
      Collections.sort(followerCounts);
      int value = followerCounts.get(bloggerCount-contracts);
      int firstIdx = Search.findFirstIdx(followerCounts, value);
      int lastIdx = Search.findLastIdx(followerCounts, value);
      int lastCount = lastIdx-firstIdx+1;
      int needed = lastIdx - (bloggerCount-contracts) + 1;
      solution.append(getNCR(lastCount, needed) + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static void setFactorials() {
    fac = new long[1001];
    fac[0] = 1;
    for (int i = 1; i < 1001; i += 1) {
      fac[i] = (fac[i-1] * i) % mod;
    }
  }

  static long getNCR(int n, int r) {
    return GFG.nCrModPFermat(n, r, mod);
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

class Search {
  
  // Assuming an ascending ordered list, finds first (smallest idx) occurrence of requested value
  static int findFirstIdx(List<Integer> list, Integer value) {
    int left, right, idx, mid, comparison;
    left = 0;
    right = list.size()-1;
    idx = -1;
    while (left <= right) {
      mid = (left+right) / 2;
      comparison = list.get(mid).compareTo(value);
      if (comparison == 0) {
        idx = mid;
      }
      if (comparison >= 0) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return idx;
  }
  static int findFirstIdx(List<Long> list, Long value) {
    int left, right, idx, mid, comparison;
    left = 0;
    right = list.size()-1;
    idx = -1;
    while (left <= right) {
      mid = (left+right) / 2;
      comparison = list.get(mid).compareTo(value);
      if (comparison == 0) {
        idx = mid;
      }
      if (comparison >= 0) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return idx;
  }
  static int findFirstIdx(List<Double> list, Double value) {
    int left, right, idx, mid, comparison;
    left = 0;
    right = list.size()-1;
    idx = -1;
    while (left <= right) {
      mid = (left+right) / 2;
      comparison = list.get(mid).compareTo(value);
      if (comparison == 0) {
        idx = mid;
      }
      if (comparison >= 0) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return idx;
  }

  // Assuming an ascending ordered list, finds last (largest idx) occurrence of requested value
  static int findLastIdx(List<Integer> list, Integer value) {
    int left, right, idx, mid, comparison;
    left = 0;
    right = list.size()-1;
    idx = -1;
    while (left <= right) {
      mid = (left+right) / 2;
      comparison = list.get(mid).compareTo(value);
      if (comparison == 0) {
        idx = mid;
      }
      if (comparison <= 0) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return idx;
  }
  static int findLastIdx(List<Long> list, Long value) {
    int left, right, idx, mid, comparison;
    left = 0;
    right = list.size()-1;
    idx = -1;
    while (left <= right) {
      mid = (left+right) / 2;
      comparison = list.get(mid).compareTo(value);
      if (comparison == 0) {
        idx = mid;
      }
      if (comparison <= 0) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return idx;
  }
  static int findLastIdx(List<Double> list, Double value) {
    int left, right, idx, mid, comparison;
    left = 0;
    right = list.size()-1;
    idx = -1;
    while (left <= right) {
      mid = (left+right) / 2;
      comparison = list.get(mid).compareTo(value);
      if (comparison == 0) {
        idx = mid;
      }
      if (comparison <= 0) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return idx;
  }
}

class GFG {
    static long power(long x, long y, long p)
    {
        // Initialize result
        long res = 1;
        // Update x if it is more than or
        // equal to p
        x = x % p;
        while (y > 0) {
            // If y is odd, multiply x
            // with result
            if (y % 2 == 1)
                res = (res * x) % p;
            // y must be even now
            y = y >> 1; // y = y/2
            x = (x * x) % p;
        }
        return res;
    }
    // Returns n^(-1) mod p
    static long modInverse(long n, long p)
    {
        return power(n, p - 2, p);
    }
    // Returns nCr % p using Fermat's
    // little theorem.
    static long nCrModPFermat(long n, long r,
                             long p)
    {
          if (n<r) 
              return 0;
      // Base case
        if (r == 0)
            return 1;
        // Fill factorial array so that we
        // can find all factorial of r, n
        // and n-r
        return (Main.fac[(int)n] * modInverse(Main.fac[(int)r], p)
                % p * modInverse(Main.fac[(int)(n - r)], p)
                % p)
            % p;
    }
}