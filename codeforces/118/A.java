// Codeforces 118A
import java.util.Scanner;

public class CF118A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String input = SC.next();
        String encodedString = encode(input);
        System.out.println(encodedString);
    }

    // Function to perform encoding
    static String encode(String input) {
        StringBuilder sb = new StringBuilder();
        for (int o = 0; o < input.length(); ++o) {
            char ch = input.charAt(o);
            switch (ch) {
                case 'a':   case 'A':
                case 'e':   case 'E':
                case 'i':   case 'I':
                case 'o':   case 'O':
                case 'u':   case 'U':
                case 'y':   case 'Y':
                            continue;
            } // Ignore given letters
            
            // Appending '.' followed by lower-case equivalent for others
            char lower = Character.toLowerCase(ch);
            sb.append('.');
            sb.append(lower);
        }
        String encodedString = sb.toString();
        return encodedString;
    }
}