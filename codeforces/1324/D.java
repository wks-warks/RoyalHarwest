// Author : RegalBeast

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
      int topics = FR.nextInt();
      int[] teacherInterests = new int[topics];
      for (int tpc = 0; tpc < topics; tpc++) {
        teacherInterests[tpc] = FR.nextInt();
      }
      int[] studentInterests = new int[topics];
      for (int tpc = 0; tpc < topics; tpc++) {
        studentInterests[tpc] = FR.nextInt();
      }

      long goodPairs = getGoodPairs(teacherInterests, studentInterests);
      solution.append(goodPairs + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static long getGoodPairs(int[] teacherInterests, int[] studentInterests) {
    int[] differences = new int[teacherInterests.length];
    for (int t = 0; t < teacherInterests.length; t++) {
      differences[t] = teacherInterests[t] - studentInterests[t];
    }
    SortAndSearch.sort(differences);

    long goodPairs = 0;
    for (int i = 0; i < differences.length; i++) {
      int compliments = SortAndSearch.searchCompliments(i, differences);
      goodPairs += compliments;
    }
    return goodPairs;
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


class SortAndSearch {
  static void sort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      int r = i + (int) (Math.random() * (arr.length - i));
      int temp = arr[i];
      arr[i] = arr[r];
      arr[r] = temp;
    }
    Arrays.sort(arr);
  }

  static int searchCompliments(int idx, int[] arr) {
    int ansIdx = arr.length;

    int left = 0;
    int right = arr.length-1;
    while (left <= right) {
      int mid = (left + right) >> 1;
      if (arr[mid] + arr[idx] > 0) {
        ansIdx = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    ansIdx = Math.max(ansIdx, idx + 1);
    return (arr.length - ansIdx - (ansIdx <= idx ? 1 : 0));
  }
}