import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      int sticksPerExchange = scanner.nextInt();
      int sticksPerCoal = scanner.nextInt();
      int torchesNeeded = scanner.nextInt();

      long exchangesNeeded = computeExchangesNeeded(sticksPerExchange, sticksPerCoal, torchesNeeded);
      System.out.println(exchangesNeeded);
    }
  }

  static long computeExchangesNeeded(int sticksPerExchange, int sticksPerCoal, int torchesNeeded) {
    long coalExchanges = torchesNeeded;
    long forCoal = (long) sticksPerCoal * coalExchanges;
    long forSticks = torchesNeeded;
    long stickExchanges = LIG(forCoal + forSticks - 1, sticksPerExchange - 1);
    
    return stickExchanges + coalExchanges;
  }

  static long LIG(long numerator, long denominator) {
    return (numerator + denominator - 1) / denominator;
  }
}