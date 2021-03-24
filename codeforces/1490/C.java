import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;

public class Main {
  public static void main(String[] args) throws IOException {
    precomputeCubes(1, 10_000);

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tests = Integer.parseInt(br.readLine());

    StringBuilder output = new StringBuilder();
    for (int t = 0; t < tests; t += 1) {
      long desiredSum = Long.parseLong(br.readLine());
      boolean canRepresent = isRepresentable(desiredSum);
      output.append(canRepresent ? "Yes\n" : "No\n");
    }

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(output.toString());
    pw.close();
  }

  static Set<Long> cubes;
  static void precomputeCubes(int lowerBound, int upperBound) {
    cubes = new HashSet<Long>();
    
    for (int num = lowerBound; num <= upperBound; num += 1) {
      long cube = (long) num * num * num;
      cubes.add(cube);
    }
  }

  static boolean isRepresentable(long sum) {
    for (var cube : cubes) {
      long diff = sum - cube;

      if (cubes.contains(diff)) {
        return true;
      }
    }

    return false;
  }
}