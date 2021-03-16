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
      int components = FR.nextInt();
      List<Integer> miners = new ArrayList<Integer>(components);
      List<Integer> mines = new ArrayList<Integer>(components);

      int coordinates = components << 1;
      for (int c = 0; c < coordinates; c += 1) {
        int x = FR.nextInt();
        int y = FR.nextInt();
        if (x == 0) {
          miners.add(y);
        } else {
          mines.add(x);
        }
      }

      Collections.sort(miners);
      Collections.sort(mines);

      double minimumEnergy = computeEnergy(miners, mines);
      solution.append(minimumEnergy + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static double computeEnergy(List<Integer> miners, List<Integer> mines) {
    int minerRight = findRight(miners);
    int minerLeft = minerRight - 1;
    int mineRight = findRight(mines);
    int mineLeft = mineRight - 1;

    double totalEnergy = 0.0;

    while (minerLeft >= 0 && minerRight < miners.size()) {
      int leftPos = Math.abs(miners.get(minerLeft));
      int rightPos = Math.abs(miners.get(minerRight));
      int minerPos = Math.min(leftPos, rightPos);
      
      if (leftPos < rightPos) {
        minerLeft -= 1;
      } else {
        minerRight += 1;
      }

      int leftMine = mineLeft >= 0 ? Math.abs(mines.get(mineLeft)) : Integer.MAX_VALUE;
      int rightMine = mineRight < mines.size() ? Math.abs(mines.get(mineRight)) : Integer.MAX_VALUE;
      int minePos = Math.min(leftMine, rightMine);

      if (leftMine < rightMine) {
        mineLeft -= 1;
      } else {
        mineRight += 1;
      }

      totalEnergy += Math.sqrt((long) minerPos * minerPos + (long) minePos * minePos);
    }

    while (minerLeft >= 0) {
      int minerPos = Math.abs(miners.get(minerLeft--));

      int leftMine = mineLeft >= 0 ? Math.abs(mines.get(mineLeft)) : Integer.MAX_VALUE;
      int rightMine = mineRight < mines.size() ? Math.abs(mines.get(mineRight)) : Integer.MAX_VALUE;
      int minePos = Math.min(leftMine, rightMine);

      if (leftMine < rightMine) {
        mineLeft -= 1;
      } else {
        mineRight += 1;
      }

      totalEnergy += Math.sqrt((long) minerPos * minerPos + (long) minePos * minePos);
    }

    while (minerRight < miners.size()) {
      int minerPos = Math.abs(miners.get(minerRight++));

      int leftMine = mineLeft >= 0 ? Math.abs(mines.get(mineLeft)) : Integer.MAX_VALUE;
      int rightMine = mineRight < mines.size() ? Math.abs(mines.get(mineRight)) : Integer.MAX_VALUE;
      int minePos = Math.min(leftMine, rightMine);

      if (leftMine < rightMine) {
        mineLeft -= 1;
      } else {
        mineRight += 1;
      }

      totalEnergy += Math.sqrt((long) minerPos * minerPos + (long) minePos * minePos);
    }

    return totalEnergy;
  }

  static int findRight(List<Integer> list) {
    int positiveIdx = list.size();
    int left = 0;
    int right = list.size() - 1;
    
    while (left <= right) {
      int mid = (left + right) >> 1;
      int value = list.get(mid);

      if (value > 0) {
        positiveIdx = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return positiveIdx;
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