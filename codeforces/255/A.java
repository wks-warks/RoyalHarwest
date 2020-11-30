//Codeforces 255A 
import java.util.Scanner;

public class CF255A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        Focus focus = new Focus();
        int trainings = SC.nextInt();
        int[] repetitions = new int[trainings];
        for (int t = 0; t < trainings; ++t)
            switch (t % 3) {
                case 0 : focus.chestAddition(SC.nextInt());
                    break;
                case 1 : focus.bicepsAddition(SC.nextInt());
                    break;
                case 2 : focus.backAddition(SC.nextInt());
            }
        System.out.println(focus.mostFocussed());
    }
}

class Focus {
    int chest, biceps, back;
    public void chestAddition(int add) {
        chest += add;
    }
    public void bicepsAddition(int add) {
        biceps += add;
    }
    public void backAddition(int add) {
        back += add;
    }

    public String mostFocussed() {
        if (biceps > chest && biceps > back)
            return "biceps";
        else if (chest > biceps && chest > back)
            return "chest";
        return "back";
    }
}