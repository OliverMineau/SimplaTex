package View;

import Controller.Controller;
import Model.Manager;
import Patterns.Observable;
import Patterns.Observer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import javax.swing.text.html.HTMLDocument;
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
        manager.getSectionManager().getCurrentSelectedSection().editor.addObserver(this);

        setLayout(new BorderLayout());
        textArea = new JEditorPane();
        textArea.setContentType("text/html");
        //textArea.setBackground(Color.gray);
        //textArea.setFont( new Font("Courier", Font.PLAIN, 30));

        /*Document doc = textArea.getDocument();
        if (doc instanceof PlainDocument) {
            doc.putProperty(PlainDocument.tabSizeAttribute, 8);
        }*/
        
        
        
        
        
        
        
        
        
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
    public void update(){
        textArea.setText(manager.getCurrentSelectedSection().getLatexDocument().convertToDisplayText());
        textArea.setCaretPosition(0);

        manager.getSectionManager().getCurrentSelectedSection().editor.addObserver(this);

        System.out.println("Updated textEditor");
    }

    @Override
    public void updateJPanel(){
        textArea.setText(manager.getCurrentSelectedSection().getLatexDocument().convertToDisplayText());
        textArea.setCaretPosition(0);
        System.out.println("Updated textEditor from Jpanel");
    }

}


