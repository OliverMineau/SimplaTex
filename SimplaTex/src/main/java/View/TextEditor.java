package View;

import Controller.Controller;
import Model.Manager;
import Patterns.Observer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.awt.*;
import java.io.StringReader;

public class TextEditor extends JPanel implements Observer {

    Manager manager;
    Controller controller;
    JEditorPane textArea;

    public TextEditor(Manager manager, Controller controller) {
        this.manager = manager;
        this.controller = controller;

        manager.getSectionManager().addObserver(this);

        setLayout(new BorderLayout());
        textArea = new JEditorPane();
        textArea.setContentType("text/html");
        textArea.setFont( new Font("Courier", Font.PLAIN, 30));
        JScrollPane textEditorScroll = new JScrollPane (textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(textEditorScroll);

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                manager.getCurrentSelectedSection().setDisplayCode(textArea.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                manager.getCurrentSelectedSection().setDisplayCode(textArea.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                manager.getCurrentSelectedSection().setDisplayCode(textArea.getText());
            }
        });

    }

    @Override
    public void updateEditor() {
        textArea.setText(manager.getCurrentSelectedSection().getDisplayCode());
        textArea.setCaretPosition(0);
        System.out.println("Updated Editor");
    }



}
