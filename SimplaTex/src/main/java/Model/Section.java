package Model;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.StringReader;

public abstract class Section {

    public String name;
    public String displayCode;


    public String getName() {
        return name;
    }

    public String getDisplayCode() {
        return displayCode;
    }

    public void setDisplayCode(String latexCode) {
        this.displayCode = latexCode;
    }

    @Override
    public String toString() {
        return name;
    }

    public void getPurLatex(){
        //TODO Pas ouf la methode
        System.out.println("Text : " + htmlToPlainText(displayCode.replace("<br>", "\\n")).replace("\\n","\n"));
    }

    private String htmlToPlainText(String html) {
        try {
            // Create a document to parse the HTML content
            HTMLEditorKit.Parser parser = new ParserDelegator();
            StringBuilder plainText = new StringBuilder();

            // Callback to handle text elements
            HTMLEditorKit.ParserCallback callback = new HTMLEditorKit.ParserCallback() {
                @Override
                public void handleText(char[] data, int pos) {
                    plainText.append(data);
                }
            };

            // Parse the HTML content and extract the plain text
            parser.parse(new StringReader(html), callback, true);

            return plainText.toString();
        } catch (Exception e) {
            // Handle parsing exceptions
            e.printStackTrace();
            return "";
        }
    }
}
