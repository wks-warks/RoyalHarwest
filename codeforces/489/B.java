// Codeforces 489B
import java.util.Scanner;
import java.util.Arrays;

public class CF489B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int boys = SC.nextInt(); // Number of boys
        int[] boysSkills = new int[boys];
        for (int b = 0; b < boys; ++b)
            boysSkills[b] = SC.nextInt();
        
        int girls = SC.nextInt();
        int[] girlsSkills = new int[girls];
        for (int g = 0; g < girls; ++g)
            girlsSkills[g] = SC.nextInt();
        int maxPairs = computeMaxPairsPossible(boysSkills, girlsSkills);
        System.out.println(maxPairs);
    }

    // Computes and returns maximum number of pairs possible
    static int computeMaxPairsPossible(int[] boysSkills, int[] girlsSkills) {
        Arrays.sort(boysSkills);
        Arrays.sort(girlsSkills);
        int boyIdx = 0; // Index of boy in consideration
        int girlIdx = 0; // Index of girl in consideration
        int pairs = 0;
        while (boyIdx < boysSkills.length && girlIdx < girlsSkills.length) { // While there exist at least 1 boy and girl each to be considered
            int consideredBoysSkill = boysSkills[boyIdx];
            int consideredGirlsSkill = girlsSkills[girlIdx];
            int skillDifference = Math.abs(consideredBoysSkill - consideredGirlsSkill);
            if (skillDifference <= 1) {
                pairs += 1;
                boyIdx += 1;
                girlIdx += 1;
            }
            // Considering next boy/ girl such that the skill difference is closed if possible
            else if (consideredBoysSkill < consideredGirlsSkill)
                boyIdx += 1;
            else
                girlIdx += 1;
        }
        return pairs;
    }
}