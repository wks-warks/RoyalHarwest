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
      int permutationSize = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());

      List<Integer> permutation = new ArrayList<Integer>(permutationSize);
      for (int e = 0; e < permutationSize; e += 1) {
        permutation.add(Integer.parseInt(st.nextToken()));
      }

      setDepths(permutation);
      for (var depth : depths) {
        output.append(depth + " ");
      }
      output.append("\n");
    }

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(output.toString());
    pw.close();
  }

  static List<Integer> depths;
  static void setDepths(List<Integer> permutation) {
    depths = new ArrayList<Integer>(Collections.nCopies(permutation.size(), -1));
    setDepths(permutation, 0, permutation.size() - 1, 0);
  }

  static void setDepths(List<Integer> permutation,  int left, int right, int assign) {
    int maxIdx = getMaxIdx(permutation, left, right);
    depths.set(maxIdx, assign);
    
    if (left != maxIdx) {
      setDepths(permutation, left, maxIdx - 1, assign + 1);
    }

    if (right != maxIdx) {
      setDepths(permutation, maxIdx + 1, right, assign + 1);
    }
  }

  static int getMaxIdx(List<Integer> permutation, int left, int right) {
    int maxIdx = left;

    for (int i = left + 1; i <= right; i += 1) {
      int value = permutation.get(i);
      if (value > permutation.get(maxIdx)) {
        maxIdx = i;
      }
    }

    return maxIdx;
  }
}