import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int k = scanner.nextInt();
    String str = scanner.next();

    String result = getResult(k, str);
    System.out.println(result);
  }

  static String getResult(int k, String str) {
    int[] charFreq = new int[26];
    for (var ch : str.toCharArray()) {
      charFreq[ch-'a']++;
    }

    for (int i = 0; i < 26; i++) {
      if (charFreq[i] % k != 0) {
        return "-1";
      }
    }
    
    int[] inEach = new int[26];
    for (int i = 0; i < 26; i++) {
      inEach[i] = charFreq[i] / k;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 26; i++) {
      for (int j = 0; j < inEach[i]; j++) {
        sb.append((char) ('a' + i));
      }
    }

    String eachOfK = sb.toString();
    for (int i = 1; i < k; i++) {
      sb.append(eachOfK);
    }
    return sb.toString();
  }
}