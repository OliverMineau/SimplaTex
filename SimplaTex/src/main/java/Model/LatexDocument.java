package Model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatexDocument {

    private String FONT_START = "<b><font face=\"Courier\" size=\"10\" color=\"black\">";
    private String FONT_END = "</font></b>";
    private String START = "<span";
    private String END = "</span>";
    private String lastDisplay = "";

    private ArrayList<StringElement> contentArray = new ArrayList<>();

    public LatexDocument(String inputText) {
        findElementsFromInput(inputText);
    }

    private void findElementsFromInput(String inputText){

        int indexStart = inputText.indexOf(START);
        if(indexStart == -1) return;

        addText(inputText.substring(0,indexStart));

        while (indexStart != -1) {
            int indexEnd = inputText.indexOf(END,indexStart);

            String element = inputText.substring(indexStart,indexEnd+END.length());
            parseTextElement(element);

            indexStart = inputText.indexOf(START, indexEnd);

            if(indexStart == -1) addText(inputText.substring(indexEnd+END.length()));
            else addText(inputText.substring(indexEnd+END.length(),indexStart));
        }
    }

    private void parseTextElement(String element){

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

        contentArray.add(stringElement);
    }

    private String matchElement(String regex, String element){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(element);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }


    /**
     * Ajouter un element apres un autre
     */
    public void addElementAfter(String afterID, StringElement element) {
        int elmIndex = findElementIndex(afterID);
        if(elmIndex == -1) return;
        int index = elmIndex+1;
        if(elmIndex+1 == contentArray.size()) contentArray.add(element);
        else contentArray.add(index,element);
    }


    /**
     * Ajouter un element
     */
    public void addElement(StringElement element) {
        contentArray.add(element);
    }


    /**
     * Supprimer l'element id
     */
    public void removeElement(String id) {
        int index = findElementIndex(id);
        if(index == -1) return;
        contentArray.remove(index);
    }


    /**
     * modifier le contenu (texte) d'un element
     */
    public void modifyElement(String id, StringElement element) {
        int index = findElementIndex(id);
        contentArray.set(index, element);
    }


    /**
     * Prendre tous les elements de type
     */
    public StringElement getElement(String id) {
        for (StringElement elm : contentArray) {
            if(elm.id.equals(id)){
                return elm;
            }
        }
        return null;
    }

    public int findElementIndex(String id) {
        for (StringElement elm : contentArray) {
            if(elm.id.equals(id)){
                return contentArray.indexOf(elm);
            }
        }
        return -1;
    }


    /**
     * Prendre tous les elements
     */
    public ArrayList<StringElement> getElements() {
        return contentArray;
    }


    /**
     * Ajouter du texte apres un element
     */
    public void addTextAfter(String id, String text) {
        int elmIndex = findElementIndex(id);
        if(elmIndex == -1) return;
        int index = elmIndex+1;
        if(elmIndex+1 == contentArray.size()) contentArray.add(new StringElement(text));
        else contentArray.add(index,new StringElement(text));
    }

    public void addText(String text) {
        contentArray.add(new StringElement(text));
    }


    /**
     * Supprime tous les tags en laissant les textes
     * Convertit les <br> en fins de lignes
     * Convertit les &nbsp; en espace
     */
    public String convertToPlainLatex() {
        String output = "";
        for(StringElement elm : contentArray){
            output += elm.text;
        }
        return output;
    }

    /**
     * (que les fin de ligne, faire attention au \n dans "\newspace,..")
     * Convertit les fins de lignes en <br>
     * Convertit les espace en &nbsp;
     * Convertit les \t en &nbsp; &nbsp;
     */
    public String convertToDisplayText(){
        String output = "", content = "";

        for(StringElement elm : contentArray) {
            if (elm.type.equals("PlainText")) {
                content += elm.toHTML();

            }else if(elm.type.equals("TextArea")){

                String element = createSpanElement(elm,elm.toHTML());

                content += element;

            }else{

                content += createSpanElement(elm,null);
            }
        }

        lastDisplay = content;
        lastDisplay = lastDisplay.replace("style=\"color:red\"","");

        output = FONT_START + content + FONT_END;
        return output;
    }


    public void updateWithDisplayText(String displayText){

        //displayText = displayText.replaceAll("<(?:html|head|body)>|<\\/(?:html|head|body)>\\n","");


        displayText = removeHtml(displayText);

        System.out.println("input display text : " + displayText);

        for(StringElement stringElement : contentArray){
            if(stringElement.id.equals("")) continue;

            String START = "<p id=\""+ stringElement.id + "\">";
            int indexStart = displayText.indexOf(START);
            int indexEnd = displayText.indexOf("</p>",indexStart);

            if(!displayText.substring(indexStart,indexEnd).contains("<span")){
                String strt = displayText.substring(0,indexStart+START.length());
                String end = displayText.substring(indexEnd);
                displayText = strt + createSpanElement(stringElement,null) + end;
            }
        }

        System.out.println("New display text : " + displayText);
        System.out.println("Last display text : " + lastDisplay);

        contentArray.clear();
        findElementsFromInput(displayText);

        /*if(displayText == null || displayText.equals(lastDisplay)) return;

        contentArray.clear();
        findElementsFromInput(displayText);
        System.out.println("Content array : " + contentArray.toString());*/
    }


    public String removeHtml(String displayText){
        String SBODY = "<body>";
        String EBODY = "</body>";

        int indexStart = displayText.indexOf(SBODY);
        int indexEnd = displayText.indexOf(EBODY);

        if(indexStart == -1 || indexEnd == -1) return null;

        displayText = displayText.substring(indexStart+SBODY.length(),indexEnd);
        displayText = displayText.trim();
        displayText = displayText.replace("&#160;","&nbsp;");
        displayText = displayText.replace("<font face=\"Courier\" size=\"10\" color=\"black\">","");
        displayText = displayText.replace("</font>","");
        displayText = displayText.replace("<font face=\"Courier\" size=\"10\" color=\"red\">","");
        displayText = displayText.replace("<b>","");
        displayText = displayText.replace("</b>","");
        displayText = displayText.replace("\n","");
        displayText = displayText.replace("\t","");


        return displayText;
    }

    public String convertToSimplatex(String text){
        String txt = text;
        txt = txt.replace("<br>","\n");
        txt = txt.replace("&nbsp;&nbsp;","\t");
        txt = txt.replace("&nbsp;"," ");
        return txt;
    }

    public String createSpanElement(StringElement elm, String overrideText){
        String text = (overrideText==null)?elm.text:overrideText;
        String style = "style=\"color:red\">";

        String ds = "<p id=\""+ elm.id +"\">", de = "</p>";

        String element = "<span id=\"" +
                elm.id + "\" class=\"" +
                elm.className + "\" title=\"" +
                elm.title + "\" type=\"" +
                elm.type + "\"" +
                style +
                text + "</span>";

        return element;
    }


    public void printElements(){
        for (StringElement elm : getElements()){
            System.out.println(elm);
        }
    }

}
