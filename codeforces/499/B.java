//Codeforces 499B 
import java.util.Scanner;
import java.util.HashMap;

public class CF499B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int lectureLength = SC.nextInt(); // Words in lecture
        int languageLength = SC.nextInt(); // Words in each language
        HashMap<String, String> encoding = new HashMap<>();
        for (int i = 0; i < languageLength; ++i) {
            String lang1Word = SC.next();
            String lang2Word = SC.next();
            String encodedWord = (lang2Word.length() < lang1Word.length()) ? lang2Word : lang1Word;
            encoding.put(lang1Word, encodedWord);
        }
        String[] lecture = new String[lectureLength]; // Contents of the lecture
        for (int i = 0; i < lectureLength; ++i)
            lecture[i] = SC.next();
        String[] lectureEncoding = getEncoding(lecture, encoding);
        out(lectureEncoding);
    }

    // Finds and returns encoded lecture
    static String[] getEncoding(String[] lecture, HashMap<String, String> encoding) {
        String[] encodedLecture = new String[lecture.length];
        for (int i = 0; i < lecture.length; ++i)
            encodedLecture[i] = encoding.get(lecture[i]);
        return encodedLecture;
    }

    // Prints output corresponding to lecture encoding
    static void out(String[] lectureEncoding) {
        for (int i = 0; i < lectureEncoding.length; ++i)
            System.out.print(lectureEncoding[i] + " ");
    }
}
