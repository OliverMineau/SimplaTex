package Model;

import java.util.ArrayList;

public class LatexDocument {

    private String contentText;


    /**
     * Ajouter un element apres un autre
     */
    public void addElement(String afterID, StringElement element) {

    }


    /**
     * Supprimer l'element id
     */
    public void removeElement(String id) {

    }


    /**
     * modifier le contenu (texte) d'un element
     */
    public void modifyElement(String id, StringElement element) {

    }


    /**
     * Prendre tous les elements de type
     */
    public StringElement getElement(String id) {

        return null;
    }


    /**
     * Prendre tous les elements de type
     */
    public ArrayList<StringElement> getElements(String type) {
        ArrayList<StringElement> stringElements = new ArrayList<>();


        return stringElements;
    }


    /**
     * Ajouter du texte apres un element
     */
    public void addTextAfter(String id, String text) {

    }


    /**
     * Supprime tous les tags en laissant les textes
     * Convertit les <br> en fins de lignes
     * Convertit les &nbsp; en espace
     */
    public void convertToPlainLatex() {

    }

    /**
     * (que les fin de ligne, faire attention au \n dans "\newspace,..")
     * Convertit les fins de lignes en <br>
     * Convertit les espace en &nbsp;
     * Convertit les \t en &nbsp; &nbsp;
     */
    public String convertToDisplayText() {

        return null;
    }

    /**
     <html>
     <head>
     </head>
     <body>
     <b><font face="Courier" size="10" color="black">\begin{lstlisting}[language=Python, caption=</font><font face="Courier" size="10" color="red"><span id="1" class="--TITLE--">--TITLE--</span></font><font face="Courier" size="10" color="black">]<br></font><font face="Courier" size="10" color="red"><span id="2" class="--CODE--">public&nbsp;void&nbsp;updateJPanel(){<br>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;textArea.setText(manager.getCurrentSelectedSection().getDisplayCode());<br>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;textArea.setCaretPosition(0);<br>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.println("Updated&nbsp;textEditor&nbsp;from&nbsp;Jpanel");<br>
     &nbsp;&nbsp;&nbsp;&nbsp;}</span></font><font face="Courier" size="10" color="black"><br>
     \end{lstlisting}<br></font></b>
     </body>
     </html>
     */



}
