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
      String s1 = FR.next();
      String s2 = FR.next();
      String lcm = getLCM(s1, s2);
      solution.append(lcm+"\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static String getLCM(String s1, String s2) {
    int totalLen = LCM(s1.length(), s2.length());
    StringBuilder sb1 = new StringBuilder();
    for (int i = 0; i < totalLen/s1.length(); i += 1) {
      sb1.append(s1);
    }
    StringBuilder sb2 = new StringBuilder();
    for (int i = 0; i < totalLen/s2.length(); i += 1) {
      sb2.append(s2);
    }
    if (sb1.toString().equals(sb2.toString())) return sb1.toString();
    else return "-1";
  }

  static int LCM(int num1, int num2) {
    return num1 / GCD(num1, num2) * num2;
  }
  
  static int GCD(int num1, int num2) {
    if (num2 == 0) return num1;
    else return GCD(num2, num1 % num2);
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