package View.Editors;

import javax.swing.*;
import java.awt.*;

public class Editor_Main_Page extends JPanel {

    Font labelFont;
    Font textFieldFont;
    GridBagConstraints gridBagConstraints;

    public Editor_Main_Page() {

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.CENTER; // Align elements to the top-left corner
        gridBagConstraints.fill = GridBagConstraints.BOTH; // Elements will fill horizontally
        gridBagConstraints.gridy = 0; // Start at the top (first row)
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 1;

        setLayout(gridBagLayout);

        setBackground(Color.BLUE);

        labelFont = new Font("Arial", Font.BOLD, 30);
        textFieldFont = new Font("Arial", Font.PLAIN, 30);

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

        gridBagConstraints.gridy++; // Increment the row number for the next element
        add(jPanel, gridBagConstraints);
    }

    private void createTextAreaPanel(String titleText, JTextArea textArea) {
        JPanel jPanel = createTitle(titleText);

        textArea.setFont(textFieldFont);
        textArea.setRows(5);
        jPanel.add(textArea, BorderLayout.SOUTH);

        gridBagConstraints.gridy++; // Increment the row number for the next element
        add(jPanel, gridBagConstraints);
    }

    public JPanel createTitle(String titleText) {
        BorderLayout borderLayout = new BorderLayout();
        JPanel jPanel = new JPanel(borderLayout);
        jPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel(titleText);
        label.setFont(labelFont);
        jPanel.add(label, BorderLayout.NORTH);
        return jPanel;
    }
}
