import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int lowerBound = Integer.parseInt(st.nextToken());
    int upperBound = Integer.parseInt(st.nextToken());
    int totalWeight = 1000 * Integer.parseInt(st.nextToken());

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.println(orangeCount(lowerBound, upperBound, totalWeight));
    pw.close();
  }

  static String orangeCount(int lowerBound, int upperBound, int totalWeight) {
    int maxCount = totalWeight / lowerBound;
    int minCount = LIG(totalWeight, upperBound);

    if (minCount > maxCount || (minCount== maxCount && maxCount * upperBound < totalWeight)) {
      return "UNSATISFIABLE";
    } else {
      return minCount + " " + maxCount;
    }
  }

  static int LIG(int numerator, int denominator) {
    return (numerator + denominator - 1) / denominator;
  }
}