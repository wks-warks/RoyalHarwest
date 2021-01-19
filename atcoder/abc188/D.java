import java.io.*;
import java.util.*;
 
class Main {
    static final FastReader FR = new FastReader();
 
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int services = FR.nextInt();
            int primeCost = FR.nextInt();
            int start, end, price;
            Map<Integer, Long> priceVariation = new TreeMap<Integer, Long>();
            for (int i = 0; i < services; i += 1) {
                start = FR.nextInt();
                end = FR.nextInt();
                price = FR.nextInt();
                priceVariation.put(start, priceVariation.containsKey(start) ? priceVariation.get(start)+price : price);
                priceVariation.put(end+1, priceVariation.containsKey(end+1) ? priceVariation.get(end+1)-price : -price);
            }
            
            long billAmount = getBill(priceVariation, primeCost);
            solution.append(billAmount + "\n");
        }
      	PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
		writer.print(solution.toString());
        writer.close();
    }

    static long getBill(Map<Integer, Long> priceVariation, int primeCost) {
        long bill = 0;
        long cost = 0;
        int prevDay = 0;
        for (var element : priceVariation.entrySet()) {
            int day = element.getKey();
            bill += (long) (day-prevDay) * Math.min(cost, primeCost);
            prevDay = day;
            long variation = element.getValue();
            cost += variation;
        }
        return bill;
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