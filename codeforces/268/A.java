// Codeforces 268A
import java.util.Scanner;

public class CF268A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int teams = SC.nextInt();
        int[][] uniforms = new int[teams][2];
        for (int t = 0; t < teams; ++t) {
            uniforms[t][0] = SC.nextInt();
            uniforms[t][1] = SC.nextInt();
        }
        int specialGames = countSpecialGames(uniforms); // Number of games where hosts wear guest uniforms
        System.out.println(specialGames);
    }

    // Counts and returns number of games where hosts wear guest uniforms
    static int countSpecialGames(int[][] uniforms) {
        int specialGames = 0;
        for (int i = 0; i < uniforms.length; ++i)
            for (int j = 0; j < uniforms.length; ++j)
                if (uniforms[i][0] == uniforms[j][1]) // Note that any team does not have same coloured home and guest uniforms
                    specialGames += 1;
        return specialGames;
    }
}