import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder solution = new StringBuilder();
    int tests = Integer.parseInt(br.readLine());

    StringTokenizer st;
    for (int t = 0; t < tests; t += 1) {
      int treeCount = Integer.parseInt(br.readLine());
      List<Integer> treeHeights = new ArrayList<Integer>(treeCount);
      
      st = new StringTokenizer(br.readLine());
      for (int tree = 0; tree < treeCount; tree += 1) {
        treeHeights.add(Integer.parseInt(st.nextToken()));
      }

      int differentAreas = countDifferentAreas(treeHeights);
      solution.append(differentAreas + "\n");
    }

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(solution.toString());
    pw.close();
  }

  static int countDifferentAreas(List<Integer> treeHeights) {
    Set<Integer> heights = new HashSet<Integer>();

    for (int first = 0; first < treeHeights.size(); first += 1) {
      for (int second = 0; second < first; second += 1) {
        int firstHeight = treeHeights.get(first);
        int secondHeight = treeHeights.get(second);
        heights.add(firstHeight - secondHeight);
      }
    }

    return heights.size();
  }
}