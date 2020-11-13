//Codeforces 459A 
import java.util.Scanner;

public class CF459A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int x, y;
        // First point
        x = SC.nextInt();
        y = SC.nextInt();
        Point p1 = new Point(x, y);
        // Second point
        x = SC.nextInt();
        y = SC.nextInt();
        Point p2 = new Point(x, y);
        Solution solution = getSolution(p1, p2);
        out(solution);
    }

    // Prints output corresponding to the solution
    static void out(Solution solution) {
        if (!solution.doesExist())
            System.out.println("-1");
        else
            System.out.println(solution.getP1().getX() + " " + solution.getP1().getY() + " " + solution.getP2().getX() + " " + solution.getP2().getY());
    }

    // Computes and returns solution
    static Solution getSolution(Point p1, Point p2) {
        if (p1.getX() == p2.getX())
            // p1.getY() - p2.getY() = sidelen (allowed to be negative as that doesn't affect our solution)
            return new Solution(true, new Point(p1.getX() - (p1.getY() - p2.getY()), p1.getY()), new Point(p2.getX() - (p1.getY() - p2.getY()), p2.getY()));
        else if (p1.getY() == p2.getY())
            // p1.getX() - p2.getX() = sidelen (allowed to be negative as that doesn't affect our solution)
            return new Solution(true, new Point(p1.getX(), p1.getY() - (p1.getX() - p2.getX())), new Point(p2.getX(), p2.getY() - (p1.getX() - p2.getX())));
        else if (Math.abs(p1.getX() - p2.getX()) == Math.abs(p1.getY() - p2.getY()))
            return new Solution(true, new Point(p1.getX(), p2.getY()), new Point(p2.getX(), p1.getY()));
        else
            return new Solution(false);
    }
}

class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}

class Solution {
    private boolean exists;
    private Point p1, p2;
    public Solution(boolean exists) {
        this.exists = exists;
    }
    public Solution(boolean exists, Point p1, Point p2) {
        this.exists = exists;
        this.p1 = p1;
        this.p2 = p2;
    }

    public boolean doesExist() {
        return exists;
    }
    public Point getP1() {
        return p1;
    }
    public Point getP2() {
        return p2;
    }
}