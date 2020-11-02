//Codeforces 978B 
import java.util.Scanner;

public class CF978B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int strLen = SC.nextInt();
        String content = SC.next();
        int removalsForDecency = countRemovals(content);
        System.out.println(removalsForDecency);
    }

    // Counts and returns number of characters to be removed for the string to be decent
    static int countRemovals(String content){
        int removals = 0;
        char previous = '-';
        char beforeThat = '-';
        for (int i = 0; i < content.length(); ++i) {
            if (content.charAt(i) == 'x' && previous == 'x' && beforeThat == 'x')
                removals += 1;
            beforeThat = previous;
            previous = content.charAt(i);
        }
        return removals;
    }
}
