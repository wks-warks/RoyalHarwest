//Codeforces 1366A 
import java.util.Scanner;

public class CF1366A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int sticks = SC.nextInt();
            int diamonds = SC.nextInt();
            int maxTools = computeMaxTools(sticks, diamonds);
            System.out.println(maxTools);    
        }
    }

    // Computes and returns maximum number of tools that can be manufactured 
    static int computeMaxTools(int sticks, int diamonds) {
        // Since both tools give equal benefits, we try to maximize tool production        
        // I'll try to bring the difference between the two resources to a minimum at first
        int resource1 = Math.min(sticks, diamonds);
        int resource2 = Math.max(sticks, diamonds);
        int tools = Math.min(resource1, resource2-resource1);
        resource1 -= tools;
        resource2 -= 2*tools;
        // Now we either have both resources in equal amounts or have exhausted the lesser resource
        int additionalTools = 2 * (resource1 / 3); // Brackets are important here
        resource1 -= 3*additionalTools/2;
        resource2 -= 3*additionalTools/2;
        tools += additionalTools;
        // We can no longer create tools of both types.
        if (resource1 + resource2 >= 3 && Math.min(resource1, resource2) >= 1)
            tools += 1;
        return tools;
    }
}
