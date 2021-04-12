// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    setRelevantLists();

    StringBuilder solution = new StringBuilder();
    int tests = 1;
    tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int firstSize = FR.nextInt();
      int secondSize = FR.nextInt();
      int gcdSize = FR.nextInt();

      int firstNum = getFirstNum(gcdSize, firstSize);
      int secondNum = getSecondNum(gcdSize, secondSize);

      solution.append(firstNum + " " + secondNum + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getFirstNum(int gcdSize, int firstSize) {
    int gcd = bases.get(gcdSize - 1);
    int firstNum = gcd * firstPrimes.get(firstSize - gcdSize);
    return firstNum;
  }

  static int getSecondNum(int gcdSize, int secondSize) {
    int gcd = bases.get(gcdSize - 1);
    int secondNum = gcd * secondPrimes.get(secondSize - gcdSize);
    return secondNum;
  }

  static List<Integer> bases;
  static List<Integer> firstPrimes;
  static List<Integer> secondPrimes;
  static void setRelevantLists() {
    bases = new ArrayList<Integer>(9);
    bases.add(1);
    bases.add(10);
    bases.add(100);
    bases.add(1000);
    bases.add(10000);
    bases.add(100000);
    bases.add(1000000);
    bases.add(10000000);
    bases.add(100000000);

    firstPrimes = new ArrayList<Integer>(9);
    firstPrimes.add(7);
    firstPrimes.add(17);
    firstPrimes.add(107);
    firstPrimes.add(1009);
    firstPrimes.add(16127);
    firstPrimes.add(933199);
    firstPrimes.add(1003001);
    firstPrimes.add(16785407);
    firstPrimes.add(370248451);

    secondPrimes = new ArrayList<Integer>(9);
    secondPrimes.add(3);
    secondPrimes.add(13);
    secondPrimes.add(103);
    secondPrimes.add(1013);
    secondPrimes.add(11939);
    secondPrimes.add(919393);
    secondPrimes.add(1008001);
    secondPrimes.add(24036583);
    secondPrimes.add(777767777);
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