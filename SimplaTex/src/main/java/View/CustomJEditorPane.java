package View;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.IOException;
import java.io.StringReader;

public class CustomJEditorPane extends JEditorPane {
    public CustomJEditorPane() {
        super();
        setEditorKit(new HTMLParsingEditorKit());
    }

    private static class HTMLParsingEditorKit extends HTMLEditorKit {
        @Override
        public Document createDefaultDocument() {
            return new HTMLParsingDocument();
        }
    }

    private static class HTMLParsingDocument extends HTMLDocument {

        public HTMLParsingDocument() {
            setDocumentFilter(new PreserveSpanDocumentFilter());
        }

        private String getRawHTMLContent() {
            try {
                // Get the root element of the document
                Element root = getDefaultRootElement();

                // Get the content of the document using the root element
                StringBuilder rawHTMLContent = new StringBuilder();
                writeLock();
                try {
                    int numElements = root.getElementCount();
                    for (int i = 0; i < numElements; i++) {
                        Element element = root.getElement(i);
                        int startOffset = element.getStartOffset();
                        int endOffset = element.getEndOffset();
                        String elementContent = getText(startOffset, endOffset - startOffset);
                        rawHTMLContent.append(elementContent);
                    }
                } finally {
                    writeUnlock();
                }

                return rawHTMLContent.toString();
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void remove(int offs, int len) throws BadLocationException {
            // Get the content of the deletion range
            String deletion = getText(offs, len);
            //System.out.println("Remove code : " + HTMLDocument);

            // Parse the content to check if it contains the '<span>' tag
            HTMLEditorKit.ParserCallback parserCallback = new HTMLEditorKit.ParserCallback() {
                private boolean insideSpan = false;

                @Override
                public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
                    if (t == HTML.Tag.SPAN) {
                        insideSpan = true;
                    }

                    //System.out.println(getContent());
                }

                @Override
                public void handleEndTag(HTML.Tag t, int pos) {
                    if (t == HTML.Tag.SPAN) {
                        insideSpan = false;
                    }
                }

                @Override
                public void handleText(char[] data, int pos) {
                    if (insideSpan) {
                        String spanText = new String(data);
                        if (deletion.contains(spanText)) {
                            // The entire <span> tag is being removed, stop its deletion
                            return;
                        }
                    }
                }
            };

            try {
                // Parse the content to check for the <span> tag
                new ParserDelegator().parse(new StringReader(deletion), parserCallback, false);
            } catch (IOException e) {
                e.printStackTrace();
            }

            super.remove(offs, len);
        }

    }
}
