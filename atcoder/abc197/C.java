// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();

    int sequenceSize = FR.nextInt();
    List<Integer> sequence = new ArrayList<Integer>(sequenceSize);
    for (int s = 0; s < sequenceSize; s++) {
      sequence.add(FR.nextInt());
    }
    
    minXOR = Integer.MAX_VALUE;
    setMinXOR(0, sequence, 0, sequenceSize-1);
    solution.append(minXOR + "\n");


		PW.print(solution.toString());
    PW.close();
  }

  static int minXOR;
  static void setMinXOR(int prevXOR, List<Integer> sequence, int start, int end) {
    if (start > end) {
      minXOR = Math.min(minXOR, prevXOR);
      return;
    }

    int orValue = 0;
    for (int i = start; i <= end; i++) {
      orValue |= sequence.get(i);
      setMinXOR(prevXOR ^ orValue, sequence, i+1, end);
    }
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
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