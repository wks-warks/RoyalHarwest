// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();

  static List<Integer> permutation;
  public static void main(String[] args) {
    int tests = 1;
    for (int t = 0; t < tests; ++t) {
      int permutationSize = FR.nextInt();
      permutation = new ArrayList<Integer>(Collections.nCopies(permutationSize+2, -1));
      permutation.set(0, Integer.MAX_VALUE);
      permutation.set(permutation.size()-1, Integer.MAX_VALUE);
      interact();
    }
  }

  static void interact() {
    int left = 1;
    int right = permutation.size() - 1;
    while (left <= right) {
      int mid = (left + right) >> 1;
      int queryResult = minimaFound(mid);
      if (queryResult == 1) {
        System.out.println("! " + mid);
        System.out.flush();
        return;
      } else if (queryResult == 0) {
        right = mid -1 ;
      } else {
        left = mid + 1;
      }
    }
  }

  // Returns 1 if minima is found
  // else returns 0 leftValue < rightValue
  // else returns 2
  static int minimaFound(int idx) {
    int value = ask(idx);
    int leftValue = ask(idx-1);
    int rightValue = ask(idx+1);
    if (value < leftValue && value < rightValue) {
      return 1;
    } else if (leftValue < rightValue) {
      return 0;
    } else {
      return 2;
    }
  }

  static int ask(int idx) {
    if (permutation.get(idx) != -1) {
      return permutation.get(idx);
    }
    System.out.println("? " + idx);
    System.out.flush();
    permutation.set(idx, FR.nextInt());
    return permutation.get(idx);
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