import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    static int fact[], ifact[];
    static int mmul(int num1, int num2) {
        return (int) ((long)num1 * num2 % mod);
    }
    static int mmul(int... a) {
        int ans = a[0];
        for (int i = 1; i < a.length; i += 1) {
            ans = mmul(ans, a[i]);
        }
        return ans;
    }
    static int minv(int num) {
        return (exgcd(num, mod)[0] % mod + mod) % mod;
    }
    static int[] exgcd(int num1, int num2) {
        if (num2 == 0)
            return new int[]{1, 0};
        int[] arr = exgcd(num2, num1 % num2);
        return new int[]{arr[1], arr[0] - arr[1] * (num1 / num2)};
    }
    static int choose(int n, int k) {
        if (k > n)
            return 0;
        return mmul(fact[n], ifact[n-k], ifact[k]);
    }

    public static void main(String[] args) {
        fact = new int[200001];
        ifact = new int[200001];
        fact[0] = fact[1] = ifact[0] = ifact[1] = 1;
        for (int i = 2; i <= 200000; i += 1) {
            fact[i] = mmul(fact[i-1], i);
            ifact[i] = minv(fact[i]);
        }
        
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int rows = FR.nextInt();
            int cols = FR.nextInt();
            int bottomPit = FR.nextInt();
            int leftPit = FR.nextInt();
            int ways = countWays(rows, cols, bottomPit, leftPit);
            solution.append(ways);
        }
        System.out.println(solution.toString());
    }

    static final int mod = 1_000_000_007;
    // Computes and returns ways to reach bottom right cell Modulo 1e9+7
    static int countWays(int rows, int cols, int bottomPit, int leftPit) {
        int answer = 0;
        for (int i = leftPit; i < cols; ++i) {
            answer += mmul(choose(leftPit+rows-bottomPit-1, i), choose(cols-2-(leftPit-bottomPit-1), cols-i-1));
            answer %= mod;
        }
        // for (int i = leftPit; i < leftPit+rows-bottomPit; i += 1) {
            // System.out.println(i + "C" + leftPit + " * " + (rows+cols-2-i) + "C" + (cols-leftPit-1));
            // answer += mmul(choose(i, leftPit), choose(rows+cols-2-i, cols-leftPit-1));
            // answer %= mod;
        // }
        return answer;
    }

    // Fast Input - GFG
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
                } catch (IOException  e) { 
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
            } catch (IOException e)  { 
                e.printStackTrace(); 
            } 
            return str; 
        } 
    }
}