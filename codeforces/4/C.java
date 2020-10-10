// Codeforces 4C
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class CF4C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int names = SC.nextInt(); // Number of names to be inserted
        Map<String, Integer> namesMap = new HashMap<String, Integer>();

        for(int n = 0; n < names; ++n) {
            String nameRequest = SC.next();
            String response = getResponse(namesMap, nameRequest);
            System.out.println(response);
        }
    }

    // Returns response based on names already present in map and name passed and adds name to map
    static String getResponse(Map<String, Integer> namesMap, String nameRequest) {
        // Names are comprised of lowercase letters only
        // Therefore no need to store additional names with numbers appended at their ends, just knowing the count is enough
        String response;
        boolean alreadyExists = namesMap.containsKey(nameRequest);
        if (!alreadyExists) {
            namesMap.put(nameRequest, 1);
            response = "OK";
        }
        else {
            int appendNum = namesMap.get(nameRequest);
            namesMap.put(nameRequest, appendNum+1);
            response = nameRequest + Integer.toString(appendNum);
        }
        return response;
    }
}