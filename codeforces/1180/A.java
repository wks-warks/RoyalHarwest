//Codeforces 1180A 
import java.util.Scanner;

public class CF1180A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int order = SC.nextInt();
        int cells = computeCells(order);
        System.out.println(cells);
    }

    // Computes and returns number of cells in a rhombus of given order
    static int computeCells(int order) {
        if (order == 1)
            return 1;
        else
            return (order-1) * 4 + computeCells(order-1);
    }
}
