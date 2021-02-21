// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int n = FR.nextInt();
    int elementCount = 3*n;
    List<Integer> list = new ArrayList<Integer>(3*n);
    for (int e = 0; e < elementCount; e += 1) {
      list.add(FR.nextInt());
    }
    List<Long> leftSum = getSum(0, elementCount-1, list, n);
    List<Long> rightSum = getSum(elementCount-1, 0, list, n);
    long maxScore = getMaxScore(leftSum, rightSum);
    solution.append(maxScore + "\n");
		PW.print(solution.toString());
    PW.close();
  }

  static long getMaxScore(List<Long> leftSum, List<Long> rightSum) {
    long maxScore = Long.MIN_VALUE;
    for (int i = 0; i < leftSum.size(); i += 1) {
      long score = leftSum.get(i) - rightSum.get(rightSum.size()-1-i);
      // System.out.println(leftSum.get(i) + " i " + i + " i " + rightSum.get(i));
      maxScore = Math.max(maxScore, score);
    }
    return maxScore;
  }

  static List<Long> getSum(int start, int end, List<Integer> list, int n) {
    int increment;
    PriorityQueue<Integer> pq;
    if (start < end) {
      increment = 1;
      pq = new PriorityQueue<Integer>();
    } else {
      increment = -1;
      pq = new PriorityQueue<Integer>(Collections.reverseOrder());
    }

    long sum = 0;
    int idx = start;
    for (int i = 0; i < n; i += 1) {
      int num = list.get(idx);
      sum += num;
      pq.add(num);
      idx += increment;
    }

    // System.out.println("MERA" + sum);

    List<Long> sumList = new ArrayList<Long>(n + 1);
    sumList.add(sum);
    for (int i = 0; i < n; i += 1) {
      int insertValue = list.get(idx);
      pq.add(insertValue);
      int removedValue = pq.poll();
      // System.out.println(removedValue + " RM");
      sum = sum + insertValue - removedValue;
      // System.out.println(sum);
      sumList.add(sum);
      idx += increment;
    }
    // for (var val : sumList) {
      // System.out.println(val + " " + (start < end ? "ls" : "rs"));
    // }
    return sumList;
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