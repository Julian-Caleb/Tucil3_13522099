import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

    public static void createDictionaryLength(int length) {
        String outputFile = "dictionary/dictionary_length_" + length + ".txt";
        File file = new File(outputFile);
        if (file.exists()) {
            System.out.println("Dictionary file with words length " + length + " already exists.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(dictionary));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().length() == length) {
                    bw.write(line.trim());
                    bw.newLine();
                }
            }
            System.out.println("Dictionary file with words length " + length + " created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> findWordsWithOneLetterDifference(String inputWord) {
        ArrayList<String> wordsWithOneDifference = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dictionary))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (findAmountLettersDifference(inputWord, line.trim()) == 1) {
                    wordsWithOneDifference.add(line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsWithOneDifference;
    }

    public static int findAmountLettersDifference(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return -1;
        }
        int diff = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }

}
