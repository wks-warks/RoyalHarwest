import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int height = scanner.nextInt();
    int width = scanner.nextInt();
    int colors = scanner.nextInt();
    List<Integer> colorCount = new ArrayList<Integer>(colors);
    for (int c = 0; c < colors; c += 1) {
      colorCount.add(scanner.nextInt());
    }
    List<List<Integer>> grid = getPaintedGrid(height, width, colorCount);
    for (var row : grid) {
      for (var element : row) {
        System.out.print(element + " ");
      }
      System.out.println();
    }
  }

  static List<List<Integer>> getPaintedGrid(int height, int width, List<Integer> colorCount) {
    List<List<Integer>> grid = new ArrayList<List<Integer>>(height);
    int color = 0;
    for (int h = 0; h < height; h += 1) {
      grid.add(new ArrayList<Integer>(width));
      for (int w = 0; w < width; w += 1) {
        grid.get(h).add(color+1);
        colorCount.set(color, colorCount.get(color)-1);
        if (colorCount.get(color) == 0) {
          color += 1;
        }
      }
      if ((h&1) == 1) {
        Collections.reverse(grid.get(h));
      }
    }
    return grid;
  }
}