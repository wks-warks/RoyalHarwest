import java.util.Scanner;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int friends = scanner.nextInt();
    int differenceLimit = scanner.nextInt();
    
    int[][] friendsData = new int[friends][2];
    for (int f = 0; f < friends; f++) {
      friendsData[f][0] = scanner.nextInt();
      friendsData[f][1] = scanner.nextInt();
    }

    long maxFriendshipSum = getFriendshipSum(friendsData, differenceLimit);
    System.out.println(maxFriendshipSum);
  }

  static long getFriendshipSum(int[][] friendsData, int differenceLimit) {
    Arrays.sort(friendsData, (first, second) -> Integer.compare(first[0], second[0]));
    
    long maxFriendshipSum = 0;
    long friendshipSum = 0;
    int terminalIdx = 0;
    for (int f = 0; f < friendsData.length; f++) {
      while (friendsData[f][0] - friendsData[terminalIdx][0] >= differenceLimit) {
        friendshipSum -= friendsData[terminalIdx][1];
        terminalIdx++;
      }

      friendshipSum += friendsData[f][1];
      maxFriendshipSum = Math.max(maxFriendshipSum, friendshipSum);
    }

    return maxFriendshipSum;
  }
}