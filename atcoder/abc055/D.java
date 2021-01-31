import java.io.*;
import java.util.*;
 
public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));
 
  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    // tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int animalCount = FR.nextInt();
      char[] responses = FR.next().toCharArray();
      String assignment = getAssignment(responses);
      solution.append(assignment + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static String getAssignment(char[] responses) {
    char[] assignment;
    assignment = getAssignment('S', 'W', responses);
    if (assignment != null) return String.valueOf(assignment);
    assignment = getAssignment('S', 'S', responses);
    if (assignment != null) return String.valueOf(assignment);
    assignment = getAssignment('W', 'W', responses);
    if (assignment != null) return String.valueOf(assignment);
    assignment = getAssignment('W', 'S', responses);
    if (assignment != null) return String.valueOf(assignment);
    else return "-1";
  }

  static char[] getAssignment(char first, char second, char[] responses) {
    char[] animals = new char[responses.length];
    animals[0] = first;
    animals[1] = second;
    for (int i = 1; i < animals.length-1; ++i) {
      if (animals[i] == 'S') {
        if (responses[i] == 'o') {
          animals[i+1] = animals[i-1];
        } else {
          if (animals[i-1] == 'S') animals[i+1] = 'W';
          else animals[i+1] = 'S';
        }
      } else {
        if (responses[i] == 'o') {
          if (animals[i-1] == 'S') animals[i+1] = 'W';
          else animals[i+1] = 'S';
        } else {
          animals[i+1] = animals[i-1];
        }
      }
    }
    if (animals[animals.length-1] == 'S') {
      if ((responses[responses.length-1] == 'o' && animals[animals.length-2] != animals[0]) || (responses[responses.length-1] == 'x' && animals[animals.length-2] == animals[0])) return null;
    } else {
      if ((responses[responses.length-1] == 'o' && animals[animals.length-2] == animals[0]) || (responses[responses.length-1] == 'x' && animals[animals.length-2] != animals[0])) return null;
    }
    if (animals[0] == 'S') {
      if ((responses[0] == 'o' && animals[animals.length-1] != animals[1]) || (responses[0] == 'x' && animals[animals.length-1] == animals[1])) return null;
    } else {
      if ((responses[0] == 'o' && animals[animals.length-1] == animals[1]) || (responses[0] == 'x' && animals[animals.length-1] != animals[1])) return null;
    }
    return animals;
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