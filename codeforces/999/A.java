import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int problemCount = scanner.nextInt();
    List<Integer> problemDifficulties = new ArrayList<Integer>(problemCount);

    int solvingSkill = scanner.nextInt();
    for (int p = 0; p < problemCount; p += 1) {
      problemDifficulties.add(scanner.nextInt());
    }

    int solved = countSolved(problemDifficulties, solvingSkill);
    System.out.println(solved);
  }

  static int countSolved(List<Integer> problemDifficulties, int solvingSkill) {
    int leftSolved = 0;
    for (var difficulty : problemDifficulties) {
      if (difficulty > solvingSkill) {
        break;
      }
      leftSolved += 1;
    }

    int rightSolved = 0;
    for (int i = problemDifficulties.size() - 1; i >= 0; i -= 1) {
      if (problemDifficulties.get(i) > solvingSkill) {
        break;
      }
      rightSolved += 1;
    }
    return Math.min(problemDifficulties.size(), leftSolved + rightSolved);
  }
}