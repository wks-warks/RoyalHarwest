import java.io.*;
import java.util.*;
 
public class E2 {
    static final FastReader FR = new FastReader();
    static int[] fact, ifact;
    static int choose, diff;
    static final int MOD = 1_000_000_007;
    public static void main(String[] args) {
        fact = new int[200_001];
        ifact = new int[200_001];
        fact[0] = fact[1] = ifact[0] = ifact[1] = 1;
        for (int i = 2; i <= 200_000; ++i) {
            fact[i] = (int) ((long)fact[i-1] * i % MOD);
            ifact[i] = modInverse(fact[i]);
        }
        
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = FR.nextInt();
            choose = FR.nextInt();
            diff = FR.nextInt();
            Integer[] arr = new Integer[arrLen];
            for (int i = 0; i < arrLen; ++i)
                arr[i] = FR.nextInt();
            Arrays.sort(arr);
            long answer;
            if (choose > 1)
                answer = getAnswer(arr);
            else
                answer = arrLen;
            solution.append(answer);
            solution.append('\n');
        }
        System.out.print(solution.toString());
    }
    
    static int modInverse(int n) {
        int[] coeffs = new int[2];
        exgcd(n, MOD, coeffs);
        return (coeffs[0] % MOD + MOD) % MOD;
    }
    static int exgcd(int num1, int num2, int[] coeffs) {
        if (num2 == 0) {
            coeffs[0] = 1;
            coeffs[1] = 0;
            return num1;
        }
        int[] pass = new int[2];
        int g = exgcd(num2, num1 % num2, pass);
        coeffs[0] = pass[1];
        coeffs[1] = pass[0] - pass[1] * (num1 / num2);
        return g;
    }
    
    static int nCr (int n, int r) {
        if (r > n) return 0;
        else return (int) (((long)fact[n] * ifact[r] % MOD) * ifact[n-r] % MOD);
    }
  
    // Gets answer
    static long getAnswer(Integer[] arr) {
        long answer = 0;
        int left, right, mid, lIdx, rIdx;
        for (int i = 0; i < arr.length-1; ++i) {
            left = i+1;
            right = arr.length-1;
            rIdx = -1;
            while (left <= right) {
                mid = (left + right) / 2;
                if (arr[mid] - arr[i] <= diff) {
                    rIdx = mid;
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
            if (rIdx != -1) {
                lIdx = i+1;
                answer += nCr(rIdx-lIdx+1, choose-1);
                answer %= (long)MOD;
            }
        }
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