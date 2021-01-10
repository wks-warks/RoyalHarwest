import java.io.*;
import java.util.*;
 
class Main {
    static final FastReader FR = new FastReader();
 
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int towns = FR.nextInt();
            int roads = FR.nextInt();
            int[] prices = new int[towns];
            for (int i = 0; i < towns; i += 1) {
                prices[i] = FR.nextInt();
            }
            List<Set<Integer>> connectedTowns = new ArrayList<Set<Integer>>(towns);
            for (int i = 0; i < towns; i += 1) {
                connectedTowns.add(new TreeSet<Integer>());
            }
            for (int i = 0; i < roads; i += 1) {
                int start = FR.nextInt();
                int end = FR.nextInt();
                connectedTowns.get(start-1).add(end-1);
            }
            int profit = getProfit(towns, prices, connectedTowns);
            solution.append(profit);
        }
      	PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
		writer.print(solution.toString());
        writer.close();
    }

    static int getProfit(int towns, int[] prices, List<Set<Integer>> connectedTowns) {
        int maxProfit = Integer.MIN_VALUE;
        int[] minCosts = new int[towns];
        for (int i = 0; i < towns; ++i) minCosts[i] = Integer.MAX_VALUE;

        for (int i = 0; i < towns; i += 1) {
            if (minCosts[i] < Integer.MIN_VALUE);
            Queue<Pair> inPath = new ArrayDeque<Pair>();
            for (var element : connectedTowns.get(i)) {
                if (minCosts[element] > prices[i]) inPath.add(new Pair(element, prices[i]));
            }
            while (inPath.size() > 0) {
                Pair considered = inPath.poll();
                int costPrice = considered.price;
                int sellingPrice = prices[considered.idx];
                maxProfit = Math.max(maxProfit, sellingPrice-costPrice);
                if (costPrice>= minCosts[considered.idx]) continue;
                minCosts[considered.idx] = Math.min(minCosts[considered.idx], costPrice);
                int nextCost = Math.min(costPrice, prices[considered.idx]);
                for (var element : connectedTowns.get(considered.idx)) {
                    if (minCosts[element] > nextCost) inPath.add(new Pair(element, nextCost));
                }
            }
/*            TreeMap<Integer, Integer> inPath = new TreeMap<Integer, Integer>();
            for (var element : connectedTowns.get(i)) {
                inPath.put(element, prices[i]);
            }
            while (inPath.size() > 0) {
                int considered = inPath.firstKey();
                int costPrice = inPath.get(considered);
                inPath.remove(considered);
                int sellingPrice = prices[considered];
                maxProfit = Math.max(maxProfit, sellingPrice-costPrice);
                if (visited[considered]) continue;
                int nextCost = Math.min(costPrice, prices[considered]);
                for (var element : connectedTowns.get(considered)) {
                    if (inPath.containsKey(element)) inPath.put(element, Math.min(inPath.get(element), nextCost));
                    else inPath.put(element, nextCost);
                }
                visited[considered] = true;
            }
*/
        }
        return maxProfit;
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

class Pair {
    public Pair() {

    }
    public Pair(int idx, int price) {
        this.idx = idx;
        this.price = price;
    }
    int idx;
    int price; // Cost price
}