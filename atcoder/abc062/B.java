import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int height = scanner.nextInt();
    int width = scanner.nextInt();
    List<List<Character>> boxedImage = new ArrayList<List<Character>>(height+2);
    boxedImage.add(new ArrayList<Character>(Collections.nCopies(width+2, '#')));
    for (int h = 1; h <= height; h += 1) {
      boxedImage.add(new ArrayList<Character>(width+2));
      String boxedImageRow = "#" + scanner.next() + "#";
      for (char pixel : boxedImageRow.toCharArray()) {
        boxedImage.get(h).add(pixel);
      }
    }
    boxedImage.add(new ArrayList<Character>(Collections.nCopies(width+2, '#')));
    
    for (List<Character> row : boxedImage) {
      for (char pixel : row) {
        System.out.print(pixel);
      }
      System.out.println();
    }
  }
}