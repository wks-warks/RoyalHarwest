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
            int totalTime = FR.nextInt();
            travelTimes = new int[cities][cities];
            for (int i = 0; i < cities; ++i) 
                for (int j = 0; j < cities; ++j)
                    travelTimes[i][j] = FR.nextInt();


            int routesWithGivenTotalTime = routesWithGivenTotalTime(cities, totalTime);
            solution.append(routesWithGivenTotalTime);
        }
        System.out.println(solution.toString());
    }
    static int[][] travelTimes;

    // Computes and returns number of routes with total time = given total time
    static int routesWithGivenTotalTime(int cities, int totalTime) {
        ArrayList<Integer> visited = new ArrayList<>();
        visited.add(0);
        int answer = countRoutes(visited, 0, totalTime);
        return answer;
    }

    // Counts and returns number of eligible routes
    static int countRoutes(ArrayList<Integer> visited, int timeTaken, int totalTime) {
        if (visited.size() == travelTimes.length)
            return (timeTaken + travelTimes[visited.get(visited.size()-1)][0] == totalTime) ? 1 : 0;

        int routes = 0;
        for (int i = 0; i < travelTimes.length; ++i) {
            boolean add = !visited.contains(i);
            if (add && travelTimes[visited.get(visited.size()-1)][i] + timeTaken <= totalTime) {
                visited.add(i);
                routes += countRoutes(visited, timeTaken + travelTimes[visited.get(visited.size()-2)][i], totalTime);
                visited.remove(visited.size()-1);
            }
        }
        return routes;
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