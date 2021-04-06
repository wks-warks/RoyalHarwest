// // import java.util.Scanner;
// // import java.util.Deque;
// // import java.util.ArrayDeque;

// // public class Main {
// //   public static void main(String[] args) {
// //     Scanner scanner = new Scanner(System.in);
// //     int tests = scanner.nextInt();

// //     for (int t = 0; t < tests; t += 1) {
// //       String bracketSequence = scanner.next();
// //       boolean possiblyRegularSequence = isPossiblyRegular(bracketSequence);
// //       System.out.println(possiblyRegularSequence ? "Yes" : "No");
// //     }
// //   }

// //   static boolean isPossiblyRegular(String bracketSequence) {
// //     if ((bracketSequence.length() & 1) == 1) {
// //       return false;
// //     }

// //     boolean leftBalanced = checkLeftBalanced(bracketSequence);
// //     boolean rightBalanced = checkRightBalanced(bracketSequence);
// //     // System.out.println(leftBalanced + " " + rightBalanced);
// //     return leftBalanced && rightBalanced;
// //   }

// //   static boolean checkLeftBalanced(String bracketSequence) {
// //     Deque<Integer> rightBrackets = new ArrayDeque<Integer>(bracketSequence.length() + 1);
// //     Deque<Integer> wildCards = new ArrayDeque<Integer>(bracketSequence.length() + 1);
// //     rightBrackets.add(0);
// //     wildCards.add(0);
    
// //     for (int i = bracketSequence.length() - 1; i >= 0; i -= 1) {
// //       Integer count = rightBrackets.peekFirst() + (bracketSequence.charAt(i) == ')' ? 1 : 0);
// //       rightBrackets.addFirst(count);
// //       count = wildCards.peekFirst() + bracketSequence.charAt(i) == '?' ? 1 : 0;
// //       wildCards.addFirst(count);
// //     }
// //     rightBrackets.pollLast();
// //     wildCards.pollLast();

// //     int leftCount = 0;
// //     int wildCount = 0;
// //     for (int i = 0; i < bracketSequence.length(); i += 1) {
// //       if (bracketSequence.charAt(i) == '(') {
// //         leftCount += 1;
// //         if (rightBrackets.peek() > leftCount + wildCount || leftCount > rightBrackets.peek() + wildCards.peek()) {
// //           return false;
// //         }
// //       }
// //       wildCount += bracketSequence.charAt(i) == '?' ? 1 : 0;
// //       rightBrackets.poll();
// //       wildCards.poll();
// //     }

// //     return true;
// //   }

// //   static boolean checkRightBalanced(String bracketSequence) {
// //     Deque<Integer> leftBrackets = new ArrayDeque<Integer>(bracketSequence.length() + 1);
// //     Deque<Integer> wildCards = new ArrayDeque<Integer>(bracketSequence.length() + 1);
// //     leftBrackets.add(0);
// //     wildCards.add(0);

// //     for (int i = 0; i < bracketSequence.length(); i += 1) {
// //       Integer count = leftBrackets.peekLast() + (bracketSequence.charAt(i) == '(' ? 1 : 0);
// //       leftBrackets.add(count);
// //       count = wildCards.peekLast() + bracketSequence.charAt(i) == '?' ? 1 : 0;
// //       wildCards.add(count);
// //     }
// //     leftBrackets.pollFirst();
// //     wildCards.pollFirst();

// //     int rightCount = 0;
// //     int wildCount = 0;
// //     // System.out.println(leftBrackets);
// //     for (int i = bracketSequence.length() - 1; i >= 0; i -= 1) {
// //       // System.out.println(rightCount + "RC");
// //       if (bracketSequence.charAt(i) == ')') {
// //         rightCount += 1;
// //         if (leftBrackets.peekLast() > rightCount + wildCount || rightCount > leftBrackets.peekLast() + wildCards.peekLast()) {
// //           // System.out.println(i + " I ");
// //           return false;
// //         }
// //       }
// //       wildCount += bracketSequence.charAt(i) == '?' ? 1 : 0;
// //       leftBrackets.pollLast();
// //       wildCards.pollLast();
// //     }

// //     return true;
// //   }
// // }

// import java.util.Scanner;
// import java.util.List;
// import java.util.ArrayList;
// import java.util.Collections;

// public class Main {
//   public static void main(String[] args) {
//     Scanner scanner = new Scanner(System.in);
//     int tests = scanner.nextInt();

//     for (int t = 0; t < tests; t += 1) {
//       String bracketSequence = scanner.next();
//       boolean possiblyRegularSequence = isPossiblyRegular(bracketSequence);
//       System.out.println(possiblyRegularSequence ? "Yes" : "No");
//     }
//   }

//   static boolean isPossiblyRegular(String bracketSequence) {
//     if ((bracketSequence.length() & 1) == 1) {
//       return false;
//     }

//     List<List<Boolean>> dp = new ArrayList<List<Boolean>>(bracketSequence.length());
//     for (int i = 0; i < bracketSequence.length(); i += 1) {
//       dp.add(new ArrayList<Boolean>(Collections.nCopies(bracketSequence.length(), false)));
//     }

//     dp.get(0).set(0, true);

//     for (var bracket : bracketSequence.toCharArray()) {
//       switch(bracket) {
//         case '(': addLeftBracket(dp);
//           break;
//         case ')': addRightBracket(dp);
//           break;
//         default : addWildcard(dp);
//       }
//     }

//     return dp.get(bracketSequence.length()>>1).get(bracketSequence.length()>>1);
//   }

//   static void addLeftBracket(List<List<Boolean>> dp) {
//     for (int i = dp.size() - 1; i >= 0; i -= 1) {
//       for (int j = dp.get(i).size() - 1; j >= 0; j -= 1) {
//         if (dp.get(i).get(j)) {
//           dp.get(i).set(j, false);
//           if (i + 1 < dp.size() && isLegal(i+1, j)) {
//             dp.get(i+1).set(j, true);
//           }
//         }
//       }
//     }
//   }

//   static void addRightBracket(List<List<Boolean>> dp) {
//     for (int i = dp.size() - 1; i >= 0; i -= 1) {
//       for (int j = dp.get(i).size() - 1; j >= 0; j -= 1) {
//         if (dp.get(i).get(j)) {
//           dp.get(i).set(j, false);
//           if (j + 1 < dp.get(i).size() && isLegal(i, j+1)) {
//             dp.get(i).set(j+1, true);
//           }
//         }
//       }
//     }
//   }

//   static void addWildcard(List<List<Boolean>> dp) {
//     for (int i = dp.size() - 1; i >= 0; i -= 1) {
//       for (int j = dp.get(i).size() - 1; j >= 0; j -= 1) {
//         if (dp.get(i).get(j)) {
//           dp.get(i).set(j, false);
//           if (i + 1 < dp.size() && isLegal(i+1, j)) {
//             dp.get(i+1).set(j, true);
//           }
//           if (j + 1 < dp.get(i).size() && isLegal(i, j+1)) {
//             dp.get(i).set(j+1, true);
//           }
//         }
//       }
//     }
//   }

//   static boolean isLegal(int left, int right) {
//     return left >= right;
//   }
// }

// import java.util.Scanner;
// import java.util.Deque;
// import java.util.ArrayDeque;

// public class Main {
//   public static void main(String[] args) {
//     Scanner scanner = new Scanner(System.in);
//     int tests = scanner.nextInt();

//     for (int t = 0; t < tests; t += 1) {
//       String bracketSequence = scanner.next();
//       boolean possiblyRegularSequence = isPossiblyRegular(bracketSequence);
//       System.out.println(possiblyRegularSequence ? "Yes" : "No");
//     }
//   }

//   static boolean isPossiblyRegular(String bracketSequence) {
//     if ((bracketSequence.length() & 1) == 1) {
//       return false;
//     }

//     boolean leftBalanced = checkLeftBalanced(bracketSequence);
//     boolean rightBalanced = checkRightBalanced(bracketSequence);
//     // System.out.println(leftBalanced + " " + rightBalanced);
//     return leftBalanced && rightBalanced;
//   }

//   static boolean checkLeftBalanced(String bracketSequence) {
//     Deque<Integer> rightBrackets = new ArrayDeque<Integer>(bracketSequence.length() + 1);
//     Deque<Integer> wildCards = new ArrayDeque<Integer>(bracketSequence.length() + 1);
//     rightBrackets.add(0);
//     wildCards.add(0);
    
//     for (int i = bracketSequence.length() - 1; i >= 0; i -= 1) {
//       Integer count = rightBrackets.peekFirst() + (bracketSequence.charAt(i) == ')' ? 1 : 0);
//       rightBrackets.addFirst(count);
//       count = wildCards.peekFirst() + bracketSequence.charAt(i) == '?' ? 1 : 0;
//       wildCards.addFirst(count);
//     }
//     rightBrackets.pollLast();
//     wildCards.pollLast();

//     int leftCount = 0;
//     int wildCount = 0;
//     for (int i = 0; i < bracketSequence.length(); i += 1) {
//       if (bracketSequence.charAt(i) == '(') {
//         leftCount += 1;
//         if (rightBrackets.peek() > leftCount + wildCount || leftCount > rightBrackets.peek() + wildCards.peek()) {
//           return false;
//         }
//       }
//       wildCount += bracketSequence.charAt(i) == '?' ? 1 : 0;
//       rightBrackets.poll();
//       wildCards.poll();
//     }

//     return true;
//   }

//   static boolean checkRightBalanced(String bracketSequence) {
//     Deque<Integer> leftBrackets = new ArrayDeque<Integer>(bracketSequence.length() + 1);
//     Deque<Integer> wildCards = new ArrayDeque<Integer>(bracketSequence.length() + 1);
//     leftBrackets.add(0);
//     wildCards.add(0);

//     for (int i = 0; i < bracketSequence.length(); i += 1) {
//       Integer count = leftBrackets.peekLast() + (bracketSequence.charAt(i) == '(' ? 1 : 0);
//       leftBrackets.add(count);
//       count = wildCards.peekLast() + bracketSequence.charAt(i) == '?' ? 1 : 0;
//       wildCards.add(count);
//     }
//     leftBrackets.pollFirst();
//     wildCards.pollFirst();

//     int rightCount = 0;
//     int wildCount = 0;
//     // System.out.println(leftBrackets);
//     for (int i = bracketSequence.length() - 1; i >= 0; i -= 1) {
//       // System.out.println(rightCount + "RC");
//       if (bracketSequence.charAt(i) == ')') {
//         rightCount += 1;
//         if (leftBrackets.peekLast() > rightCount + wildCount || rightCount > leftBrackets.peekLast() + wildCards.peekLast()) {
//           // System.out.println(i + " I ");
//           return false;
//         }
//       }
//       wildCount += bracketSequence.charAt(i) == '?' ? 1 : 0;
//       leftBrackets.pollLast();
//       wildCards.pollLast();
//     }

//     return true;
//   }
// }

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    for (int t = 0; t < tests; t += 1) {
      String bracketSequence = scanner.next();
      boolean possiblyRegularSequence = isPossiblyRegular(bracketSequence);
      System.out.println(possiblyRegularSequence ? "Yes" : "No");
    }
  }

  static boolean isPossiblyRegular(String bracketSequence) {
    return bracketSequence.charAt(0) != ')' && bracketSequence.charAt(bracketSequence.length() - 1) != '(' && bracketSequence.length() % 2 != 1;
  }
}