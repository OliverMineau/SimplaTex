package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;

import javax.swing.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

public class CustomHTMLEditorKit extends HTMLEditorKit {
    private Document doc;

    @Override
    public void install(JEditorPane c) {
        super.install(c);
        doc = c.getDocument();
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // No action needed for insertion
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> preventRemovalOfEmptyTags(e));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // No action needed for changes in attributes
            }
        });
    }

    private void preventRemovalOfEmptyTags(DocumentEvent e) {
        try {
            String content = doc.getText(0, doc.getLength());
            String cleanedContent = content.replaceAll("\\<.*?\\>", "").trim();

            if (cleanedContent.isEmpty()) {
                doc.remove(0, doc.getLength()); // Remove all content to prevent deletion
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }
}

