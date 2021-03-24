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

    StringTokenizer st;
    StringBuilder output = new StringBuilder();
    for (int t = 0; t < tests; t += 1) {
      int listSize = Integer.parseInt(br.readLine());
      
      st = new StringTokenizer(br.readLine());
      List<Integer> list = new ArrayList<Integer>(listSize);
      for (int e = 0; e < listSize; e += 1) {
        list.add(Integer.parseInt(st.nextToken()));
      }

      int additionsNeeded = computeAdditionsNeeded(list);
      output.append(additionsNeeded + "\n");
    }

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(output.toString());
    pw.close();
  }

  static int computeAdditionsNeeded(List<Integer> list) {
    int additionsNeeded = 0;

    for (int e = 1; e < list.size(); e += 1) {
      additionsNeeded += addForPair(list.get(e), list.get(e-1));
    }

    return additionsNeeded;
  }

  static int addForPair(int num1, int num2) {
    int min = Math.min(num1, num2);
    int max = Math.max(num1, num2);

    int additions = 0;
    while ((min << 1) < max) {
      min <<= 1;
      additions += 1;
    }

    return additions;
  }
}