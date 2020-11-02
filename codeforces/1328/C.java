//Codeforces 1328C 
import java.util.Scanner;

public class CF1328C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int numLength = SC.nextInt();
            String ternaryXor = SC.next();
            NumPair np = getNums(numLength, ternaryXor);
            out(np);
        }
    }

    // Computes and returns pair of numbers satisfying given conditions
    static NumPair getNums(int numLength, String ternaryXor) {
        int larger = 0; // 0 => Neither is larger, 1 => first is larger
        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();
        for (int i = 0; i < ternaryXor.length(); ++i)
            if (larger == 0)
                switch(ternaryXor.charAt(i)) {
                    case '2' :
                        num1.append('1');
                        num2.append('1');
                        break;
                    case '1' :
                        num1.append('1');
                        num2.append('0');
                        larger = 1;
                        break;
                    case '0':
                        num1.append('0');
                        num2.append('0');
                }
            else { // It is established that the 1st number is now larger
                num1.append(0);
                num2.append(ternaryXor.charAt(i));
            }
        return new NumPair(num1.toString(), num2.toString());
    }

    // Prints output corresponding to the NumPair
    static void out(NumPair np) {
        System.out.println(np.getFirst());
        System.out.println(np.getSecond());
    }
}

class NumPair {
    private String num1;
    private String num2;
    public NumPair(String num1, String num2) {
        this.num1 =  num1;
        this.num2 = num2;
    }

    // Getter mehtods
    public String getFirst() {
        return num1;
    }
    public String getSecond() {
        return num2;
    }
}
