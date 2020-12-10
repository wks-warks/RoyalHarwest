// //Codeforces 1287A 
// import java.util.Scanner;

// public class CF1287A {
//     static final Scanner SC = new Scanner(System.in);
//     public static void main(String[] args) {
//         int tests = SC.nextInt();
//         for (int t = 0; t < tests; ++t) {
//             int students = SC.nextInt();
//             String state = SC.next();
//             int timeUntilArmageddon = computeTimeUntilArmageddon(state);
//             System.out.println(timeUntilArmageddon);
//         }
//     }

//     // Computes and returns time until all students are in the worst possible state that they can be given the initial states
//     static int computeTimeUntilArmageddon(String state) {
//         boolean angryFound = false;
//         int maxTime = 0;
//         int time = 0;
//         for (int i = 0; i < state.length(); ++i) {
//             if (state.charAt(i) == 'A') {
//                 angryFound = true;
//                 time = 0;
//             }
//             else if (angryFound) {
//                 time += 1;
//             }
//             maxTime = Math.max(time, maxTime);
//         }
//         return maxTime;
//     }
// }

//Codeforces 1287A 
import java.util.Scanner;

public class CF1287A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int students = SC.nextInt();
            String state = SC.next();
            int timeUntilArmageddon = computeTimeUntilArmageddon(state);
            System.out.println(timeUntilArmageddon);
        }
    }

    // Computes and returns time until all students are in the worst possible state that they can be given the initial states
    static int computeTimeUntilArmageddon(String state) {
        int prevAngry = -1;
        int maxTime = 0;
        for (int i = 0; i < state.length(); ++i) {
            if (state.charAt(i) == 'A') {
                if (prevAngry == -1) {
                    prevAngry = i;
                    continue;
                }
                int time = i - prevAngry - 1;
                maxTime = Math.max(time, maxTime);
                // System.out.println("MT" + maxTime);
                prevAngry = i;
            }
        }

        if (prevAngry != -1)
            maxTime = Math.max(maxTime, state.length() - prevAngry - 1);
        return maxTime;
    }
}
