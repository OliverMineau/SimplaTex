package View.SimpleEditors;

import Controller.SimpleEditorListener;
import Patterns.ObservableJPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class SimpleEditorHelper extends ObservableJPanel {

    public Font labelFont;
    public Font textFieldFont;
    public GridBagConstraints gridBagConstraints;
    private SimpleEditorListener simpleEditorListener;
    public HashMap<String, JTextComponent> components;

    private int subindex = 0;

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
        jPanel.add(textField);

        JLabel picLabel = new JLabel();
        picLabel.setBorder(new EmptyBorder(15,0,0,0));
        picLabel.setFont(textFieldFont);
        picLabel.setText("No Image Found");
        jPanel.add(picLabel);

        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE,textField.getPreferredSize().height));

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
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE,textField.getPreferredSize().height));
        jPanel.add(textField);

        gridBagConstraints.gridy++; // Increment the row number for the next element
        add(jPanel, gridBagConstraints);

        return textField;
    }

    public JTextArea createTextAreaPanel(String titleText) {
        JPanel jPanel = createTitle(titleText);

        JTextArea textArea = new JTextArea();
        textArea.setFont(textFieldFont);
        textArea.setRows(5);
        textArea.setTabSize(2);
        textArea.getDocument().addDocumentListener(simpleEditorListener);
        jPanel.add(textArea, BorderLayout.SOUTH);

        gridBagConstraints.gridy++; // Increment the row number for the next element
        add(jPanel, gridBagConstraints);

        return textArea;
    }

    public void createMultipleTextsPanel(String titleText, String index, HashMap<String, JTextComponent> components) {

        JPanel jPanel = createTitle(titleText);
        jPanel.setLayout(new GridLayout(0,1));

        JTextField jTextField = new JTextField();
        jTextField.setFont(textFieldFont);
        jTextField.getDocument().addDocumentListener(simpleEditorListener);
        jPanel.add(jTextField);

        //Add main textfield to components
        components.put(index,jTextField);

        JPanel buttons = new JPanel(new GridLayout(0,2));

        JButton addBtn = new JButton("Add");
        JButton rmvBtn = new JButton("Remove");
        addBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE,addBtn.getPreferredSize().height));

        rmvBtn.setVisible(false);

        addBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                subindex++;

                JTextField jTextField = new JTextField();
                jTextField.setFont(textFieldFont);
                jTextField.getDocument().addDocumentListener(simpleEditorListener);
                jTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE,jTextField.getPreferredSize().height));
                jPanel.add(jTextField,subindex);

                //Add textfield to components
                components.put(index + "-" + subindex, jTextField);


                if(subindex >= 1) rmvBtn.setVisible(true);

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

                if(subindex == 0) return;

                jPanel.remove(subindex);

                components.remove(index + "-" + subindex);

                subindex--;
                if(subindex == 0) {
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

    }

    public JPanel createTitle(String titleText) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        jPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel(titleText);
        label.setFont(labelFont);
        jPanel.add(label);
        return jPanel;
    }

    public abstract void updateViews();
    public abstract void saveViews();
    public void notifyAndSave(){
        saveViews();
        update();
    }
}
