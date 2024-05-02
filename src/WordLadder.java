import java.util.*;

// Ah yes, Class WordLadder, class utama untuk path finding.
// Bukan singleton karena, ya gapapa sih. Sepertinya bisa dibuat lebih dari satu dalam satu program
// sebagai history penggunaan.
public class WordLadder {
    
    // Atribut (self-explanatory)
    private String startWord;
    private String endWord;
    private String algorithm;

    private Integer wordsVisitedAmount;
    private Integer steps;
    private ArrayList<String> path;
    private long timeElapsed;

    // Constructor, yang hanya memasukkan parameter ke atribut, inisialisasi untuk eksekusi program
    // dan menjalankan path finding.
    public WordLadder(String startWord, String endWord, String algorithm) {
        EnglishDictionary.createDictionaryLength(startWord.length());
        this.startWord = startWord.toLowerCase();
        this.endWord = endWord.toLowerCase();
        this.algorithm = algorithm;
        this.wordsVisitedAmount = 0;
        this.steps = 0;
        this.path = new ArrayList<>();

        this.findPath();
    }

    // Getter
    public ArrayList<String> getPath() {
        return this.path;
    }

    public Integer getWordVisitedAmount() {
        return this.wordsVisitedAmount;
    }

    public Integer getSteps() {
        return this.steps;
    }

    public long getTimeElapsed() {
        return this.timeElapsed;
    }

    // Fungsi g(n), penghitung cost untuk UCS.
    // g(n) = cost sementara + banyak perbedaan huruf kata sekarang dengan kata berikutnya, yaitu 1.
    public int gn(int cost) {
        return cost + 1;
    }

    // Fungsi h(n), fungsi evaluasi heuristik untuk GBFS.
    // h(n) = banyak beda huruf antara word dengan endWord.
    public int hn(String word) {
        return EnglishDictionary.findAmountLettersDifference(word, this.endWord);
    }

    // Fungsi f(n) = g(n) + h(n), fungsi penghitung cost untuk A* (tidak diimplementasikan sebagai method).
    // nb. gak ada alasan khusus sih.

    // Ini dia fungsi utamanya, fungsi untuk mencari path antar kedua kata.
    // Menggunakan versi array wordVisited sudah dihapus.
    private void findPath() {
        // Mulai waktu
        long startTime = System.currentTimeMillis();

        // Buat struktur data untuk UCS
        ArrayList<String> tempWords = new ArrayList<>(); // Untuk menampung kata-kata yang sudah dibangkitkan sementara.
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost)); // Untuk queue pemrosesan kata.

        Node currNode; // Untuk node sementara
        ArrayList<String> currPath; // Untuk path sementara
        Boolean found = false; // Untuk memberi tahu apakah sudah ditemukan atau belum

        // Kalau startWord == endWord
        if (this.startWord.equals(this.endWord)) {
            this.path = new ArrayList<>();  
            this.path.add(this.endWord);
            this.steps = this.path.size() - 1;
            System.out.println("Found!");
        } else {
            // Kalau tidak

            // debug
            // String temp;

            // Buat node startNode
            currPath = new ArrayList<>();
            currPath.add(this.startWord);
            currNode = new Node(startWord, 0, currPath);

            // Masukkan ke queue untuk diproses
            queue.add(currNode);

            // debug
            // debugQueue.add(startWord);

            // Proses queue dengan UCS
            while (!queue.isEmpty() && !found) {
                // Ambil isinya (paling pertama)
                currNode = queue.poll();

                // debug
                // temp = debugQueue.poll();

                // debug
                System.out.println("Current word: " + currNode.getWord());
                System.out.println();

                // Bangkitkan next word
                tempWords.clear();
                tempWords = EnglishDictionary.findWordsWithOneLetterDifference(currNode.getWord());

                // debug
                System.out.println("Found words: " + tempWords);
                System.out.println();
                
                if (tempWords.contains(this.endWord)) {
                    // Kalau menemukan end word
                    found = true;
                } else {
                    // Kalau tidak
                    for (String word : tempWords) {
                        this.wordsVisitedAmount++;

                        // Ambil path sekarang, ditambah kata yang baru
                        currPath = new ArrayList<>();
                        currPath.addAll(currNode.getPath());
                        currPath.add(word);

                        // Mengambil cost yang baru
                        int newCost = 0;
                        if (this.algorithm == "UCS") {
                            newCost = gn(currNode.getCost());
                        } else if (this.algorithm == "GBFS") {
                            newCost = hn(word);
                        } else {
                            newCost = gn(currNode.getCost()) + hn(word);
                        }

                        // Membuat node, lalu dimasukkan ke prioQueue
                        Node tempNode = new Node(word, newCost, currPath);
                        queue.add(tempNode);
                    }
                }

                // debug
                // System.out.println("Current Queue: " + debugQueue);
                // System.out.println();
                // System.out.println(wordsVisited);

            }

            if (found) {
                // Kalau semisal ketemu
                this.path = currNode.getPath();
                this.path.add(this.endWord);

                this.steps = this.path.size() - 1;

                System.out.println("Found!");
            } else {
                // Semisal tidak
                System.out.println("Not found");
            }
        }

        // Hentikan dan hitung waktu
        long endTime = System.currentTimeMillis();
        this.timeElapsed = endTime - startTime;

        System.out.println("Path: " + this.path);
        System.out.println("Steps: " + this.steps);
        System.out.println("Words visited: " + this.wordsVisitedAmount);
        System.out.println("Time elapsed: " + this.timeElapsed + "ms");
    }

}