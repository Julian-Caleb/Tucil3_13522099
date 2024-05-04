import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Static Class EnglishDictionary, menyimpan text file berisi kamus bahasa Inggris secara lokal.
// Dibuat static, singleton, karena cukup hanya perlu ada satu.

// Versi terakhir, yaitu dengan menggunakan array (sebelumnya file temp) sebagai penampungan sementara.
public class EnglishDictionary {

    // Kamus disimpan secara lokal, ditampung pathnya relatif kepada .class.
    public static String dictionary = "dictionary/dictionaryWordsAlpha.txt";
    // Array untuk menampung dan memproses kata-kata secara sementara
    public static ArrayList<String> currLocalDictionary = new ArrayList<>();

    // Fungsi untuk mengecek apakah ada kata di kamus, digunakan untuk validasi di awal.
    public static boolean checkWord(String word) {
        try (BufferedReader br = new BufferedReader(new FileReader(EnglishDictionary.dictionary))) {
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

    // Untuk mempercepat proses, dari kamus awal, diambil kata-kata yang berukuran sama dengan kata input (start dan end).
    // Dimasukkan ke array untuk diproses.
    public static void createDictionaryLength(int length) {
        try (BufferedReader br = new BufferedReader(new FileReader(EnglishDictionary.dictionary))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().length() == length) {
                    EnglishDictionary.currLocalDictionary.add(line.trim());
                }
            }
            System.out.println("Dictionary array with words length " + length + " created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Fungsi pembangkit, yaitu mencari kata-kata yang berbeda satu huruf dari kata input.
    public static ArrayList<String> findWordsWithOneLetterDifference(String inputWord) {
        ArrayList<String> wordsWithOneDifference = new ArrayList<>();
        ArrayList<String> restOfWords = new ArrayList<>();
        for (String word : EnglishDictionary.currLocalDictionary) {
            if (EnglishDictionary.findAmountLettersDifference(word.toLowerCase(), inputWord) == 1) {
                // Jika berbeda satu huruf, dimasukkan ke array dan akan di return.
                wordsWithOneDifference.add(word.toLowerCase());
            } else {
                // Jika berbeda lebih dari satu huruf, dimasukkan ke array, yang diakhir akan mengganti kamus sementara sekarang.
                restOfWords.add(word);
            }
        }

        // Mengubah kamus sementara.
        // Hal ini dilakukan sebagai alternatif array kata-kata yang sudah di visit, sehingga bisa mempercepat proses.
        EnglishDictionary.currLocalDictionary.clear();
        EnglishDictionary.currLocalDictionary.addAll(restOfWords);

        return wordsWithOneDifference;
    }

    // Fungsi untuk mencari berapa banyak huruf yang beda antara kedua kata.
    // Jika length tidak sama, mengembalikan -1, namun selama fungsi ini digunakan dalam eksekusi program,
    // pasti length kedua kata akan sama (karena kamus sementara mempunyai panjang kata yang sama semua).
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
