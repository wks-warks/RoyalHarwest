//Codeforces 265A 
import java.util.Scanner;

public class CF265A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String stones = SC.next();
        String commands = SC.next();
        int pos = 0;
        for (char command : commands.toCharArray()) {
            if (stones.charAt(pos) == command)
                pos += 1;
        }
        System.out.println(pos+1);
    }
}
