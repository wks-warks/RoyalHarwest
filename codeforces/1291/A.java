import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tests = Integer.parseInt(br.readLine());

    StringBuilder sb = new StringBuilder();
    for (int t = 0; t < tests; t++) {
      br.readLine();
      String s = br.readLine();
      String ebneNumber = getEbneNumber(s);
      sb.append(ebneNumber + "\n"); 
    }

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(sb.toString());
    pw.close();
  }

  static String getEbneNumber(String s) {
    char firstOdd = '-';
    char secondOdd = '-';

    for (var digit : s.toCharArray()) {
      if (isOdd(digit)) {
        if (firstOdd == '-') {
          firstOdd = digit;
        } else {
          secondOdd = digit;
          break;
        }
      }
    }

    return secondOdd != '-' ? String.format("%c%c", firstOdd, secondOdd) : "-1";
  }

  static boolean isEven(int num) {
    return (num & 1) == 0;
  }
  static boolean isOdd(int num) {
    return !isEven(num);
  }
}