import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tests = Integer.parseInt(br.readLine());

    StringBuilder output = new StringBuilder();
    for (int t = 0; t < tests; t += 1) {
      String input = br.readLine();
      boolean canSort = checkSortability(input);
      output.append(canSort ? "Yes\n" : "No\n");
    }

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(output.toString());
    pw.close();
  }

  static boolean checkSortability(String input) {
    boolean found1pair = false;
    
    char prev = input.charAt(0);
    for (int i = 1; i < input.length(); i += 1) {
      char pres = input.charAt(i);
      
      if (pres == prev) {
        if (pres == '0' && found1pair) {
          return false;
        } else if (pres == '1') {
          found1pair = true;
        }
      }

      prev = pres;
    }

    return true;
  }
}