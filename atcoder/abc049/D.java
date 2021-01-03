import java.io.*;
import java.util.*;
 
class Main {
    static final FastReader FR = new FastReader();
 
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            int cities = FR.nextInt();
            int roads = FR.nextInt();
            int rails = FR.nextInt();
            List<Set<Integer>> roadConnects = new ArrayList<Set<Integer>>(cities);
            List<Set<Integer>> railConnects = new ArrayList<Set<Integer>>(cities);
            for (int i = 0; i < cities; i += 1) {
                roadConnects.add(new HashSet<Integer>());
                roadConnects.get(i).add(i);
                railConnects.add(new HashSet<Integer>());
                railConnects.get(i).add(i);
            }
            for (int i = 0; i < roads; i += 1) {
                int c1 = FR.nextInt();
                int c2 = FR.nextInt();
                roadConnects.get(c1-1).add(c2-1);
                roadConnects.get(c2-1).add(c1-1);
            }
            for (int i = 0; i < rails; i += 1) {
                int c1 = FR.nextInt();
                int c2 = FR.nextInt();
                railConnects.get(c1-1).add(c2-1);
                railConnects.get(c2-1).add(c1-1);
            }
            int[] railPlusRoad = getRailPlusRoad(cities, roadConnects, railConnects);
            for (var element : railPlusRoad) {
                solution.append(element);
                solution.append(' ');
            }
        }
      	PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
		writer.println(solution.toString());
        writer.close();
    }
 
    static int[] getRailPlusRoad(int cities, List<Set<Integer>> roadConnects, List<Set<Integer>> railConnects) {
        boolean[] roadVisits = new boolean[cities];
        boolean[] railVisits = new boolean[cities];
        Map<Integer, Integer> roadIdxMap = new HashMap<Integer, Integer>();
 
        int roadSetIdx = 0;
        for (int i = 0; i < cities; i += 1) {
            if (roadVisits[i])
                continue;
            Queue<Integer> queue = new ArrayDeque<Integer>();
            queue.add(i);
            while (queue.size() > 0) {
                int idx = queue.poll();
                for (var element : roadConnects.get(idx)) {
                    if (roadVisits[element])
                        continue;
                    roadVisits[element] = true;
                    queue.add(element);
                    roadIdxMap.put(element, roadSetIdx);
                }
            }
            roadSetIdx += 1;
        }


        int[] railPlusRoad = new int[cities];
        int railSetIdx = 0;
        for (int i = 0; i < cities; i += 1) {
            if (railVisits[i])
                continue;
            Queue<Integer> queue = new ArrayDeque<Integer>();
            queue.add(i);
            Queue<Integer> thisSet = new ArrayDeque<Integer>();
            Map<Integer, Integer> count = new HashMap<Integer, Integer>();
            while (queue.size() > 0) {
                int idx = queue.poll();
                for (var element : railConnects.get(idx)) {
                    if (railVisits[element])
                        continue;
                    railVisits[element] = true;
                    queue.add(element);
                    thisSet.add(element);
                    int roadIdx = roadIdxMap.get(element);
                    if (count.containsKey(roadIdx))
                        count.put(roadIdx, count.get(roadIdx)+1);
                    else
                        count.put(roadIdx, 1);
                }
            }
            for (var element : thisSet) {
                railPlusRoad[element] = count.get(roadIdxMap.get(element));
            }
            railSetIdx += 1;
        }
 
        Map<Long, Integer> count = new HashMap<Long, Integer>();
        return railPlusRoad;
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