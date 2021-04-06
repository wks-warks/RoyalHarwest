import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      long arrivalTime = scanner.nextLong();
      long periodA = scanner.nextLong();
      long periodB = scanner.nextLong();
      long periodC = scanner.nextLong();

      long wait = calculateWait(arrivalTime, periodA, periodB, periodC);
      System.out.println(wait);
    }
  }

  static long calculateWait(long arrivalTime, long periodA, long periodB, long periodC) {
    long waitA = LIG(arrivalTime, periodA) * periodA - arrivalTime;
    long waitB = LIG(arrivalTime, periodB) * periodB - arrivalTime;
    long waitC = LIG(arrivalTime, periodC) * periodC - arrivalTime;
    
    return Math.min(waitA, Math.min(waitB, waitC));
  }

  static long LIG(long numerator, long denominator) {
    return (numerator + denominator - 1) / denominator;
  }
}