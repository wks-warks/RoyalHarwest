// Codeforces 1360A
import java.util.Scanner;

public class CF1360A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int rectLength = SC.nextInt(); // Length of the rectangles
            int rectBreadth = SC.nextInt(); // Breadth of the rectangles
            int sqArea = computeSquareArea(rectLength, rectBreadth); // Size of square containing both rectangles
            System.out.println(sqArea);
        }
    }

    // Computes and returns area of square containing both rectangles
    static int computeSquareArea(int rectLength, int rectBreadth) {
        int side = Math.max(2*Math.min(rectLength, rectBreadth), Math.max(rectLength, rectBreadth));
        return squareArea(side);
    }

    // Computes and returns area of a square with given sideLength
    static int squareArea(int side) {
        return side * side;
    }
}