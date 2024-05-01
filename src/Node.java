import java.util.ArrayList;

public class Node {
    
    private String word;
    private Integer cost;
    private ArrayList<String> path;

    public Node(String word, Integer cost, ArrayList<String> path) {
        this.word = word;
        this.cost = cost;
        this.path = path;
    }

    public String getWord() {
        return word;
    }

    public int getCost() {
        return cost;
    }

    public ArrayList<String> getPath() {
        return path;
    }

}
