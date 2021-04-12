import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tests = Integer.parseInt(br.readLine());

    StringBuilder output = new StringBuilder();
    while (tests-->0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int mountains = Integer.parseInt(st.nextToken());
      int doorSize= Integer.parseInt(st.nextToken());

      st = new StringTokenizer(br.readLine());
      List<Integer> mountainHeights = new ArrayList<Integer>(mountains);
      for (int m = 0; m < mountains; m += 1) {
        mountainHeights.add(Integer.parseInt(st.nextToken()));
      }

      Pair solution = computeMaxPartCount(mountainHeights, doorSize);
      output.append(solution + "\n");
    }

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(output.toString());
    pw.close();
  }

  static Pair computeMaxPartCount(List<Integer> mountainHeights, int doorSize) {
    /*
      Method 1: Build a prefixSumList of peaks and query that
      Method 2: Build a list of peaks and query that
    */

    List<Integer> peaks = new ArrayList<Integer>();

    for (int m = 1; m < mountainHeights.size() - 1; m += 1) {
      if (isPeak(mountainHeights, m)) {
        peaks.add(m);
      }
    }

    int count = 0;
    int start = 1;

    int left, right = doorSize-1;
    int leftPeak = 0;
    int rightPeak = findRightPeak(peaks, right);

    for (left = 0; left + doorSize - 1 < mountainHeights.size(); left += 1, right += 1) {
      leftPeak = adjustLeftPeak(left, leftPeak, peaks);
      rightPeak = adjustRightPeak(right, rightPeak, peaks);
      if (Math.min(leftPeak, rightPeak) < 0 || Math.max(leftPeak, rightPeak) >= peaks.size()) {
        continue;
      }

      int tempCount = rightPeak - leftPeak + 1;
      if (tempCount > count) {
        count = tempCount;
        start = left + 1;
      }
    }

    return new Pair(count + 1, start);
  }

  static int adjustLeftPeak(int left, int leftPeak, List<Integer> peaks) {
    return leftPeak == peaks.size() ? leftPeak : peaks.get(leftPeak) > left ? leftPeak : leftPeak + 1;
  }

  static int adjustRightPeak(int right, int rightPeak, List<Integer> peaks) {
    return rightPeak < peaks.size() - 1 ? peaks.get(rightPeak + 1) < right ? rightPeak + 1 : rightPeak : rightPeak;
  }

  static int findRightPeak(List<Integer> peaks, int rightBound) {
    int idx = -1;
    int left, right;
    left = 0;
    right = peaks.size() - 1;

    while (left <= right) {
      int mid = (left + right) >> 1;
      
      if (peaks.get(mid) < rightBound) {
        idx = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return idx;
  }

  static boolean isPeak(List<Integer> mountainHeights, int idx) {
    return mountainHeights.get(idx) > mountainHeights.get(idx - 1) && mountainHeights.get(idx) > mountainHeights.get(idx + 1);
  }
}

class Pair {
  private int count, start;

  public Pair(int count, int start) {
    this.count = count;
    this.start = start;
  }

  public int getCount() {
    return count;
  }
  public int getStart() {
    return start;
  }

  @Override
  public String toString() {
    return String.format("%d %d", count, start);
  }
}