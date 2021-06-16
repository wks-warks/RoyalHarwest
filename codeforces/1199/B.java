import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int height = scanner.nextInt();
    int length = scanner.nextInt();

    double depth = computeDepth(height, length);
    System.out.println(depth);
  }

  static double computeDepth(int height, int length) {
    return ((long)length*length - (long)height*height)/(2.0*height);
  }
}