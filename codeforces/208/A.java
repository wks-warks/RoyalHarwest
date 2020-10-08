// Codeforces 208A
import java.util.Scanner;

public class CF208A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String remix = SC.next();
        String actualWords = getActualWords(remix);
        System.out.println(actualWords);
    }

    // Gets actual words from the remix provided
    static String getActualWords(String remix) {
        StringBuilder sb = new StringBuilder();
        int subStart = 0, subEnd = 0; // Start and end of substring to be appended
        int uptoIdx = remix.length();
        for (int i = 0; i < uptoIdx; ++i) {
            int skip = skipWUBs(remix, i, uptoIdx);
            if (skip > 0) {
                subEnd = i;
                sb.append(remix.substring(subStart, subEnd));
                if (subEnd > 0) // No ' ' to be added at the beginning
                    sb.append(' ');
                i += skip;
                subStart = i;
            }
        }
        sb.append(remix.substring(subStart, remix.length()));
        return sb.toString();
    }

    // Returns characters to be skipped to remove WUBs
    static int skipWUBs(String remix, int idx, int uptoIdx) {
        int skip = 0;
        boolean foundWUB;
        if (idx + 3 > uptoIdx)
            foundWUB = false;
        else {
            boolean checkW = remix.charAt(idx) == 'W';
            boolean checkU = remix.charAt(idx+1) == 'U';
            boolean checkB = remix.charAt(idx+2) == 'B';
            if (checkW && checkU && checkB)
                foundWUB = true;
            else
                foundWUB = false;
        }
        if (foundWUB)
            return 3 + skipWUBs(remix, idx+3, uptoIdx);
        else
            return 0;
    }
}