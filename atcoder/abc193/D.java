// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int k = FR.nextInt();
    String takahashi = FR.next();
    String aoki = FR.next();
    double takaWinProbability = getTakaWinProbability(takahashi, aoki, k);
    solution.append(takaWinProbability + "\n");
		PW.print(solution.toString());
    PW.close();
  }

  static double getTakaWinProbability(String takahashi, String aoki, int k) {
    double takaWinProbability = 0.0;
    for (int tLast = 1; tLast <= 9; tLast += 1) {
      for (int aLast = 1; aLast <= 9; aLast += 1) {
        double caseProbability = getCaseProbability(takahashi, tLast, aoki, aLast, k);
        if (takaWinCase(takahashi, tLast, aoki, aLast)) {
          takaWinProbability += caseProbability;
          // System.out.println(caseProbability + " " + tLast + " " + aLast);
        }
      }
    }

    return takaWinProbability;
  }

  static boolean takaWinCase(String takahashi, int tLast, String aoki, int aLast) {
    List<Integer> takaCards = getCards(takahashi.substring(0, 4) + tLast);
    List<Integer> aokiCards = getCards(aoki.substring(0, 4) + aLast);
    int takaScore = getScore(takaCards);
    int aokiScore = getScore(aokiCards);
    return takaScore > aokiScore;
  }

  static int getScore(List<Integer> cards) {
    int score = 0;
    for (int i = 1; i <= 9; i += 1) {
      score += (int) (i * Math.pow(10, cards.get(i)));
    }
    return score;
  }

  static List<Integer> getCards(String hand) {
    List<Integer> cards = new ArrayList<Integer>(Collections.nCopies(10, 0));
    for (var card : hand.toCharArray()) {
      int val = card - '0';
      cards.set(val, cards.get(val) + 1);
    }
    return cards;
  }

  static double getCaseProbability(String takahashi, int tLast, String aoki, int aLast, int k) {
    List<Integer> takaCards = getCards(takahashi.substring(0, 4) + tLast);
    List<Integer> aokiCards = getCards(aoki.substring(0, 4) + aLast);
    for (int i = 1; i <= 9; i += 1) {
      int total = takaCards.get(i) + aokiCards.get(i);
      if (total > k) {
        return 0.0;
      }
    }
    List<Integer> remaining = new ArrayList<Integer>(Collections.nCopies(10, k));
    // if (tLast == 9 && aLast == 8) {
      // System.out.println(remaining);
    // }
    for (int i = 0; i < 4; i += 1) {
      int tValue = takahashi.charAt(i) - '0';
      remaining.set(tValue, remaining.get(tValue) - 1);
      int aValue = aoki.charAt(i) - '0';
      remaining.set(aValue, remaining.get(aValue) - 1);
    }
    int totalRemaining = 0;
    for (int i = 1; i <= 9; i += 1) {
      totalRemaining += remaining.get(i);
    }
    double probability;
    probability = (double) remaining.get(aLast) / totalRemaining;
    remaining.set(aLast, remaining.get(aLast)-1);
    totalRemaining -= 1;
    probability *= (double) remaining.get(tLast) / totalRemaining;

    // if (tLast == 9 && aLast == 8) {
      // System.out.println("Probability" + probability);
      // System.out.println("total remaining" + (totalRemaining + 1));
      // System.out.println(remaining);
    // }
    return probability;

    // if (aLast == tLast) {
      // double probability = (double) remaining.get(aLast) / totalRemaining * (double) (remaining.get(aLast) - 1) / (totalRemaining-1);
      // return probability;
    // }

    // double probability = (double) remaining.get(aLast) / totalRemaining * (double) remaining.get(tLast) / (totalRemaining - 1);
    // return probability;
    // double case2Probability = 0.0;
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