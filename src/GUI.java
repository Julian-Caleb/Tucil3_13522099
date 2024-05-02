import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private JTextField startWordField, endWordField;
    private JButton concatenateButton;
    private JLabel resultLabel;
    private JComboBox<String> algorithmComboBox; // Added

    public GUI() {
        setTitle("Word Ladder");
        setSize(1000, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        startWordField = new JTextField(10);
        endWordField = new JTextField(10);
        concatenateButton = new JButton("Find the path!");
        resultLabel = new JLabel();

        // Dropdown menu for selecting search algorithm
        algorithmComboBox = new JComboBox<>();
        algorithmComboBox.addItem("UCS");
        algorithmComboBox.addItem("GBFS");
        algorithmComboBox.addItem("A*");

        concatenateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String startWord = startWordField.getText();
                String endWord = endWordField.getText();
                if (!EnglishDictionary.checkWord(startWord)) {
                    resultLabel.setText("Start word is not an English word!");
                } else if (!EnglishDictionary.checkWord(endWord)) {
                    resultLabel.setText("End word is not an English word!");
                } else if (startWord.length() != endWord.length()) {
                    resultLabel.setText("The length are not the same!");
                } else {
                    String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
                    System.out.println("Your start word is: " + startWord);
                    System.out.println("Your end word is: " + endWord);
                    System.out.println("Your algorithm is: " + selectedAlgorithm);
                    resultLabel.setText("Finding path...");
                    WordLadder path = new WordLadder(startWord, endWord, selectedAlgorithm);
                    resultLabel.setText((path.getPath()).toString());
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 5, 5));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Enter start word:"));
        panel.add(startWordField);
        panel.add(new JLabel("Enter end word:"));
        panel.add(endWordField);
        panel.add(new JLabel("Select search algorithm:"));
        panel.add(algorithmComboBox);
        panel.add(concatenateButton);
        panel.add(resultLabel);

        add(panel);
        setVisible(true);
    }
}
