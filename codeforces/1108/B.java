import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int divisorCount = scanner.nextInt();

    PriorityQueue<Integer> divisors = new PriorityQueue<Integer>(Collections.reverseOrder());
    for (int d = 0; d < divisorCount; d += 1) {
      divisors.add(scanner.nextInt());
    }

    int secondNum = -1;
    int firstNum = divisors.poll();
    int largestDivisor = firstNum;
    
    while (divisors.size() > 0) {
      int divisor = divisors.poll();
      if (divisor == largestDivisor || firstNum % divisor > 0) {
        secondNum = divisor;
        break;
      }
      largestDivisor = divisor;
    }

    System.out.println(firstNum + " " + secondNum);
  }
}