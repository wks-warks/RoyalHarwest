//Codeforces 1433A 
import java.util.Scanner;

public class CF1433A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            String respondent = SC.next();
            int digitsPressed = computeDigitsPressed(respondent);
            System.out.println(digitsPressed);
        }
    }

    // Computes and returns number of digits pressed
    static int computeDigitsPressed(String respondent) {
        int digitsBefore = (respondent.charAt(0)-49)*10; // Number of pressed digits of other categories
        int digitsInCategory = (respondent.length())*(respondent.length()+1)/2;
        return digitsBefore+digitsInCategory;
    }
}
