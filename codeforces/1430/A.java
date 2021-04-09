import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    RoomCount.initiateMap();
    while (tests-->0) {
      int windows = scanner.nextInt();
      RoomCount solution = RoomCount.getSolution(windows);
      System.out.println(solution.toString());
    }
  }
}

class RoomCount {
  private int threeBedroom, fiveBedroom, sevenBedroom;
  
  public RoomCount() {
    threeBedroom = -1;
    fiveBedroom = -1;
    sevenBedroom = -1;
  }

  public RoomCount(int threeBedroom, int fiveBedroom, int sevenBedroom) {
    this.threeBedroom = threeBedroom;
    this.fiveBedroom = fiveBedroom;
    this.sevenBedroom = sevenBedroom;
  }

  public int getThreeCount() {
    return threeBedroom;
  }
  public int getFiveCount() {
    return fiveBedroom;
  }
  public int getSevenCount() {
    return sevenBedroom;
  }

  public boolean isPossible() {
    return threeBedroom >= 0;
  }

  @Override
  public String toString() {
    if (!isPossible()) {
      return "-1";
    } else {
      return String.format("%d %d %d", threeBedroom, fiveBedroom, sevenBedroom);
    }
  }

  static Map<Integer, RoomCount> possibleSolutions;
  static void initiateMap() {
    possibleSolutions = new HashMap<Integer, RoomCount>();
    possibleSolutions.put(0, new RoomCount(0, 0, 0));
    // System.out.println(possibleSolutions.get(0).isPossible());
  }

  static RoomCount getSolution(int windows) {
    // System.out.println(windows);
    if (windows < 0) {
      return new RoomCount();
    }

    RoomCount solution = possibleSolutions.getOrDefault(windows, null);
    // if (windows == 0) {
      // System.out.println(solution.toString());
    // }
    if (solution == null) {
      RoomCount minus3 = getSolution(windows - 3);
      if (minus3.isPossible()) {
        solution = new RoomCount(minus3.getThreeCount() + 1, minus3.getFiveCount(), minus3.getSevenCount());
      }
    }
    
    if (solution == null) {
      RoomCount minus5 = getSolution(windows - 5);
      if (minus5.isPossible()) {
        solution = new RoomCount(minus5.getThreeCount(), minus5.getFiveCount() + 1, minus5.getSevenCount());
      }
    }

    if (solution == null) {
      RoomCount minus7 = getSolution(windows - 7);
      if (minus7.isPossible()) {
        solution = new RoomCount(minus7.getThreeCount(), minus7.getFiveCount(), minus7.getSevenCount() + 1);
      }
    }

    if (solution == null) {
      solution = new RoomCount();
    }
    possibleSolutions.putIfAbsent(windows, solution);

    return solution;
  }
}