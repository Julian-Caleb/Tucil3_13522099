import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

// Class GUI untuk menampilkan program
public class GUI extends JFrame {
    private JTextField startWordField, endWordField;
    private JButton concatenateButton;
    private JComboBox<String> algorithmComboBox;
    private JTextArea pathTextArea;

    public GUI() {
        // Menampilkan GUI
        setTitle("Word Ladder");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        startWordField = new JTextField(10);
        endWordField = new JTextField(10);
        concatenateButton = new JButton("Find the path!");
        
        pathTextArea = new JTextArea(10, 30);
        pathTextArea.setEditable(false);
        pathTextArea.setLineWrap(true);
        pathTextArea.setWrapStyleWord(true);

        algorithmComboBox = new JComboBox<>();
        algorithmComboBox.addItem("UCS");
        algorithmComboBox.addItem("GBFS");
        algorithmComboBox.addItem("A*");

        // Yang terpenting adalah, jika tombol ditekan, maka akan melakukan validasi
        // lalu jika valid, akan dilakukan pencarian path
        concatenateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String startWord = startWordField.getText();
                String endWord = endWordField.getText();
                if (!EnglishDictionary.checkWord(startWord)) {
                    pathTextArea.setText("Start word is not an English word!");
                } else if (!EnglishDictionary.checkWord(endWord)) {
                    pathTextArea.setText("End word is not an English word!");
                } else if (startWord.length() != endWord.length()) {
                    pathTextArea.setText("The lengths are not the same!");
                } else {
                    String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();

                    // debug
                    System.out.println("Your start word is: " + startWord);
                    System.out.println("Your end word is: " + endWord);
                    System.out.println("Your algorithm is: " + selectedAlgorithm);
                    WordLadder path = new WordLadder(startWord, endWord, selectedAlgorithm);

                    // Result
                    String result = "Path: " + path.getPath().toString() + "\n" + "Steps: " + path.getSteps() + "\n" + "Words Visited: " + path.getWordVisitedAmount() + "\n" + "Time Elapsed: " + path.getTimeElapsed() + "ms";
                    pathTextArea.setText(result);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 5, 5)); 
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Enter start word:"));
        panel.add(startWordField);
        panel.add(new JLabel("Enter end word:"));
        panel.add(endWordField);
        panel.add(new JLabel("Select search algorithm:"));
        panel.add(algorithmComboBox);
        panel.add(concatenateButton);
        panel.add(new JScrollPane(pathTextArea)); 

        add(panel);
        setVisible(true);
    }
}