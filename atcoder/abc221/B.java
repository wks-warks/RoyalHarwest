import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        String t = scanner.next();

        boolean possible = s.equals(t);

        for (int i = 0; i < s.length()-1; i++) {
            StringBuilder sb = new StringBuilder(s);
            char first = s.charAt(i);
            char second = s.charAt(i+1);

            sb.setCharAt(i, second);
            sb.setCharAt(i+1, first);

            if (sb.toString().equals(t)) {
                possible = true;
                break;
            }
        }

        System.out.println(possible ? "Yes" : "No");
    }
}