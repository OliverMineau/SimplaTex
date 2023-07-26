package View;

import javax.swing.*;
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

public class CustomHTMLEditorKit extends HTMLEditorKit {



/*@Override
    public void insertHTML(HTMLDocument doc, int offset, String html, int popDepth, int pushDepth, HTML.Tag insertTag) throws BadLocationException, IOException {
        super.insertHTML(doc, offset, html, popDepth, pushDepth, insertTag);

        if (offset > doc.getLength()) {
            throw new BadLocationException("Invalid location", offset);
        } else {
            Parser p = this.ensureParser(doc);
            ParserCallback receiver = doc.getReader(offset, popDepth, pushDepth, insertTag);
            Boolean ignoreCharset = (Boolean)doc.getProperty("IgnoreCharsetDirective");
            p.parse(new StringReader(html), receiver, ignoreCharset == null ? true : ignoreCharset);
            receiver.flush();
        }
    }*/
}
