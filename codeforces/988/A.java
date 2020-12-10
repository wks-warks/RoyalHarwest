//Codeforces 988A 
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;

public class CF988A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        TreeMap<Integer, Integer> ratings = new TreeMap<>();
        int students = SC.nextInt();
        int teamSize = SC.nextInt();
        for (int s = 0; s < students; ++s) {
            int rating = SC.nextInt();
            if (!ratings.containsKey(rating))
                ratings.put(rating, s+1);
        }
        if (ratings.size() >= teamSize) {
            System.out.println("YES");
            int printed = 0;
            for (Map.Entry<Integer, Integer> me : ratings.entrySet()) {
                if (printed == teamSize) {
                    System.out.println();
                    break;
                }
                else {
                    System.out.print(me.getValue() + " ");
                    printed += 1;
                }
            }
        }
        else {
            System.out.println("NO");
        }
        
    }
}
