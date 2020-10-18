// Codeforces 1369A
import java.util.Scanner;

public class CF1369A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int sides = SC.nextInt();
            boolean beautiful = checkBeauty(sides);
            out(beautiful);
            /*
                interior = 180(1 - 2/sides);
                if sides % 4 == 0
                    interior = 180 - 90/(sides/4)
                    sides/4 * interior = 180*sides/4 - 90 => if (sides % 4) == 0, then true
                
                generic, for sides >= 4
                    interior = 180 - 360/sides
                             = 90(2 - 4/sides)
                    Required x * interior = +-90, 0 <= x <= sides
                        x * (2 - 4/sides) needs to be odd
                        => x*4/sides needs to be 1, as 2-4/sides >= 1
                        there
                 
            */
        }
    }

    // Checks and returns whether the given regular polygon is beautiful or not
    static boolean checkBeauty(int sides) {
        return sides % 4 == 0;
        /*
            Obviously false for 3
            For sides >= 4
                interiorAngle = 180 - 360/sides
                              = 90(2 - 4/sides)  //This lies in [90, 180) for sides >= 4
                Required
                    x * (2 - 4/sides) needs to be odd, where 1 <= x <= sides, x is an integer
                    2x - 4x/sides needs to be odd
                    => 4x/sides needs to be odd
                    x <= sides
                    4x/sides <= 4
                    => 4x/sides needs to be 1 or 3
                    4x/sides = 3 => x = 3*sides/4
                    4x/sides = 1 => x = sides/4
                    either way, for x to be an integer, sides % 4 needs to be 0
                    => answer is true iff sides % 4 == 0, sides >= 4
        */
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("yes");
        else
            System.out.println("no");
    }
}