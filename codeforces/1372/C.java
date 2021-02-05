// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int permLen = FR.nextInt();
      List<Integer> permutation = new ArrayList<Integer>(permLen);
      for (int i = 0; i < permLen; i += 1) {
        permutation.add(FR.nextInt());
      }
      int movesToSort = getMovesToSort(permutation);
      solution.append(movesToSort + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getMovesToSort(List<Integer> permutation) {
    int len = permutation.size();
    int sortedTill = -1;
    for (int i = 0; i < len; i += 1) {
      if (permutation.get(i) == i + 1) {
        sortedTill = i;
      } else {
        break;
      }
    }

    int sortedFrom = len;
    for (int i = len-1; i >= 0; i -= 1) {
      if (permutation.get(i) == i + 1) {
        sortedFrom = i;
      } else {
        break;
      }
    }

    int midSorted = 0;
    for (int i = sortedTill + 1; i < sortedFrom; i += 1) {
      if (permutation.get(i) == i+1) {
        midSorted += 1;
      }
    }

    if (sortedTill == len-1) {
      return 0;
    } else if (midSorted == 0) {
      return 1;
    } else {
      return 2;
    }
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