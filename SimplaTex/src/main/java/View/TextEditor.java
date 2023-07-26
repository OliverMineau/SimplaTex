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
        //textArea = new CustomJEditorPane();
        textArea.setContentType("text/html");
        //textArea.setEditable(false);

        //((AbstractDocument) textArea.getDocument()).setDocumentFilter(new CustomDocumentFilter(textArea));

        


        JScrollPane textEditorScroll = new JScrollPane (textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(textEditorScroll);

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                //System.out.println("1 : " +textArea.getText());
                //debug(documentEvent);
                manager.getCurrentSelectedSection().setDisplayCode(textArea.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                //debug(documentEvent);

                //System.out.println("2 : " +textArea.getText());
                manager.getCurrentSelectedSection().setDisplayCode(textArea.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                //debug(documentEvent);
                //System.out.println("3 : " +textArea.getText());
                manager.getCurrentSelectedSection().setDisplayCode(textArea.getText());
            }
        });

    }

    @Override
    public void update(){
        textArea.setText(manager.getCurrentSelectedSection().getLatexDocument().convertToDisplayText());
        textArea.setCaretPosition(0);

        boolean enabled = manager.getSectionManager().getCurrentSelectedSection().simpleEditorEnabled;
        textArea.setEnabled(!enabled);

        manager.getSectionManager().getCurrentSelectedSection().editor.addObserver(this);

        //System.out.println("Updated textEditor");
    }

    @Override
    public void updateJPanel(){
        textArea.setText(manager.getCurrentSelectedSection().getLatexDocument().convertToDisplayText());
        textArea.setCaretPosition(0);
        //System.out.println("Updated textEditor from Jpanel");
    }

    public void debug(DocumentEvent documentEvent){
        try {
            System.out.println(documentEvent.getDocument().getText(documentEvent.getOffset()-10,documentEvent.getLength()+10));
            System.out.println(textArea.getText());
        } catch (BadLocationException e) {

        }
    }

}


