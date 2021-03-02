// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      String input = FR.next();
      boolean answer = false;
      for (int i = 0; i < 2; i += 1) {
        for (int j = 0; j < 2; j += 1) {
          for (int k = 0; k < 2; k += 1) {
            answer |= isRegularReplacement(input, bracketType(i), bracketType(j), bracketType(k));
          }
        }
      }
      solution.append(answer ? "YES\n" : "NO\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static char bracketType(int num) {
    if (num == 0) {
      return '(';
    }
    return ')';
  }

  static boolean isRegularReplacement(String input, char replacementA, char replacementB, char replacementC) {
    Deque<Character> bracketSequence = new ArrayDeque<Character>();
    for (var ch : input.toCharArray()) {
      switch(ch) {
        case 'A':
        bracketSequence.add(replacementA);
        break;
        case 'B':
        bracketSequence.add(replacementB);
        break;
        default :
        bracketSequence.add(replacementC);
      }
    }

    return isRegular(bracketSequence);
  }

  static boolean isRegular(Deque<Character> bracketSequence) {
    int leftBrackets = 0;
    for (var bracket : bracketSequence) {
      if (bracket == '(') {
        leftBrackets += 1;
      } else {
        if (leftBrackets == 0) {
          return false;
        }
        leftBrackets -= 1;
      }
    }
    return leftBrackets == 0;
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