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
      int len = FR.nextInt();
      String b = FR.next();
      String a = geta(b);
      solution.append(a + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static String geta(String b) {
    StringBuilder a = new StringBuilder();
    int dPrev = -1;
    for (var element : b.toCharArray()) {
      if (element == '0') {
        switch(dPrev) {
          case 0: a.append('1');
                  dPrev = 1;
                  break;
          case 1: a.append('0');
                  dPrev = 0;
                  break;
          case 2: a.append('1');
                  dPrev = 1;
                  break;
          default:  a.append('1');
                    dPrev = 1;
        }
      } else {
        switch(dPrev) {
          case 0: a.append('1');
                  dPrev = 2;
                  break;
          case 1: a.append('1');
                  dPrev = 2;
                  break;
          case 2: a.append('0');
                  dPrev = 1;
                  break;
          default:  a.append('1');
                    dPrev = 2;
        }
      }
    }
    return a.toString();
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