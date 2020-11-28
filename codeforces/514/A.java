//Codeforces 514A 
import java.util.Scanner;
import java.util.HashMap;

public class CF514A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String numString = SC.next();
        String converted = getConverted(numString);
        System.out.println(converted);
    }

    // Computes and returns converted number-string
    static String getConverted(String numString) {
        HashMap<Character, Character> mapping = new HashMap<>();
        for (int i = 0; i < 10; ++i)
            mapping.put((char) ('0' + i), (char) ('0' + 9 - i));
        
        int i = 1;
        StringBuilder sb = new StringBuilder();
        if (numString.charAt(0) == '9')
            sb.append(9);
        else
            i = 0;
        while (i < numString.length()) {
            sb.append((char) Math.min(mapping.get(numString.charAt(i)), numString.charAt(i)));
            i += 1;
        }
        return sb.toString();
    }
}
