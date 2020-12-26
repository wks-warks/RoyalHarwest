import java.io.*;
import java.util.*;

class Main {
    static final FastReader FR = new FastReader();

    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = 1;
        // tests = FR.nextInt();
        for (int t = 0; t < tests; ++t) {
            String aliceDeck = FR.next();
            String bobDeck = FR.next();
            String charlieDeck = FR.next();
            char winner = getWinnerInitial(aliceDeck, bobDeck, charlieDeck);
            solution.append(winner);
        }
        System.out.println(solution.toString());
    }

    // Computes and returns the winner of the game
    static char getWinnerInitial(String aliceDeck, String bobDeck, String charlieDeck) {
        char player = 'a';
        int aPtr, bPtr, cPtr;
        aPtr = bPtr = cPtr = 0;
        while(true) {
            switch(player) {
                case 'a':
                    if (aPtr == aliceDeck.length()) {
                        return 'A';
                    }
                    player = aliceDeck.charAt(aPtr++);
                    break;
                case 'b':
                    if (bPtr == bobDeck.length()) {
                        return 'B';
                    }
                    player = bobDeck.charAt(bPtr++);
                    break;
                default :
                    if (cPtr == charlieDeck.length()) {
                        return 'C';
                    }
                    player = charlieDeck.charAt(cPtr++);
            }
        }
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