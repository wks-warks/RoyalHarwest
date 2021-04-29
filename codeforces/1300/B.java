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
      int n = FR.nextInt();
      int[] studentSkills = new int[n<<1];
      for (int i = 0; i < studentSkills.length; i++) {
        studentSkills[i] = FR.nextInt();
      }

      int minDiff = getMinDiff(studentSkills);
      solution.append(minDiff + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getMinDiff(int[] studentSkills) {
    Sorter.sort(studentSkills);
    int mid = studentSkills.length >> 1;
    return studentSkills[mid] - studentSkills[mid-1];
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

class Sorter {
  static void sort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      int r = i + (int) (Math.random() * (arr.length - i));
      int temp = arr[i];
      arr[i] = arr[r];
      arr[r] = temp;
    }
    Arrays.sort(arr);
  }
}