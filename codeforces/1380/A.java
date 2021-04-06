import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class CF1380A {
  public static void main(String[] args) {
    StringBuilder output = new StringBuilder();
    Scanner scanner = new Scanner(System.in);
    int testCases = scanner.nextInt();

    for (int t = 0; t < testCases; t += 1) {
      int permutationSize = scanner.nextInt();
      List<Integer> permutation = new ArrayList<Integer>(permutationSize);

      for (int i = 0; i < permutationSize; i += 1) {
        permutation.add(scanner.nextInt());
      }

      Solution solution = getSolution(permutation);
      output.append(solution.toString());
    }
    System.out.print(output);
  }

  static Solution getSolution(List<Integer> permutation) {
    List<Pair> minPrefix = getPrefix("min", permutation);
    List<Pair> minSuffix = getSuffix("min", permutation);

    for (int i = 1; i < permutation.size()-1; i += 1) {
      if (minPrefix.get(i-1).value < permutation.get(i) && minSuffix.get(i+1).value < permutation.get(i)) {
        return new Solution(minPrefix.get(i-1).key, i, minSuffix.get(i+1).key);
      }
    }

    return new Solution();
  }

  static List<Pair> getPrefix(String type, List<Integer> permutation) {
    List<Pair> prefix = new ArrayList<Pair>(permutation.size());
    prefix.add(new Pair(0, permutation.get(0)));

    for (int i = 1; i < permutation.size(); i += 1) {
      Pair prev = prefix.get(prefix.size() - 1);
      int prevBest = prev.value;
      int presValue = permutation.get(i);

      prefix.add(presValue < prevBest ? new Pair(i, presValue) : prev);
    }

    return prefix;
  }

  static List<Pair> getSuffix(String type, List<Integer> permutation) {
    List<Pair> suffix = new ArrayList<Pair>(permutation.size());
    suffix.add(new Pair(permutation.size()-1, permutation.get(permutation.size()-1)));

    for (int i = permutation.size() - 2; i >= 0; i -= 1) {
      Pair prev = suffix.get(suffix.size() - 1);
      int prevBest = prev.value;
      int presValue = permutation.get(i);

      suffix.add(presValue < prevBest ? new Pair(i, presValue) : prev);
    }

    Collections.reverse(suffix);
    return suffix;
  }
}

class Pair {
  int key, value;

  public Pair() {

  }
  public Pair(int key, int value) {
    this.key = key;
    this.value = value;
  }
}

class Solution {
  boolean possible;
  int i, j, k;

  public Solution() {
    possible = false;
    i = j = k = -1;
  }
  public Solution(int i, int j, int k) {
    this.possible = true;
    this.i = i;
    this.j = j;
    this.k = k;
  }

  @Override
  public String toString() {
    String response;
  
    if (possible) {
      response = String.format("%s\n%d %d %d\n", "YES", i+1, j+1, k+1);
    } else {
      response = "NO\n";
    }
  
    return response;
  }
}