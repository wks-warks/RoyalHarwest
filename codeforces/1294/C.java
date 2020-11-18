//Codeforces 1294C 
import java.util.Scanner;
import java.util.Vector;

public class CF1294C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        Vector<Integer> primes = getPrimes(31622); // Getting vector of primes upto Sq.rt(1_000_000_000)
        for (int t = 0; t < tests; ++t) {
            int num = SC.nextInt();
            Divisors solution = getSolution(num, primes);
            out(solution);
        }
    }

    static Divisors getSolution(int num, Vector<Integer> primes) {
        Divisors solution = new Divisors();
        int p = 0;
        while (p < primes.size()) {
            if (solution.getDivisorsFound() == 2)
                break;
            int check = primes.get(p);
            if (num % check == 0) {
                num /= check;
                solution.addDivisor(check);
                if (num % (check * check) == 0 && solution.getDivisorsFound() < 2) {
                    num /= check * check;
                    solution.addDivisor(check*check);
                }
            }
            ++p;
        }
        if (num != 1 && num != solution.getDiv1() && num != solution.getDiv2())
            solution.addDivisor(num);
        return solution;
    }

    // Computes and returns vector of primes upto the number passed
    static Vector<Integer> getPrimes(int upto) {
        Vector<Integer> primes = new Vector<>();
        boolean[] composite = new boolean[upto+1];
        for (int candidate = 2; candidate * candidate <= upto; ++candidate)
            if (composite[candidate])
                for (int num = candidate * candidate; num <= upto; num += candidate)
                    composite[candidate] = true;
        for (int num = 2; num <= upto; ++num)
            if (!composite[num])
                primes.add(num);
        return primes;
    }

    // Prints output corresponding to the solution
    static void out(Divisors solution) {
        if (solution.getDivisorsFound() != 3) {
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
        System.out.println(solution.getDiv1() + " " + solution.getDiv2() + " " + solution.getDiv3());
    }
}

class Divisors {
    private int div1, div2, div3, divisorsFound;

    // Setter methods
    public void addDivisor(int div) {
        this.div3 = div2;
        this.div2 = div1;
        this.div1 = div;
        divisorsFound += 1;
    }

    // Getter methods
    public int getDivisorsFound() {
        return divisorsFound;
    }
    public int getDiv1() {
        return div1;
    }
    public int getDiv2() {
        return div2;
    }
    public int getDiv3() {
        return div3;
    }
}