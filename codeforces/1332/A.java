//Codeforces 1332A 
import java.util.Scanner;

public class CF1332A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        Positions pos = new Positions();
        Moves mov = new Moves();
        for (int t = 0; t < tests; ++t) {
            mov.setLeft(SC.nextInt());
            mov.setRight(SC.nextInt());
            mov.setDown(SC.nextInt());
            mov.setUp(SC.nextInt());

            pos.setXInit(SC.nextInt());
            pos.setYInit(SC.nextInt());
            pos.setXLimit1(SC.nextInt());
            pos.setYLimit1(SC.nextInt());
            pos.setXLimit2(SC.nextInt());
            pos.setYLimit2(SC.nextInt());

            boolean safeWalkPossible = checkSafePossibility(mov, pos);
            out(safeWalkPossible);
        }
    }

    // Checks and returns whether or not a safe walk route exists
    static boolean checkSafePossibility(Moves mov, Positions pos) {
        int xFin = pos.getXInit() + mov.getRight() - mov.getLeft();
        int yFin = pos.getYinit() + mov.getUp() - mov.getDown();
        // Checking whether any sort of movemen (if exists) is possible
        if ((pos.getXLimit1() == pos.getXLimit2() && mov.getRight() + mov.getLeft() > 0) || (pos.getYLimit1() == pos.getYLimit2() && mov.getUp() + mov.getDown() > 0))
            return false;
        else
            return (pos.getXLimit1() <= xFin && xFin <= pos.getXLimit2()) && (pos.getYLimit1() <= yFin && yFin <= pos.getYLimit2());
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}

class Moves {
    private int left, right, down, up;
    
    // Setter methods
    public void setLeft(int left) {
        this.left = left;
    }
    public void setRight(int right) {
        this.right = right;
    }
    public void setDown(int down) {
        this.down = down;
    }
    public void setUp(int up) {
        this.up = up;
    }
    

    // Getter methods
    public int getLeft() {
        return left;
    }
    public int getRight() {
        return right;
    }
    public int getDown() {
        return down;
    }
    public int getUp() {
        return up;
    }
}

class Positions {
    private int xInit, yInit, xLimit1, yLimit1, xLimit2, yLimit2;

    // Setter Methods
    public void setXInit(int xInit) {
        this.xInit = xInit;
    }
    public void setYInit(int yInit) {
        this.yInit = yInit;
    }
    public void setXLimit1(int xLimit1) {
        this.xLimit1 = xLimit1;
    }
    public void setYLimit1(int yLimit1) {
        this.yLimit1 = yLimit1;
    }
    public void setXLimit2(int xLimit2) {
        this.xLimit2 = xLimit2;
    }
    public void setYLimit2(int yLimit2) {
        this.yLimit2 = yLimit2;
    }

    // Getter Methods
    public int getXInit() {
        return xInit;
    }
    public int getYinit() {
        return yInit;
    }
    public int getXLimit1() {
        return xLimit1;
    }
    public int getYLimit1() {
        return yLimit1;
    }
    public int getXLimit2() {
        return xLimit2;
    }
    public int getYLimit2() {
        return yLimit2;
    }
}