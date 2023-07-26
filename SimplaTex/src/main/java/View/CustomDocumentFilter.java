package View;

import Model.StringElement;

import javax.swing.text.*;
import javax.swing.JEditorPane;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomDocumentFilter extends DocumentFilter {
    private JEditorPane editorPane;

    public CustomDocumentFilter(JEditorPane editorPane) {
        this.editorPane = editorPane;
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        // Get the original HTML content with tags from the JEditorPane
        String oldHtmlContent = editorPane.getText();

        // Apply the removal operation
        super.remove(fb, offset, length);

        // Get the updated HTML content with tags after the removal
        String newHtmlContent = editorPane.getText();

        /**
         * compare old and new, place old span
         */




        HTMLDocument doc = (HTMLDocument) editorPane.getDocument();

        // Create an HTMLEditorKit to insert the HTML content
        HTMLEditorKit kit = new HTMLEditorKit();



        String spanContent = "<b><font face=\"Courier\" size=\"10\" color=\"red\"><span id=\"8\" class=\"--PATH-TO-LOGO--\" title=\"Logo Path\" type=\"Image\">--PATH-TO-LOGO--</span></font></b>";

        System.out.println("Old : " + oldHtmlContent);
        System.out.println("New : " + newHtmlContent);

        String span = "<font face='Courier' size='10' color='red'><span";

        for (int i = 0; i < newHtmlContent.length(); i++) {

            char chOld = oldHtmlContent.charAt(i);
            char chNew = newHtmlContent.charAt(i);
            if (chOld != chNew) {

                if(chOld!='<') return;

                if(i+span.length() < oldHtmlContent.length()){
                    if(oldHtmlContent.substring(i,i+span.length()).contains("red")){
                        try {
                            kit.insertHTML(doc, offset, findSpanContent(oldHtmlContent, i), 0, 0, null);
                            return;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

        }





        /*Random r = new Random();

        // If the removal caused the content of the <span> tag to be empty, undo the removal
        if (isEmptySpan(oldHtmlContent, newHtmlContent, offset, length)) {
            SimpleAttributeSet spanAttributes = new SimpleAttributeSet();
            spanAttributes.addAttribute(HTML.Attribute.ID, "2");
            spanAttributes.addAttribute(HTML.Attribute.CLASS, "--TITLE--");
            spanAttributes.addAttribute(HTML.Attribute.TITLE, "Title");
            spanAttributes.addAttribute(HTML.Attribute.TYPE, "TextField");
            spanAttributes.addAttribute(HTML.Attribute.STYLE,"color=\"red\"");

            //String spanContent = "<span id=\"8\" class=\"--PATH-TO-LOGO--\" title=\"Logo Path\" type=\"Image\" style=\"color:red\">--PATH-TO-LOGO--</span>";
            String spanContent = "<font face=\"Courier\" size=\"10\" color=\"red\"><span id=\"8\" class=\"--PATH-TO-LOGO--\" title=\"Logo Path\" type=\"Image\">--PATH-TO-LOGO--</span></font>";
            HTMLDocument doc = (HTMLDocument) editorPane.getDocument();

            // Create an HTMLEditorKit to insert the HTML content
            HTMLEditorKit kit = new HTMLEditorKit();




            System.out.println("Old : " + oldHtmlContent);
            System.out.println("New : " + newHtmlContent);

            String span = "<span";

            for (int i = 0; i < newHtmlContent.length(); i++) {

                char chOld = oldHtmlContent.charAt(i);
                char chNew = newHtmlContent.charAt(i);
                if (chOld != chNew) {

                    if(i+span.length() < oldHtmlContent.length()){
                        if(oldHtmlContent.substring(i,i+span.length()).contains(span)){
                            try {
                                kit.insertHTML(doc, offset, spanContent, 0, 0, null);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }

            }

            //fb.replace(offset, 0, ,spanAttributes); // Replace with an empty string to cancel the removal
            //editorPane.setText("<font face=\"Courier\" size=\"10\" color=\"red\"><span id=\"1\" class=\"--PATH-TO-LOGO--\" title=\"Logo Path\" type=\"Image\">--PATH-TO-LOGO--</span></font>");
        }*/
    }

    private String findSpanContent(String inputText, int index){

        String START = "<span";
        String END = "</span>";

        int indexStart = inputText.indexOf(START,index);
        int indexEnd = inputText.indexOf(END,indexStart);

        String element = inputText.substring(indexStart,indexEnd+END.length());
        StringElement elm = parseTextElement(element);

        String spanContent = "<b><font face=\"Courier\" size=\"10\" color=\"red\"><span id=\"" + elm.getId() + "\" class=\"" + elm.getClassName() + "\" title=\"" + elm.getTitle() + "\" type=\"" + elm.getType() + "\">" + elm.getClassName() +"</span></font></b>";

        return spanContent;
    }

    private StringElement parseTextElement(String element){

        StringElement stringElement = new StringElement();

        String match;
        if((match=matchElement("id=\"(\\d+)\"", element)) != null){
            stringElement.setId(match);
        }

        if((match=matchElement("class=\"([^\"]+)\"", element)) != null){
            stringElement.setClassName(match);
        }

        if((match=matchElement("title=\"([^\"]+)\"", element)) != null){
            stringElement.setTitle(match);
        }

        if((match=matchElement("type=\"([^\"]+)\"", element)) != null){
            stringElement.setType(match);
        }

        if((match=matchElement("style=\"([^\"]+)\"", element)) != null){
            stringElement.setStyle(match);
        }

        if((match=matchElement(">([^\"]+)<", element)) != null){
            stringElement.setText(match);
        }

        return stringElement;
    }

    private String matchElement(String regex, String element){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(element);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }
}
