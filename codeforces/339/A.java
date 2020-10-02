// Codeforces 339A
import java.util.Scanner;
import java.util.Vector;
import java.util.Collections;

public class CF339A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String series = SC.next();
        String rearranged = afterRearrangement(series);
        System.out.println(rearranged);
    }

    // Returns series after rearrangement
    static String afterRearrangement(String series) {
        // Getting operands and arranging them in a sorted order
        Vector<Character> operands = new Vector<Character>();
        for (int i = 0; i < series.length(); i += 2) { // skipping operators
            operands.addElement(series.charAt(i));
        }
        Collections.sort(operands);
        
        // Creating and returning string comprised of operators and reordered operands
        StringBuilder sb = new StringBuilder();
        sb.append(operands.get(0));
        for (int i = 1; i < operands.size(); ++i) {
            sb.append('+');
            sb.append(operands.get(i));
        }
        String rearranged = sb.toString();
        return rearranged;
    } 
}
