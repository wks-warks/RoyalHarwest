import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int stringCount = Integer.parseInt(st.nextToken());
    int length = Integer.parseInt(st.nextToken());

    Set<String> existingStrings = new HashSet<String>();
    List<String> palindromeList = new ArrayList<String>();
    for (int c = 0; c < stringCount; c++) {
      StringBuilder sb = new StringBuilder(br.readLine());
      String s = sb.toString();
      String reverse = sb.reverse().toString();

      if (existingStrings.contains(reverse)) {
        palindromeList.add(s);
        existingStrings.remove(reverse);
      } else {
        existingStrings.add(s);
      }
    }

    String palindromeStr = getPalindromeStr(palindromeList, existingStrings);
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.println(palindromeStr.length());
    pw.println(palindromeStr);
    pw.close();
  }

  static String getPalindromeStr(List<String> palindromeList, Set<String> existingStrings) {
    StringBuilder palindromeSB = new StringBuilder();
    for (var s : palindromeList) {
      palindromeSB.append(s);
    }

    for (var s : existingStrings) {
      if (isPalindrome(s)) {
        palindromeSB.append(s);
        break;
      }
    }

    Collections.reverse(palindromeList);
    for (var s : palindromeList) {
      palindromeSB.append(new StringBuilder(s).reverse().toString());
    }

    return palindromeSB.toString();
  }

  static boolean isPalindrome(String s) {
    int start = 0;
    int end = s.length() - 1;

    while (start < end) {
      if (s.charAt(start) != s.charAt(end)) {
        return false;
      } else {
        start++;
        end--;
      }
    }

    return true;
  }
}