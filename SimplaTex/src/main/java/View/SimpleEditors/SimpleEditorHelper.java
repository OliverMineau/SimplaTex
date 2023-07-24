package View.SimpleEditors;

import Controller.SimpleEditorListener;
import Patterns.ObservableJPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public abstract class SimpleEditorHelper extends ObservableJPanel {

    public Font labelFont;
    public Font textFieldFont;
    public GridBagConstraints gridBagConstraints;
    private SimpleEditorListener simpleEditorListener;

    public SimpleEditorHelper() {

        simpleEditorListener = new SimpleEditorListener(this);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.CENTER; // Align elements to the top-left corner
        gridBagConstraints.fill = GridBagConstraints.BOTH; // Elements will fill horizontally
        gridBagConstraints.gridy = 0; // Start at the top (first row)
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 1;

        setLayout(gridBagLayout);

        labelFont = new Font("Arial", Font.BOLD, 30);
        textFieldFont = new Font("Arial", Font.PLAIN, 30);

    }

    public JTextField createImagePanel(String titleText) {
        JPanel jPanel = createTitle(titleText);

        JTextField textField = new JTextField();
        textField.setFont(textFieldFont);
        jPanel.add(textField, BorderLayout.CENTER);

        JLabel picLabel = new JLabel();
        picLabel.setBorder(new EmptyBorder(15,0,0,0));
        picLabel.setFont(textFieldFont);
        picLabel.setText("No Image Found");
        jPanel.add(picLabel, BorderLayout.SOUTH);

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                updateImage(documentEvent);
                notifyAndSave();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                updateImage(documentEvent);
                notifyAndSave();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                updateImage(documentEvent);
                notifyAndSave();
            }

            private void updateImage(DocumentEvent documentEvent){
                try {
                    BufferedImage myPicture = ImageIO.read(new File(documentEvent.getDocument().getText(0,documentEvent.getDocument().getLength())));
                    picLabel.setIcon(new ImageIcon(myPicture));
                    picLabel.setText("");
                } catch (Exception e) {
                    picLabel.setIcon(null);
                    picLabel.setText("No Image Found");
                }
            }
        });

        gridBagConstraints.gridy++; // Increment the row number for the next element
        add(jPanel, gridBagConstraints);

        return textField;
    }

    public JTextField createTextPanel(String titleText) {
        JPanel jPanel = createTitle(titleText);

        JTextField textField = new JTextField();
        textField.setFont(textFieldFont);
        textField.getDocument().addDocumentListener(simpleEditorListener);
        jPanel.add(textField, BorderLayout.SOUTH);

        gridBagConstraints.gridy++; // Increment the row number for the next element
        add(jPanel, gridBagConstraints);

        return textField;
    }

    public JTextArea createTextAreaPanel(String titleText) {
        JPanel jPanel = createTitle(titleText);

        JTextArea textArea = new JTextArea();
        textArea.setFont(textFieldFont);
        textArea.setRows(5);
        textArea.getDocument().addDocumentListener(simpleEditorListener);
        jPanel.add(textArea, BorderLayout.SOUTH);

        gridBagConstraints.gridy++; // Increment the row number for the next element
        add(jPanel, gridBagConstraints);

        return textArea;
    }

    public ArrayList<JTextField> createMultipleTexts(String titleText) {
        JPanel jPanel = createTitle(titleText);
        jPanel.setLayout(new GridLayout(0,1));

        ArrayList<JTextField> jTextFields = new ArrayList<>();
        jTextFields.add(new JTextField());
        jTextFields.get(0).setFont(textFieldFont);
        jTextFields.get(0).getDocument().addDocumentListener(simpleEditorListener);
        jPanel.add(jTextFields.get(0));

        JPanel buttons = new JPanel(new GridLayout(0,2));
        JButton addBtn = new JButton("Add");
        JButton rmvBtn = new JButton("Remove");
        rmvBtn.setVisible(false);

        addBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                jTextFields.add(new JTextField());
                int index = jTextFields.size();
                jTextFields.get(index-1).setFont(textFieldFont);
                jPanel.add(jTextFields.get(index-1),index);

                if(index > 1) rmvBtn.setVisible(true);

                jPanel.revalidate();
                jPanel.repaint();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {}
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {}
            @Override
            public void mouseExited(MouseEvent mouseEvent) {}
        });

        rmvBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int index = jTextFields.size();

                if(index == 1) return;

                jTextFields.remove(index-1);
                jPanel.remove(index);

                if(jTextFields.size() == 1) {
                    rmvBtn.setVisible(false);
                    return;
                }

                jPanel.revalidate();
                jPanel.repaint();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {}
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {}
            @Override
            public void mouseExited(MouseEvent mouseEvent) {}
        });
        buttons.add(addBtn);
        buttons.add(rmvBtn);

        jPanel.add(buttons);


        gridBagConstraints.gridy++; // Increment the row number for the next element
        add(jPanel, gridBagConstraints);

        return jTextFields;
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

    public abstract void updateViews();
    public abstract void saveViews();
    public void notifyAndSave(){
        saveViews();
        update();
    }
}
