package View.Editors;

import javax.swing.*;
import java.awt.*;

public class Editor_Main_Page extends JPanel {

    Font labelFont;
    Font textFieldFont;

    public Editor_Main_Page() {
        GridLayout gridLayout = new GridLayout(0, 1);
        gridLayout.setVgap(30);
        setLayout(gridLayout);
        setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));


        labelFont = new Font("Arial", Font.BOLD, 30);
        textFieldFont = new Font("Arial", Font.PLAIN, 30);

        // Add rows (each row contains a JLabel and a JTextField)
        createTextPanel("Logo Path :", new JTextField());
        createTextPanel("Level of Education :", new JTextField());
        createTextPanel("Field of Study :", new JTextField());
        createTextPanel("Title :", new JTextField());
        createTextPanel("Author Name:", new JTextField());
        createTextPanel("Date :", new JTextField());
        createTextAreaPanel("Text Area", new JTextArea());
    }

    private void createTextPanel(String titleText, JTextField textField) {
        JPanel jPanel = createTitle(titleText);

        textField.setFont(textFieldFont);
        jPanel.add(textField, BorderLayout.SOUTH);

        add(jPanel);
    }

    private void createTextAreaPanel(String titleText, JTextArea textArea) {
        JPanel jPanel = createTitle(titleText);

        textArea.setFont(textFieldFont);
        textArea.setRows(5);
        jPanel.add(textArea, BorderLayout.SOUTH);

        add(jPanel);
    }

    public JPanel createTitle(String titleText){
        BorderLayout borderLayout = new BorderLayout();
        JPanel jPanel = new JPanel(borderLayout);
        jPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        JLabel label = new JLabel(titleText);
        label.setFont(labelFont);
        jPanel.add(label, BorderLayout.NORTH);
        return jPanel;
    }
}
