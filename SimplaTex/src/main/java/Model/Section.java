package Model;

import View.SimpleEditors.SimpleEditorHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.StringReader;
import java.util.HashMap;

public abstract class Section {

    public String name;
    public String displayCode;

    public SimpleEditorHelper editor;
    private HashMap<String, String> simpleEditorValues = new HashMap<>();

    public String FONT_START = "<b><font face=\"Courier\" size=\"10\">";
    public String FONT_END = "</font></b>";
    public String RED = "<span style=\"color:red\">";
    public String END = "</span>";
    public String BR = "<br>";

    private int lastId = 0;
    public String element(String text, String color){
        lastId++;
        return "<span id=\""+ lastId +"\" class=\"" + text +  "\" style=\"color:"+ color +"\">" + text + "</span>" ;
    }


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

    public String getLatex(){

        //TODO Pas ouf la methode
        String htmlCode = displayCode.replace("<br>", "\\n");
        String plainLatex = htmlToPlainText(htmlCode);
        String latex = plainLatex.replace("\\n","\n");
        return latex;
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

    public HashMap<String,String> getInfo(){
        return simpleEditorValues;
    }

    public void addInfo(String key, String value){
        simpleEditorValues.put(key, value);

        Document htmlDoc = Jsoup.parse(displayCode);

        Element elm = htmlDoc.getElementById(key);
        if(elm != null){

            if(value.equals("")) value = elm.className();

            elm.text(value);
            displayCode = htmlDoc.html();
        }

    }

}
