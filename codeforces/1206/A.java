//Codeforces 1206A 
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class CF1206A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int aSize = SC.nextInt();
        Integer[] a = new Integer[aSize];
        for (int i = 0; i < aSize; ++i)
            a[i] = SC.nextInt();

        int bSize = SC.nextInt();
        Integer[] b = new Integer[bSize];
        for (int i = 0; i < bSize; ++i)
            b[i] = SC.nextInt();
        
        Pair solution = getSolution(a, b);
        out(solution);
    }

    // Computes and returns solution pair value
    static Pair getSolution(Integer[] a, Integer[] b) {
        int maxFromA = Collections.max(Arrays.asList(a));            
        int maxFromB = Collections.max(Arrays.asList(b));
        return new Pair(maxFromA, maxFromB);
    }

     // Prints output corresponding to solution
     static void out(Pair solution) {
         System.out.print(solution.getNum1() + " ");
         System.out.println(solution.getNum2());
     }
}

class Pair {
    private int num1, num2;
    public Pair(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    // Getter methods
    public int getNum1() {
        return num1;
    }
    public int getNum2() {
        return num2;
    }
}