// Codeforces 707A
import java.util.Scanner;

public class CF707A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int rows = SC.nextInt();
        int cols = SC.nextInt();
        char[] pixels = new char[rows*cols];
        for (int p = 0; p < pixels.length; ++p)
            pixels[p] = SC.next().charAt(0);
        boolean colored = checkIfColored(pixels);
        out(colored);
    }

    // Checks and returns whether or not the image is colored
    static boolean checkIfColored(char[] pixels) {
        boolean colored = false;
        for (int p = 0; p < pixels.length; ++p)
            switch(pixels[p]) {
                case 'C':
                case 'M':
                case 'Y':
                    colored = true;
            }
        return colored;
    }


    // Prints output based on whether the image is colored or black and white
    static void out(boolean colored) {
        if (colored)
            System.out.println("#Color");
        else
            System.out.println("#Black&White");
    }
}