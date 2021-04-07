import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int sequenceLength = scanner.nextInt();

    int minDiff = calculateMinDiff(sequenceLength);
    System.out.println(minDiff);
  }

  static int calculateMinDiff(int sequenceLength) {
    long sum = (long) (sequenceLength) * (sequenceLength + 1) >> 1;
    return (int) (sum & 1);
  }
}