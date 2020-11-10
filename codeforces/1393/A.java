//Codeforces 1393A 
import java.util.Scanner;

public class CF1393A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int gridSize = SC.nextInt();
            System.out.println(gridSize/2 + 1);
        }
    }
}