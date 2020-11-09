//Codeforces 1095A 
import java.util.Scanner;

public class CF1095A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int strLen = SC.nextInt();
        String encrypted = SC.next();
        String decrypted = decrypt(encrypted);
        System.out.println(decrypted);
    }

    // Decrypts encrypted string passed and returns the decrypted string
    static String decrypt(String encrypted) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, jump = 0; i < encrypted.length(); i += ++jump)
            sb.append(encrypted.charAt(i));
        return sb.toString();
    }
}
