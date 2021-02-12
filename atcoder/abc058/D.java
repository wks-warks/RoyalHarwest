import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  
  static int mod = 1_000_000_007;
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int parallenToY = scanner.nextInt();
    int parallenToX = scanner.nextInt();
    List<Integer> xCoordinates = new ArrayList<Integer>(parallenToY);
    for (int c = 0; c < parallenToY; c += 1) {
      xCoordinates.add(scanner.nextInt());
    }
    List<Integer> yCoordinates = new ArrayList<Integer>(parallenToX);
    for (int c = 0; c < parallenToX; c += 1) {
      yCoordinates.add(scanner.nextInt());
    }

    int moddedAreaSum = getModdedAreaSum(xCoordinates, yCoordinates);
    System.out.println(moddedAreaSum);
  }

  static int getModdedAreaSum(List<Integer> xCoordinates, List<Integer> yCoordinates) {
    int xSigma = getSigma(xCoordinates);
    int ySigma = getSigma(yCoordinates);
    return (int) ((long) xSigma * ySigma % mod);
  }

  static int getSigma(List<Integer> coordinates) {
    int sigma = 0;
    for (int i = 0; i < coordinates.size(); i += 1) {
      int add = (int) ((long)(i)* coordinates.get(i) % mod);
      int remove = (int) ((long) (coordinates.size()-1-i) * coordinates.get(i) % mod);
      sigma = (int) (((long) sigma + add) % mod);
      sigma = (int) (((long) sigma - remove + mod) % mod);
    }
    return sigma;
  }
}