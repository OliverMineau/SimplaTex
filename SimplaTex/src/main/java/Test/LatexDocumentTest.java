package Test;

import Model.LatexDocument;
import Model.StringElement;

public class LatexDocumentTest {

    static String input1 = "\\begin{lstlisting}[language=<span id=\"1\" class=\"Java|Language\" type=\"TextField\" style=\"color:red\">Java</span>, caption=<span id=\"2\" class=\"--TITLE--|Title\" type=\"TextField\" style=\"color:red\">--TITLE--</span>]\n" +
            "<span id=\"3\" class=\"--CODE--|Code\" type\"TextArea\" style=\"color:red\">--CODE--</span>\n" +
            "\\end{lstlisting}";

    static String input2 = "\\begin{titlepage}\n" +
            "\\begin{center}\n" +
            "\n" +
            "\\includegraphics[width=0.4\\textwidth]{ <span id=\"1\" class=\"--PATH-TO-LOGO--|Logo Path\" style=\"color:red\">--PATH-TO-LOGO--</span>  }~ \\\\[2cm]\n" +
            "\n" +
            "{\\Large\n" +
            "  <span id=\"2\" class=\"--LEVEL-OF-EDUCATION--|Level of education\" style=\"color:red\">--LEVEL-OF-EDUCATION--</span> \\\\[0.1cm]\n" +
            "  <span id=\"3\" class=\"--FIELD-OF-STUDY--|Field of study\" style=\"color:red\">--FIELD-OF-STUDY--</span> \\\\[1cm]\n" +
            "}\n" +
            "\n" +
            "\\rule{\\linewidth}{0.5mm} \\\\[0.4cm]\n" +
            "{\\LARGE \\textbf{ <span id=\"4\" class=\"--TITLE--|Title\" style=\"color:red\">--TITLE--</span> \\\\[0.4cm]}\n" +
            "\\rule{\\linewidth}{0.5mm}} \\\\[1.5cm]\n" +
            "\n" +
            "{\\Large\n" +
            "  <span id=\"5\" class=\"--AUTHOR--|Author\" style=\"color:red\">--AUTHOR--</span> \\\\\n" +
            "  <span class=\"skipline\">\\vspace{\\baselineskip}</span>\n" +
            "}\n" +
            "\n" +
            "\\vfill\n" +
            "\n" +
            "{\\Large <span id=\"6\" class=\"--DATE--|Date\" style=\"color:red\">--DATE--</span> }\n" +
            " \n" +
            "\\end{center}\n" +
            "\\end{titlepage}\n" +
            "\n" +
            "\\newpage";

    public static void main(String[] args) {
        LatexDocument latexDocument = new LatexDocument(input1);
        //LatexDocument latexDocument = new LatexDocument(input2);

        System.out.println("Parse Text : ");

        //latexDocument.addTextAfter("1", "This Is After Id 1"); Bon
        //latexDocument.addText("This Is At the end"); Bon

        /*StringElement stringElement = new StringElement("This Is After Id 3");
        stringElement.setType("TestType");
        StringElement stringElement2 = new StringElement("This Is at the end");
        stringElement2.setType("TestType");
        latexDocument.addElement(stringElement2);
        latexDocument.addElementAfter("3",stringElement); Bon*/

        //System.out.println(latexDocument.getElement("2"));

        latexDocument.printElements();

        System.out.println("\n\nPlain text : \n");
        System.out.println(latexDocument.convertToPlainLatex());

        System.out.println("\n\nDisplay text : \n");
        System.out.println(latexDocument.convertToDisplayText());

    }

}
