// Codeforces 230B
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class CF230B {
    static final FastReader FR = new FastReader();
    public static void main(String[] args) {
        Vector<Long> tPrimes = getTPrimes(1_000_000_000_000L); // Vector of T-Primes upto 1,000,000,000,000
        int tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            long n = FR.nextLong();
            boolean isTPrime = checkTPrimality(n, tPrimes);
            out(isTPrime);
        }
    }

    static Vector<Long> getTPrimes(long upto) {
        long sqrt = (long)Math.sqrt(upto);
        Vector<Integer> primes = getPrimes((int)sqrt); // Vector of primes upto 1,000,000
        Vector<Long> tPrimes = new Vector<>();
        for (int i = 0; i < primes.size(); ++i) {
            int prime = primes.get(i);
            long toAdd = (long) prime * prime;
            tPrimes.add(toAdd);
        }
        return tPrimes;
    }

    // Gets Vector of primes upto passed number
    static Vector<Integer> getPrimes(int upto) {
        Vector<Integer> primes = new Vector<>();
        boolean[] isComposite = new boolean[1_000_001];
        for (int check = 2; check <= 1_000; ++check) {
            if (isComposite[check])
                continue;
            else { // It is prime, set composites divisible by it
                primes.add(check); // Add checked number to vector since it is prime
                for (int set = check*check; set <= 1_000_000; set += check)
                    isComposite[set] = true;
            }
        }
        for (int check = 1_001; check <= 1_000_000; ++check)
            if (!isComposite[check])
                primes.add(check);
        return primes;
    }

    // Checks whether the given number is T-Prime and returns boolean accordingly
    static boolean checkTPrimality(long n, Vector<Long> tPrimes) {
        int lIdx = 0;
        int rIdx = tPrimes.size()-1;
        // Performing binary search over the vector of T-Primes
        while (lIdx <= rIdx) {
            int mid = (lIdx+rIdx)/2;
            long check = tPrimes.get(mid);
            if (check < n)
                lIdx = mid+1;
            else if (check > n)
                rIdx = mid-1;
            else
                return true;
        }
        return false; // Match not found in Vector of TPrimes
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    // Reference: https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
    static class FastReader { 
        BufferedReader br;
        StringTokenizer st;
  
        public FastReader() { 
            br = new BufferedReader(new
                     InputStreamReader(System.in)); 
        } 
  
        String next() { 
            while (st == null || !st.hasMoreElements()) { 
                try { 
                    st = new StringTokenizer(br.readLine()); 
                } 
                catch (IOException  e) { 
                    e.printStackTrace(); 
                } 
            } 
            return st.nextToken(); 
        } 
  
        int nextInt() { 
            return Integer.parseInt(next()); 
        } 
  
        long nextLong() { 
            return Long.parseLong(next()); 
        } 
  
        double nextDouble() { 
            return Double.parseDouble(next()); 
        } 
  
        String nextLine() { 
            String str = ""; 
            try { 
                str = br.readLine(); 
            } 
            catch (IOException e) { 
                e.printStackTrace(); 
            } 
            return str; 
        } 
    } 
}