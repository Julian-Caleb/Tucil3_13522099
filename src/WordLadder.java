import java.util.*;

public class WordLadder {

    // g(n) adalah perbedaan jumlah huruf dari current word ke word n
    // h(n) adalah perbedaan jumlah huruf dari word n ke end word
    
    private String startWord;
    private String endWord;
    private String algorithm;

    private Integer wordsVisitedAmount;
    private Integer steps;
    private ArrayList<String> path;
    private long timeElapsed;

    public WordLadder(String startWord, String endWord, String algorithm) {
        EnglishDictionary.createDictionaryLength(startWord.length());
        this.startWord = startWord;
        this.endWord = endWord;
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

    // gn
    public int gn(int cost) {
        return cost + 1;
    }

    // hn
    public int hn(String word) {
        return EnglishDictionary.findAmountLettersDifference(word, this.endWord);
    }

    // Find Path
    private void findPath() {
        // Mulai waktu
        long startTime = System.currentTimeMillis();

        // Buat struktur data untuk UCS
        ArrayList<String> tempWords = new ArrayList<>(); // Untuk menampung kata-kata yang sudah dibangkitkan sementara
        HashSet<String> wordsVisited = new HashSet<>(); // Untuk ngecek yang sudah di visit
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost)); // Current queue
        
        Queue<String> debugQueue = new LinkedList<>(); // Current queue

        Node currNode; // untuk node sementara
        ArrayList<String> currPath; // untuk path sementara
        
        Boolean found = false;
        
        // debug
        String temp;

        // Buat node startNode
        currPath = new ArrayList<>();
        currPath.add(this.startWord);
        currNode = new Node(startWord, 0, currPath);

        // Masukkan ke queue untuk diproses
        queue.add(currNode);

        // debug
        debugQueue.add(startWord);

        // Proses queue dengan UCS
        while (!queue.isEmpty() && !found) {
            // Ambil isinya
            currNode = queue.poll();

            // debug
            temp = debugQueue.poll();

            // debug
            System.out.println("Current word: " + currNode.getWord());
            System.out.println();

            // Masukkan ke wordsVisited untuk nanti dicek
            wordsVisited.add(currNode.getWord());

            // Bangkitkan child node
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

                    if (!wordsVisited.contains(word)) {
                        Node tempNode = new Node(word, newCost, currPath);
                        wordsVisited.add(word);
                        queue.add(tempNode);

                        // debug
                        debugQueue.add(word);
                    }
                }
            }

            // debug
            System.out.println("Current Queue: " + debugQueue);
            System.out.println();

        }

        // Hentikan dan hitung waktu
        long endTime = System.currentTimeMillis();
        this.timeElapsed = endTime - startTime;

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

        System.out.println("Path: " + this.path);
        System.out.println("Steps: " + this.steps);
        System.out.println("Words visited: " + this.wordsVisitedAmount);
        System.out.println("Time elapsed: " + this.timeElapsed + "ms");
    }

}