//Codeforces 1015A 
import java.util.Scanner;
import java.util.HashSet;

public class CF1015A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int segments = SC.nextInt();
        int points = SC.nextInt();
        HashSet<Integer> pointSet = new HashSet<>();
        for (int i = 0; i < points; i += 1) {
            pointSet.add(i+1);
        }
        for (int i = 0; i < segments; ++i) {
            int left = SC.nextInt();
            int right = SC.nextInt();
            while (left <= right) {
                pointSet.remove(left);
                left += 1;
            }
        }
        System.out.println(pointSet.size());
        for (var element : pointSet) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
