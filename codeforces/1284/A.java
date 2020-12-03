//Codeforces 1284A 
import java.util.Scanner;

public class CF1284A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int setOneSize = SC.nextInt();
        int setTwoSize = SC.nextInt();
        String[] setOne = new String[setOneSize];
        String[] setTwo = new String[setTwoSize];

        for (int i = 0; i < setOneSize; ++i)
            setOne[i] = SC.next();
        
        for (int i = 0; i < setTwoSize; ++i)
            setTwo[i] = SC.next();
        
        int queries = SC.nextInt();
        for (int q = 0; q < queries; ++q) {
            int year = SC.nextInt();
            String name = getName(year, setOne, setTwo);
            System.out.println(name);
        }
    }

    // Computes and returns name for given year
    static String getName(int year, String[] setOne, String[] setTwo) {
        String name = "";
        name += setOne[(year-1) % setOne.length];
        name += setTwo[(year-1) % setTwo.length];
        return name;
    }
}
