package Model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatexDocument {

    private String FONT_START = "<b><font face=\"Courier\" size=\"10\" color=\"black\">";
    private String FONT_END = "</font></b>";

    private ArrayList<StringElement> contentArray = new ArrayList<>();

    public LatexDocument(String inputText) {
        findElementsFromInput(inputText);
    }

    private void findElementsFromInput(String inputText){
        String START = "<span";
        String END = "</span>";

        int indexStart = inputText.indexOf(START);
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
            String[] classTitle = match.split("\\|");
            stringElement.setClassName(classTitle[0]);
            stringElement.setTitle(classTitle[1]);
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
        String output = "";
        output += FONT_START;

        for(StringElement elm : contentArray) {
            if (elm.type.equals("PlainText")) {
                output += elm.toHTML();

            }else if(elm.type.equals("TextArea")){

                String element = createSpanElement(elm,elm.toHTML());

                output += element;

            }else{

                output += createSpanElement(elm,null);
            }
        }

        output += FONT_END;
        return output;
    }


    public void updateWithDisplayText(String displayText){

    }

    public String createSpanElement(StringElement elm, String overrideText){
        String text = (overrideText==null)?elm.text:overrideText;
        String element = "<span id=\"" +
                elm.id + "\" class=\"" +
                elm.className + "\" type=\"" +
                elm.type + "\" style=\"" +
                elm.style + "\" >" +
                text + "</span>";

        return element;
    }


    public void printElements(){
        for (StringElement elm : getElements()){
            System.out.println(elm);
        }
    }

}
