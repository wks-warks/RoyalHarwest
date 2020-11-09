//Codeforces 80A 
import java.util.Scanner;
import java.util.Vector;

public class CF80A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        Vector<Integer> primes = getPrimeVec(50); // Vector of primes upto 50
        int firstPrime = SC.nextInt();
        int nextNum = SC.nextInt();
        if (nextNum == nextPrimeOf(firstPrime, primes))
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    // Checks and returns next prime if it's also less than or equal to the largest prime in the vector, else returns -1
    static int nextPrimeOf(int firstPrime, Vector<Integer> primes) {
        int left = 0;
        int right = primes.size() - 2;
        while (left <= right) {
            int mid = (left+right) / 2;
            int consideredPrime = primes.get(mid);
            if (consideredPrime == firstPrime)
                return primes.get(mid+1);
            else if (consideredPrime < firstPrime)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1; // No match found
    } 

    // Computes and returns vector of primes upto passed value
    static Vector<Integer> getPrimeVec(int upto) {
        boolean[] hasDivisors = new boolean[upto+1];
        int setDivisorsFor = (int)Math.sqrt(upto);
        for (int i = 2; i <= setDivisorsFor; i+= 1)
            if (!hasDivisors[i])
                for (int j = i*2; j <= upto; j += i)
                    hasDivisors[j] = true;
        Vector<Integer> primes = new Vector<>();
        for (int i = 2; i <= upto; ++i)
            if (!hasDivisors[i])
                primes.add(i);
        return primes;
    }
}
