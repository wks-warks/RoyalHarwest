//Codeforces 1370C 
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class CF1370C {
    static final Scanner SC = new Scanner(System.in);
    static Set<Integer> primes;
    public static void main(String[] args) {
        setPrimes();
        int tests = SC.nextInt();
        while(tests-->0) {
            int n = SC.nextInt();
            String winner = getWinner(n);
            System.out.println(winner);
        }
    }
    
    static void setPrimes() {
        primes = new HashSet<Integer>();
        primes.add(2);
        boolean[] isComposite = new boolean[(int)Math.sqrt(1e9) + 1];
        for (int i = 3; i < isComposite.length; i += 2) {
            if (isComposite[i]) continue;
            int prime = i;
            primes.add(prime);
            for (int j = prime*prime; j < isComposite.length; j += (prime<<1)) {
                isComposite[j] = true;
            }
        }
    }

    static String getWinner(int n) {
        boolean wins = checkWin(n);
        return wins ? "Ashishgup" : "FastestFinger";
    }

    static boolean checkWin(int n) {
        if (n == 1) return false;
        if (n == 2) return true;
        if (isOdd(n)) return true;
        int twoPows = countTwoPows(n);
        n >>= twoPows;
        if (n == 1) return false;
        boolean primeLeft = isPrime(n);
        if (primeLeft && twoPows == 1) return false;
        else return true;
    }

    static boolean isPrime(int n) {
        if (primes.contains(n)) return true;
        for (var element : primes) {
            if (n%element == 0) return false;
        }
        return true;
    }

    static int countTwoPows(int n) {
        int twoPows = 0;
        while (isEven(n)) {
            n >>= 1;
            twoPows += 1;
        }
        return twoPows;
    }

    static boolean isOdd(int n) {
        return (n & 1) != 0;
    }
    static boolean isEven(int n) {
        return (n & 1) == 0;
    }
}
