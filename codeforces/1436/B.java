import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class Main {
  public static void main(String[] args) {
    setPrimes(100_000);

    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    for (int t = 0; t < tests; t++) {
      int squareSize = scanner.nextInt();
      List<List<Integer>> primeSquare = getPrimeSquare(squareSize);
    
      for (int r = 0; r < squareSize; r++) {
        for (int c = 0; c < squareSize; c++) {
          System.out.print(primeSquare.get(r).get(c) + " ");
        }
        System.out.println();
      }
    }
  }

  static List<List<Integer>> getPrimeSquare(int squareSize) {
    List<List<Integer>> primeSquare = new ArrayList<List<Integer>>(squareSize);
    for (int r = 0; r < squareSize; r++) {
      primeSquare.add(new ArrayList<Integer>(Collections.nCopies(squareSize, 1)));
    }

    int sideSum = primes.ceiling(squareSize);
    while (primes.contains(sideSum - (squareSize - 1))) {
      sideSum = primes.higher(sideSum);
    }
    int sideValue = sideSum - (squareSize - 1);

    for (int r = 0; r < squareSize-1; r++) {
      primeSquare.get(r).set(squareSize-1, sideValue);
    }
    for (int c = 0; c < squareSize - 1; c++) {
      primeSquare.get(squareSize-1).set(c, sideValue);
    }

    int lastSideSum = primes.higher((squareSize - 1) * sideValue);
    while (primes.contains(lastSideSum - (squareSize - 1) * sideValue)) {
      lastSideSum = primes.higher(lastSideSum);
    }

    int vertex = lastSideSum - (squareSize - 1) * sideValue;
    primeSquare.get(squareSize - 1).set(squareSize - 1, vertex);

    return primeSquare;
  }

  static TreeSet<Integer> primes;
  static void setPrimes(int upto) {
    primes = new TreeSet<Integer>();

    List<Boolean> isPrime = new ArrayList<Boolean>(Collections.nCopies(upto+1, true));
    for (int cdate = 2; cdate * cdate <= upto; cdate++) {
      if (isPrime.get(cdate)) {
        primes.add(cdate);

        for (int cpsite = cdate * cdate; cpsite <= upto; cpsite += cdate) {
          isPrime.set(cpsite, false);
        }
      }
    }

    for (int cdate = (int) Math.sqrt(upto); cdate <= upto; cdate++) {
      if (isPrime.get(cdate)) {
        primes.add(cdate);
      }
    }
  }
}