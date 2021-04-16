import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int experienced = scanner.nextInt();
    int newbies = scanner.nextInt();
    int teamCount = getTeamCount(experienced, newbies);
    System.out.println(teamCount);
  }

  static int getTeamCount(int experienced, int newbies) {
    if (Math.min(experienced, newbies) == 0) {
      return 0;
    }
    
    if (experienced == newbies) {
      if (experienced <= 1) {
        return 0;
      } else if (experienced == 2) {
        return 1;
      } else {
        return ((experienced / 3) << 1) + getTeamCount(experienced % 3, newbies % 3);
      }
    }
    
    if (experienced > newbies) {
      int newTeams = Math.min(experienced - newbies, newbies);
      experienced -= newTeams << 1;
      newbies -= newTeams;
      return newTeams + getTeamCount(experienced, newbies);
    } else {
      int newTeams = Math.min(newbies - experienced, experienced);
      newbies -= newTeams << 1;
      experienced -= newTeams;
      return newTeams + getTeamCount(experienced, newbies);
    }
  }
}