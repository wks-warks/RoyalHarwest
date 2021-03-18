import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tests = Integer.parseInt(br.readLine());

    StringBuilder output = new StringBuilder();
    StringTokenizer st;
    for (int t = 0; t < tests; t += 1) {
      st = new StringTokenizer(br.readLine());
      int columns = Integer.parseInt(st.nextToken());
      int whiteFirst = Integer.parseInt(st.nextToken());
      int whiteSecond = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(br.readLine());
      int whiteDominoes = Integer.parseInt(st.nextToken());
      int blackDominoes = Integer.parseInt(st.nextToken());

      boolean dominoesPlaced = checkPlacability(columns, whiteFirst, whiteSecond, whiteDominoes, blackDominoes);
      output.append(dominoesPlaced ? "Yes\n" : "No\n");
    }
    
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(output.toString());
    pw.close();
  }

  static boolean checkPlacability(int columns, int whiteFirst, int whiteSecond, int whiteDominoes, int blackDominoes) {
    int blackFirst = columns - whiteFirst;
    int blackSecond = columns - whiteSecond;

    boolean blackPlaced = checkOneColour(blackFirst, blackSecond, blackDominoes);
    boolean whitePlaced = checkOneColour(whiteFirst, whiteSecond, whiteDominoes);

    return blackPlaced && whitePlaced;
  }

  static boolean checkOneColour(int first, int second, int dominoes) {
    int lesser = Math.min(first, second);
    int greater = Math.max(first, second);
    dominoes -= lesser;

    int difference = greater - lesser;
    dominoes -= difference >> 1;

    return dominoes <= 0;
  }
}