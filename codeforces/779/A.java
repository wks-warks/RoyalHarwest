//Codeforces 779A 
import java.util.Scanner;

public class CF779A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int studentsPerGroup = SC.nextInt();
        int[] perfCount1 = new int[5];
        for (int i = 0; i < studentsPerGroup; i += 1) {
            int performance = SC.nextInt();
            perfCount1[performance-1] += 1;
        }
        int[] perfCount2 = new int[5];
        for (int i = 0; i < studentsPerGroup; i += 1) {
            int performance = SC.nextInt();
            perfCount2[performance-1] += 1;
        }

        int exchanges = 0;
        for (int i = 0; i < 5; i += 1) {
            int sum = perfCount1[i] + perfCount2[i];
            if (sum % 2 != 0) {
                exchanges = -1;
                break;
            }
            exchanges += (Math.abs(perfCount1[i] - perfCount2[i])) / 2;
        }
        System.out.println(exchanges > 0 ? exchanges/2 : exchanges);
    }
}