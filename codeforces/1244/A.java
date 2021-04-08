import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tests = scanner.nextInt();
        
        while (tests-->0) {
            int lectures = scanner.nextInt();
            int practicals = scanner.nextInt();
            int lecturesPerPen = scanner.nextInt();
            int practicalsPerPencil = scanner.nextInt();
            int caseSize = scanner.nextInt();
            
            printAnswer(lectures, practicals, lecturesPerPen, practicalsPerPencil, caseSize);
        }
    }
    
    static void printAnswer(int lectures, int practicals, int lecturesPerPen, int practicalsPerPencil, int caseSize) {
        int pensRequired = LIG(lectures, lecturesPerPen);
        int pencilsRequired = LIG(practicals, practicalsPerPencil);
        
        if (pensRequired + pencilsRequired > caseSize) {
            System.out.println(-1);
        } else {
            System.out.println(pensRequired + " " + pencilsRequired);
        }
    }
    
    static int LIG(int numerator, int denominator) {
        return (numerator + denominator - 1) / denominator;
    }
}