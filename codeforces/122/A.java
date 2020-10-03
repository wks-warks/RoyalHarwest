// Codeforces 122A
import java.util.Scanner;
import java.util.ArrayList;

public class CF122A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        ArrayList<Integer> luckyNumbers = getLuckyNumsTill(1000);
        int num = SC.nextInt();
        boolean isAlmostLucky = checkDivisibilityByLucky(num, luckyNumbers);
        out(isAlmostLucky);
    }

    // Computes the numbers that are lucky and returns an ArrayList of those numbers 
    static ArrayList<Integer> getLuckyNumsTill(int limit) {
        ArrayList<Integer> luckyNumbers = new ArrayList<Integer>();
        appendList(luckyNumbers, ""); // Adds all lucky numbers to the list
        return luckyNumbers;
    }

    // Adds all lucky numbers starting with the given string to the list
    static void appendList(ArrayList<Integer> luckyNumbers, String startingWith) {
        String endFour = startingWith + 4; // Adds 4 to the end of the number-string
        int number = Integer.parseInt(endFour);
        if (number <= 1000)
            luckyNumbers.add(number);
        else
            return;
        appendList(luckyNumbers, endFour); // Performs the same operation with endFour

        String endSeven = startingWith + 7; // Adds 7 to the end of the number-string
        number = Integer.parseInt(endSeven);
        if (number <= 1000)
            luckyNumbers.add(number);
        else
            return;        
        appendList(luckyNumbers, endSeven);// Performs the same operation with endSeven
    }
    

    // Checks if the number is divisible by any lucky number
    static boolean checkDivisibilityByLucky(int num, ArrayList<Integer> luckyNumbers) {
        for (int ln = 0; ln < luckyNumbers.size(); ++ln)
            if (num % luckyNumbers.get(ln) == 0) // Divisible by a lucky number
                return true;
        return false; // Not divisible by any lucky number
    }

    // Prints output based on condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}