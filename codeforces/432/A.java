// Codeforces 432A
import java.util.Scanner;

public class CF432A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int students = SC.nextInt(); // Number of students
        int contestsDesired = SC.nextInt(); // Any team must be able to compete in at least this many contests together
        int[] priorParticipation = new int[students];
        for (int i = 0; i < students; ++i)
            priorParticipation[i] = SC.nextInt();
        int maxTeamsPossible = computeMaxTeamsPossible(contestsDesired, priorParticipation);
        System.out.println(maxTeamsPossible);        
    }

    // Computes and returns maximum possible teams that can compete for the desirable amount of times
    static int computeMaxTeamsPossible(int contestsDesired, int[] priorParticipation) {
        int maxParticipation = 5;
        int eligiblePlayers = 0;
        for (int i = 0; i < priorParticipation.length; ++i)
            if (maxParticipation - priorParticipation[i] >= contestsDesired)
                eligiblePlayers += 1;
        return eligiblePlayers / 3; // Teams need to be of size 3
    }
}