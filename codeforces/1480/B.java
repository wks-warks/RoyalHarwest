// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int heroAttack = FR.nextInt();
      int heroHP = FR.nextInt();
      Fighter hero = new Fighter(heroAttack, heroHP);
      int enemyCount = FR.nextInt();
      List<Fighter> enemies = new ArrayList<Fighter>(enemyCount);
      for (int e = 0; e < enemyCount; e += 1) {
        enemies.add(new Fighter());
        enemies.get(e).setAttack(FR.nextInt());
      }
      for (int e = 0; e < enemyCount; e += 1) {
        enemies.get(e).setHP(FR.nextInt());
      }
      Collections.sort(enemies, new Fighter());
      boolean villageSaved = areEnemiesDefeatable(hero, enemies);
      solution.append(villageSaved ? "YES\n" : "NO\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static boolean areEnemiesDefeatable(Fighter hero, List<Fighter> enemies) {
    for (var enemy : enemies) {
      if (hero.getHP() <= 0 || hero.battleResult(enemy) < 0) {
        return false;
      }
    }
    return true;
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException  e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e)  {
        e.printStackTrace();
      }
      return str;
    }
  }
}

class Fighter implements Comparator<Fighter> {
  private int attackPower, healthPoints;
  public Fighter() {

  }
  public Fighter(int attackPower, int healthPoints) {
    setAttack(attackPower);
    setHP(healthPoints);
  }

  public void setAttack(int attackPower) {
    this.attackPower = attackPower;
  }
  public void setHP(int healthPoints) {
    this.healthPoints = healthPoints;
  }
  public int getAttack() {
    return attackPower;
  }
  public int getHP() {
    return healthPoints;
  }

  public int battleResult(Fighter opponent) {
    int movesToKill = LIG(opponent.getHP(), this.getAttack());
    int movesToDeath = LIG(this.getHP(), opponent.getAttack());
    if (movesToDeath < movesToKill) {
      return -1;
    } else {
      int newHP = this.getHP() - opponent.getAttack() * movesToKill;
      this.setHP(newHP);
      return 1;
    }
  }

  private int LIG(int numerator, int denominator) {
    return (numerator + denominator - 1) / denominator;
  }

  @Override
  public int compare(Fighter first, Fighter second) {
    if (first.getAttack() > second.getAttack()) {
      return 1;
    } else if (first.getAttack() == second.getAttack()) {
      return 0;
    } else {
      return -1;
    }
  }
}