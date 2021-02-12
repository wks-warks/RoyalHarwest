// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    // tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int itemCount = FR.nextInt();
      int selectMin = FR.nextInt();
      int selectMax = FR.nextInt();
      TreeMap<Long, Integer> frequency = new TreeMap<Long, Integer>(Collections.reverseOrder());
      for (int i = 0; i < itemCount; i += 1) {
        long value = FR.nextLong();
        if (!frequency.containsKey(value)) {
          frequency.put(value, 0);
        }
        frequency.put(value, frequency.get(value) + 1);
      }
      Pair<Double, BigInteger> meanWays = getMeanWays(frequency, selectMin, selectMax);
      solution.append(meanWays.first + "\n" + meanWays.second + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static Pair<Double, BigInteger> getMeanWays(TreeMap<Long, Integer> frequency, int selectMin, int selectMax) {
    long sum = 0;
    BigInteger ways = BigInteger.ZERO;
    int selected = 0;
    for (var entry : frequency.entrySet()) {
      long num = entry.getKey();
      int additions = entry.getValue();
      if (selected + additions < selectMin) {
        selected += additions;
        sum += additions * num;
      } else if (sum == 0) {
        sum += num * (selectMin - selected);
        for (int chosen = selectMin - selected; chosen <= Math.min(selectMax - selected, additions); chosen += 1) {
          ways = ways.add(choose(additions, chosen));
        }
        selected = selectMin;
        break;
      } else {
        int toAdd = selectMin-selected;
        sum += toAdd * num;
        selected += toAdd;
        ways = choose(additions, toAdd);
        break;
      }
    }
    Pair<Double, BigInteger> meanWays = new Pair<Double, BigInteger>();
    meanWays.first = (double) sum / selected;
    meanWays.second = ways;
    return meanWays;
  }

  static BigInteger choose(int n, int r) {
    if (r > n) {
      return BigInteger.ZERO;
    }
    BigInteger val = BigInteger.ONE;
    for (int i = n - r + 1; i <= n; i += 1) {
      val = val.multiply(BigInteger.valueOf(i));
    }
    for (int i = 2; i <= r; i += 1) {
      val = val.divide(BigInteger.valueOf(i));
    }
    return val;
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

class Pair<T1, T2> {
  T1 first;
  T2 second;
}