//Codeforces 698A 
import java.util.Scanner;

public class CF698A {
    static final Scanner SC = new Scanner(System.in);
    
    static int days;
    static int[] optionsState;
    public static void main(String[] args) {
        days = SC.nextInt();
        optionsState = new int[days]; // 0: Rest, 1: Rest/Contest, 2: Rest/Gym, 3: Anything
        for (int day = 0; day < days; ++day)
            optionsState[day] = SC.nextInt();
        
        int minimumRest = computeMinimumRest(days, 'R');
        System.out.println(minimumRest);
    }

    // Computes and returns minimum number of days on which Vasya must rest
    static int computeMinimumRest(int day, char prevState) {
        // prevState : 'G'-Gym, 'C'-Contest, 'R'-Rest/Irrelevant
        if (day == 0)
            return 0;

        int options = optionsState[day-1];
        if (prevState == 'G')
            switch(options) {
                case 0  : return computeMinimumRest(day-1, 'R') + 1;
                case 1  : return computeMinimumRest(day-1, 'C');
                case 2  : return computeMinimumRest(day-1, 'R') + 1;
                default : return computeMinimumRest(day-1, 'C');
            }
        else if (prevState == 'C')
            switch(options) {
                case 0  : return computeMinimumRest(day-1, 'R') + 1;
                case 1  : return computeMinimumRest(day-1, 'R') + 1;
                case 2  : return computeMinimumRest(day-1, 'G');
                default : return computeMinimumRest(day-1, 'G');
            }
        else
            switch(options) {
                case 0  : return computeMinimumRest(day-1, 'R') + 1;
                case 1  : return computeMinimumRest(day-1, 'C');
                case 2  : return computeMinimumRest(day-1, 'G');
                default : return computeMinimumRest(day-1, 'R');
            }
    }
}
