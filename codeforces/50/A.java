// Codeforces 50A
import java.util.Scanner;

public class CF50A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int boardLen = SC.nextInt();
        int boardWid = SC.nextInt();
        int maxDominos = computeMaxDominos(boardLen, boardWid);
        System.out.println(maxDominos);
    }

    // Computes max. no. of dominos that can be placed on the board
    static int computeMaxDominos(int boardLen, int boardWid) {
        return boardLen * boardWid / 2;
    }
}