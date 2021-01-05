import java.io.*;
import java.util.*;
 
public class B {
    static final FastReader FR = new FastReader();
 
    static int n, x;
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            value = new TreeMap<Integer, Long>();
            stopAdding = new HashSet<Integer>();
            n = FR.nextInt();
            x = FR.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i += 1) {
                arr[i] = FR.nextInt();
            }
            long ansSum = getAnsSum(arr);
            solution.append(ansSum + "\n");
        }
      	PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
		writer.print(solution.toString());
        writer.close();
    }

    static Set<Integer> stopAdding;
    static Map<Integer, Long> value;
    static long getAnsSum(int[] arr) {
        for (var element : arr) {
            setAns(element, 0, 1);
        }
        return totalAnswer();
    }

    static long totalAnswer() {
        long sum = 0;
        boolean stop = false;
        for (var element : value.entrySet()) {
            long val = element.getValue();
            sum += val;
            if (stop) return sum;
            stop = stopAdding.contains(element.getKey());
        }
        return sum;
    }

    static boolean setAns(int num, int order, int times) {
        if (num % x != 0) {
            long presentAnswer = value.containsKey(order) ? value.get(order) : 0;
            value.put(order, presentAnswer+(long)num*times);
            stopAdding.add(order);
            return false;
        }
        long presentAnswer = value.containsKey(order) ? value.get(order) : 0;
        value.put(order, presentAnswer+(long)num*times);
        if (!stopAdding.contains(order))
            setAns(num/x, order+1, times*x);
        return true;
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