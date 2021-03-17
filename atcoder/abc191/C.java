import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int height = Integer.parseInt(st.nextToken());
    int width = Integer.parseInt(st.nextToken());

    List<String> grid = new ArrayList<String>(height);
    for (int h = 0; h < height; h += 1) {
      grid.add(br.readLine());
    }

    int sides = countSides(grid);

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.println(sides);
    pw.close();
  }

  static int countSides(List<String> grid) {
    int corners = 0;
    
    for (int r = 1; r < grid.size(); r += 1) {
      for (int c = 1; c < grid.get(r).length(); c += 1) {
        int blacks = 0;

        blacks += grid.get(r).charAt(c) == '#' ? 1 : 0;
        blacks += grid.get(r-1).charAt(c) == '#' ? 1 : 0;
        blacks += grid.get(r).charAt(c-1) == '#' ? 1 : 0;
        blacks += grid.get(r-1).charAt(c-1) == '#' ? 1 : 0;

        corners += (blacks & 1) == 1 ? 1 : 0;
      }
    }

    return corners;
  }
}