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

    StringBuilder output = new StringBuilder();    
    for (int t = 0; t < tests; t += 1) {
      int listSize = Integer.parseInt(br.readLine());
      List<Integer> list = new ArrayList<Integer>(listSize);

      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < listSize; i += 1) {
        list.add(Integer.parseInt(st.nextToken()));
      }

      boolean sortable = checkIfSortable(list);
      output.append(sortable ? "YES\n" : "NO\n");
    }

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(output.toString());
    pw.close();
  }

  static boolean checkIfSortable(List<Integer> list) {
    List<Integer> sortedList = new ArrayList<Integer>(list);
    Collections.sort(sortedList);
    int min = sortedList.get(0);

    for (int i = 0; i < list.size(); i += 1) {
      if (!list.get(i).equals(sortedList.get(i))) {
        if (GCD(list.get(i), min) != min) {
          return false;
        }
      }
    }

    return true;
  }

  static int GCD(int num1, int num2) {
    if (num1 == 0) {
      return num2;
    } else {
      return GCD(num2 % num1, num1);
    }
  }
}