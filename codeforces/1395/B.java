import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int x = Integer.parseInt(st.nextToken());
    int y = Integer.parseInt(st.nextToken());
    Coordinate start = new Coordinate(x, y);

    List<Coordinate> coordinates = getCoordinates(n, m, start);
    for (var coordinate : coordinates) {
      pw.println(coordinate.x + " " + coordinate.y);
    }

    pw.close();
  }

  static List<Coordinate> getCoordinates(int n, int m, Coordinate start) {
    List<Coordinate> coordinates = new ArrayList<Coordinate>();
    coordinates.add(start);
    coordinates.add(new Coordinate(1, start.y));
    for (int i = 1; i <= n; i++) {
      int begin = (i & 1) == 1 ? 1 : m;
      int end = (i & 1) == 1 ? m+1 : 0;
      int increment = (i & 1) == 1 ? 1 : -1;
      
      for (int j = begin; j != end; j += increment) {
        if (j == start.y && (i == 1 || i == start.x)) {
          continue;
        }
        coordinates.add(new Coordinate(i, j));
      }
    }
    return coordinates;
  }
}

class Coordinate {
  int x;
  int y;
  public Coordinate() {
  }
  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }
}