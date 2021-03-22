import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = br.readLine();

    String rounded = getRounded(input);

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.println(rounded);
    pw.close();
  }

  static String getRounded(String inp) {
    int decimalIdx = getDecimalIdx(inp);
    return inp.substring(0, decimalIdx);
  }

  static int getDecimalIdx(String inp) {
    for (int i = 0; i < inp.length(); i += 1) {
      if (inp.charAt(i) == '.') {
        return i;
      }
    }

    return inp.length();
  }
}