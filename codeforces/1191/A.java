import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int hp = scanner.nextInt();
    int change = getChange(hp);
    char newCategory = getCategory(hp + change);
    System.out.println(change + " " + newCategory);
  }

  static int getChange(int hp) {
    switch (hp % 4) {
      case 1: return 0;
      case 3: return 2;
      case 2: return 1;
      case 0: return 1;
      default: return -1;
    }
  }

  static char getCategory(int hp) {
    switch(hp % 4) {
      case 1: return 'A';
      case 3: return 'B';
      case 2: return 'C';
      case 0: return 'D';
      default: return ' ';
    }
  }
}