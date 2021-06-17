import java.util.Scanner;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int letters = scanner.nextInt();
    int choose = scanner.nextInt();
    String deck = scanner.next();

    long coins = getCoins(deck, choose);
    System.out.println(coins);
  }

  static long getCoins(String deck, int choose) {
    int[] letterFreq = getLetterFreq(deck);
    Arrays.sort(letterFreq);

    long coins = 0;
    for (int c = 25; choose > 0; c--) {
      if (letterFreq[c] <= choose) {
        choose -= letterFreq[c];
        coins += (long) letterFreq[c] * letterFreq[c];
      } else {
        coins += (long) choose * choose;
        choose = 0;
      }
    }

    return coins;
  }

  static int[] getLetterFreq(String str) {
    int[] letterFreq = new int[26];
    for (var ch : str.toCharArray()) {
      letterFreq[ch - 'A']++;
    }
    return letterFreq;
  }
}