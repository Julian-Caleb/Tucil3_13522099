import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EnglishDictionary {

    public static String dictionary = "dictionary/dictionary2.txt";

    public static boolean checkWord(String word) {
        try (BufferedReader br = new BufferedReader(new FileReader(dictionary))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equalsIgnoreCase(word)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
