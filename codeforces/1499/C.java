import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tests = Integer.parseInt(br.readLine());

    StringTokenizer st;
    StringBuilder output = new StringBuilder();
    for (int t = 0; t < tests; t += 1) {
      int n = Integer.parseInt(br.readLine());
      List<Integer> segmentCosts = new ArrayList<Integer>(n);

      st = new StringTokenizer(br.readLine());
      for (int e = 0; e < n; e += 1) {
        segmentCosts.add(Integer.parseInt(st.nextToken()));
      }

      long minCost = calculateCost(n, segmentCosts);
      output.append(minCost + "\n");
    }

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(output.toString());
    pw.close();
  }

  static long calculateCost(int n, List<Integer> segmentCosts) {
    long minCost = 200_000_000_000_000L;

    long prevCost = 0;
    int prevMin1 = 1_000_000_000;
    int prevMin2 = 1_000_000_000;

    for (int i = 1; i < segmentCosts.size(); i += 1) {
      int prevSegments = i-1;
      int last1 = segmentCosts.get(i-1);
      int last2 = segmentCosts.get(i);

      int prevIn1 = prevSegments >> 1;
      int prevIn2 = prevSegments - prevIn1;

      long candidate = (long)(n - prevIn1) * last1 + (long)(n - prevIn2) * last2 + prevCost;

      candidate = Math.min(candidate, last1 + last2 + (long)(n - prevIn1 - 1) * prevMin1 + (long)(n - prevIn2 - 1) * prevMin2 + prevCost);
      candidate = Math.min(candidate, last1 + (long)(n - prevIn1 - 1) * prevMin1 + (long)(n - prevIn2) * last2 + prevCost);
      candidate = Math.min(candidate, (long)(n - prevIn1) * last1 + (long)(n - prevIn2 - 1) * prevMin2 + last2 + prevCost);

      minCost = Math.min(minCost, candidate);

      prevMin1 = Math.min(prevMin1, last1);
      prevMin1 ^= prevMin2;
      prevMin2 ^= prevMin1;
      prevMin1 ^= prevMin2;

      prevCost += segmentCosts.get(i-1);
    }

    return minCost;
  }
}