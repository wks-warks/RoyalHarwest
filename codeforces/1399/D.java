//Codeforces 1399D 
import java.util.*;
import java.io.*;

public class CF1399D {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = FR.nextInt();
        while(tests-->0) {
            int slen = FR.nextInt();
            String s= FR.next();
            int[] answer = new int[slen];
            int subseqs = getSubSeqs(s, answer);
            solution.append(subseqs + "\n");
            for (var element : answer) {
                solution.append(element + " ");
            }
            solution.append('\n');
        }
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
		writer.print(solution.toString());
        writer.close();
    }

    static int getSubSeqs(String s, int[] answer) {
        int subseqs = 0;
        Queue<Integer> zeroEnd = new ArrayDeque<Integer>();
        Queue<Integer> oneEnd = new ArrayDeque<Integer>();
        int seq = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                seq = assign(oneEnd, zeroEnd, subseqs);
            } else {
                seq = assign(zeroEnd, oneEnd, subseqs);
            }
            answer[i] = seq;
            subseqs = Math.max(seq, subseqs);
        }
        return subseqs;
    }

    static int assign(Queue<Integer> first, Queue<Integer> second, int total) {
        int seq = 0;
        if (first.isEmpty()) {
            seq = total+1;
        } else {
            seq = first.poll();
        }
        second.add(seq);
        return seq;
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
