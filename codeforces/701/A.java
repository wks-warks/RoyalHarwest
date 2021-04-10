import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    int cardCount = scanner.nextInt();
    List<Pair> cardValues = new ArrayList<Pair>(cardCount);
    for (int c = 0; c < cardCount; c += 1) {
      cardValues.add(new Pair(scanner.nextInt(), c+1));
    }

    printDistribution(cardValues);
  }

  static void printDistribution(List<Pair> cardValues) {
    Collections.sort(cardValues, new Pair());
    int end = cardValues.size() - 1;
    for (int i = 0; i < end - i; i += 1) {
      System.out.println(cardValues.get(i).getIndex() + " " + cardValues.get(end - i).getIndex());
    }
  }
}

class Pair implements Comparator<Pair> {
  private int value, index;
  
  public Pair() {

  }
  public Pair(int value, int index) {
    this.value = value;
    this.index = index;
  }

  public int getValue() {
    return value;
  }
  public int getIndex() {
    return index;
  }

  @Override
  public int compare(Pair first, Pair second) {
    if (first.getValue() > second.getValue()) {
      return 1;
    } else if (first.getValue() == second.getValue()) {
      return 0;
    } else {
      return -1;
    }
  }
}