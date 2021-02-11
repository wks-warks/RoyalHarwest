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
      int studentCount = FR.nextInt();
      int checkPointCount = FR.nextInt();
      int[][] studentLocations = new int[studentCount][2];
      for (int i = 0; i < studentCount; i += 1) {
        studentLocations[i][0] = FR.nextInt();
        studentLocations[i][1] = FR.nextInt();
      }
      int[][] checkPointLocations = new int[checkPointCount][2];
      for (int i = 0; i < checkPointCount; i += 1) {
        checkPointLocations[i][0] = FR.nextInt();
        checkPointLocations[i][1] = FR.nextInt();
      }
      for (int i = 0; i < studentCount; i += 1) {
        int checkPointIdx = getCheckPoint(studentLocations[i], checkPointLocations);
        solution.append(checkPointIdx + "\n");
      }
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getCheckPoint(int[] studentLocation, int[][] checkPointLocations) {
    int leastDistance = Integer.MAX_VALUE;
    int checkPointIdx = -1;
    int i = 0;
    for (var pointLocation : checkPointLocations) {
      int distance = Math.abs(studentLocation[0] - pointLocation[0]) + Math.abs(studentLocation[1] - pointLocation[1]);
      if (distance < leastDistance) {
        leastDistance = distance;
        checkPointIdx = i + 1;
      }
      i += 1;
    }
    return checkPointIdx;
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