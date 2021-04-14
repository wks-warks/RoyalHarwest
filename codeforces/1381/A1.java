import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
    int tests = Integer.parseInt(br.readLine());

    while (tests-->0) {
      int strLen = Integer.parseInt(br.readLine());
      String given = br.readLine();
      String desired = br.readLine();

      printSolution(strLen, given, desired);
    }

    pw.close();
  }

  static List<Integer> solution;
  static void printSolution(int strLen, String given, String desired) {
    solution = new ArrayList<Integer>();
    
    SubstringData leftSubstring = new SubstringData(false, 0);
    
    for (int ptr = strLen - 1; ptr >= 0; ptr -= 1) {
      settle(ptr, given, desired, leftSubstring);
    }

    pw.print(solution.size() + " ");
    for (var index : solution) {
      pw.print(index + " ");
    }

    // Checker.result(given, solution);
    pw.println();
  }

  static void settle(int ptr, String given, String desired, SubstringData leftSubstring) {
    // if (leftSubstring.invertedStatus() == (given.charAt() != desired.charAt(ptr))) {
    int givenPtr = leftSubstring.invertedStatus() ? leftSubstring.getBeginning() - ptr : leftSubstring.getBeginning() + ptr;
    // System.out.print(leftSubstring.invertedStatus() + "  ");
    // System.out.println(givenPtr + "  " + ptr + " eff" + leftSubstring.getBeginning());
    if (leftSubstring.invertedStatus() != (given.charAt(givenPtr) != desired.charAt(ptr))) {
      // System.out.println("LAAHOL VILAQUAT");
      // System.out.println(leftSubstring.invertedStatus());
      // System.out.println(given.charAt(givenPtr) + "   " + desired.charAt(ptr));
      // Fiddle with the next line and see (also, the previous line as well)
      if (leftSubstring.invertedStatus() != (given.charAt(leftSubstring.getBeginning()) == desired.charAt(ptr))) {
        solution.add(1);
      }

      solution.add(ptr+1);
      // Set leftSubstring.beginning
      if (leftSubstring.invertedStatus()) {
        leftSubstring.setBeginning(leftSubstring.getBeginning() - ptr);
      } else {
        leftSubstring.setBeginning(leftSubstring.getBeginning() + ptr);
      }

      leftSubstring.setInverted(!leftSubstring.invertedStatus());
    }
  }
}

class Checker {
  static void result(String initial, List<Integer> transform) {
    StringBuilder initialSB = new StringBuilder(initial);

    StringBuilder initialCopy = new StringBuilder(initial);
    for (int i = 0; i < transform.size(); i += 1) {
      for (int j = 0; j < transform.get(i); j += 1) {
        initialSB.setCharAt(j, initialCopy.charAt(transform.get(i) -1 - j) == '1' ? '0' : '1');
      }
      initialCopy = new StringBuilder();
      for (int x = 0; x < initialSB.length(); x += 1) {
        initialCopy.append(initialSB.charAt(x));
      }
    }

    System.out.println(initialSB.toString());
  }
}

class SubstringData {
  private boolean inverted; // Status of substring from beginning upto the last unresolved point
  private int beginning;

  public SubstringData(boolean inverted, int beginning) {
    this.inverted = inverted;
    this.beginning = beginning;
  }

  public boolean invertedStatus() {
    return inverted;
  }
  public int getBeginning() {
    return beginning;
  }

  public void setInverted(boolean inverted) {
    this.inverted = inverted;
  }
  public void setBeginning(int beginning) {
    this.beginning = beginning;
  }
}

/*

0110011011
1000110100

1
1110011011

10
0001100100
0010011000

8
1110011000
0110011100

7
0001100000
0011000000

1
1001100000

2
1001100000

1
000110000
*/