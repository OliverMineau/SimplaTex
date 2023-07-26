package Model;

import View.SimpleEditors.SimpleEditorHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Section {

    public String name;

    //displayCode will be
    public LatexDocument latexDocument;
    //public String displayCode;

    public String header;

    public SimpleEditorHelper editor;
    public boolean simpleEditorEnabled = true;
    private HashMap<String, String> simpleEditorValues = new HashMap<>();

    public String FONT_START = "<b><font face=\"Courier\" size=\"10\" color=\"black\">";
    public String FONT_END = "</font></b>";
    public String RED = "<span style=\"color:red\">";
    public String END = "</span>";
    public String BR = "<br>";

    private int lastId = 0;
    public String element(String text, String color){
        lastId++;
        return "<span id=\""+ lastId +"\" class=\"" + text +  "\" style=\"color:"+ color +"\">" + text + "</span>" ;
    }

    public String multiElement(String text, int count, String seperator, String color){

        return element(text,color);

        /*lastId++;

        String divHeader = "<div id=\"" + lastId + "\">";
        String divFooter = "}</div>";

        String output = divHeader;
        for (int i = 0; i < count; i++) {
            String id = lastId + "-" + i;
            String element =  "{\\Large\n<br> \n<span id=\""+ id +"\" class=\"" + text +  "\" style=\"color:"+ color +"\">" + text + "</span> \\\\\n<br>" ;
            output+=element;
            output+=seperator;
        }

        output+=divFooter;
        return output;*/
    }


    public String getName() {
        return name;
    }

    /*public String getDisplayCode() {
        return displayCode;
    }*/

    public void setDisplayCode(String textEditorCode) {
        //this.displayCode = latexCode;

        //System.out.println("TextEditor code : " + textEditorCode);
        //latexDocument.updateWithDisplayText(textEditorCode);

        latexDocument.updateDisplayText(textEditorCode);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getLatex(){
        return latexDocument.convertToPlainLatex();
    }

    public String getDisplayCode(){
        return latexDocument.removeHtml(latexDocument.codeText);
    }

    private String htmlToPlainText(String html) {
        //TODO Revenir avec l'analyse syntaxique custom

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

    public String loadTex(Path path){
        try {
            String file = "";
            List<String> allLines = Files.readAllLines(path);
            for (String line : allLines) {
                file += line + "\n <br>";
            }
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String loadPlainTex(Path path){
        try {
            String file = "";
            List<String> allLines = Files.readAllLines(path);
            for (String line : allLines) {
                file += line + "\n";
            }
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addInfo(String key, String value){
        simpleEditorValues.put(key, value);

        //Edit element from LatexDocument
        StringElement elm = latexDocument.getElement(key);
        if(value.equals("")) elm.text = elm.className;
        else if(elm != null) elm.text = value;
        else{
            String parentKey = key.split("-")[0];
            StringElement parent = latexDocument.getElement(parentKey);
            StringElement stringElement = new StringElement(key,parent.getClassName(),"",parent.getStyle(),value,parent.type);
            latexDocument.addElementAfter(parentKey, stringElement);
        }
    }

    /*public void addInfos(String key, ArrayList<String> values){

        Document htmlDoc = Jsoup.parse(displayCode);

        Element elm = htmlDoc.getElementById(key);
        if(elm != null){
            String id = key + "-" + 0;
            Element content = htmlDoc.getElementById(id);

            elm.parent().select("span").remove();
            elm.append(multiContent(values, content, key));
            displayCode = htmlDoc.html();
        }
    }*/

    public String multiContent(ArrayList<String> values, Element content, String key){

        String output = "";

        for (int i = 0; i < values.size(); i++) {

            String value = values.get(i);
            String id = key + "-" + i;
            simpleEditorValues.put(id, value);

            Element newContent = content.clone();
            newContent.id(id);

            if(value.equals("")) value = newContent.className();

            newContent.text(value);

            output+= newContent.toString();
        }

        return output;
    }

    public LatexDocument getLatexDocument() {
        return latexDocument;
    }


}
