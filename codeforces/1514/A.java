import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tests = Integer.parseInt(br.readLine());

    StringBuilder sb = new StringBuilder();
    for (int t = 0; t < tests; t++) {
      int arrLen = Integer.parseInt(br.readLine());
      int[] arr = new int[arrLen];

      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < arrLen; i++) {
        arr[i] = Integer.parseInt(st.nextToken());
      }

      boolean perfectlyImperfect = isPerfectlyImperfect(arr);
      sb.append(perfectlyImperfect ? "YES\n" : "NO\n");
    }

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(sb.toString());
    pw.close();
  }

  static boolean isPerfectlyImperfect(int[] arr) {
    for (var num : arr) {
      if (!isSquare(num)) {
        return true;
      }
    }

    return false;
  }

  static boolean isSquare(int num) {
    int root = (int) Math.sqrt(num);
    return root * root == num;
  }
}