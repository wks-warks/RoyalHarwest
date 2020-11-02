//Codeforces 43A 
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;

public class CF43A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int goals = SC.nextInt();
        String[] scoredBy = new String[goals];
        for (int g = 0; g < goals; ++g)
            scoredBy[g] = SC.next();
        String winner = getWinner(scoredBy);
        System.out.println(winner);
    }

    // Computes and returns name of winning team
    static String getWinner(String[] scoredBy) {
        Map<String, Integer> teamScore = new TreeMap<>();
        for (int g = 0; g < scoredBy.length; ++g)
            if (teamScore.containsKey(scoredBy[g]))
                teamScore.put(scoredBy[g], teamScore.get(scoredBy[g])+1);
            else
                teamScore.put(scoredBy[g], 1);
        
        int maxGoals = 0;
        String winner = "";
        for (Map.Entry<String, Integer> team : teamScore.entrySet())
            if (team.getValue() > maxGoals) {
                winner = team.getKey();
                maxGoals = team.getValue();
            }
        return winner;
    }
}