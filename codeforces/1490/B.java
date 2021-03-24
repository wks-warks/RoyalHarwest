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
      int listSize = Integer.parseInt(br.readLine());
      
      st = new StringTokenizer(br.readLine());
      List<Integer> list = new ArrayList<Integer>(listSize);
      for (int e = 0; e < listSize; e += 1) {
        list.add(Integer.parseInt(st.nextToken()));
      }

      int operationsNeeded = computeOperationsNeeded(list);
      output.append(operationsNeeded + "\n");
    }

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(output.toString());
    pw.close();
  }

  static int computeOperationsNeeded(List<Integer> list) {
    int desiredFreq = list.size() / 3;

    List<Integer> frequency = new ArrayList<Integer>(Collections.nCopies(3, 0));
    for (var element : list) {
      int mod = element % 3;
      frequency.set(mod, frequency.get(mod) + 1);
    }


    return computeOperationsNeeded(frequency, desiredFreq);
  }

  static int computeOperationsNeeded(List<Integer> frequency, int desiredFreq) {
    for (int i = 0; i < frequency.size(); i += 1) {
      int freq = frequency.get(i);
      if (freq > desiredFreq) {
        int next = (i + 1) % 3;
        int diff = freq - desiredFreq;

        frequency.set(next, frequency.get(next) + diff);
        frequency.set(i, desiredFreq);

        return diff + computeOperationsNeeded(frequency, desiredFreq);
      }
    }

    return 0;
  }
}