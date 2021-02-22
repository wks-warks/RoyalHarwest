import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int questions = scanner.nextInt();
    List<Integer> scores = new ArrayList<Integer>(questions);
    for (int q = 0; q < questions; q += 1) {
      scores.add(scanner.nextInt());
    }
    int maxGrade = getMaxGrade(scores);
    System.out.println(maxGrade);
  }

  static int getMaxGrade(List<Integer> scores) {
    int sum = 0;
    PriorityQueue<Integer> notMultipleOf10 = new PriorityQueue<Integer>();
    for (var score : scores) {
      if (score % 10 != 0) {
        notMultipleOf10.add(score);
      }
      sum += score;
    }

    if (sum % 10 != 0) {
      return sum;
    } else {
      if (notMultipleOf10.size() > 0) {
        return sum - notMultipleOf10.poll();
      } else {
        return 0;
      }
    }
  }
}