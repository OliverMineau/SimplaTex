package Sections;

import Model.Section;
import View.SimpleEditors.Editor_Main_Page;

import java.nio.file.Path;

public class Section_Main_Page extends Section {

    String authorSeperator = "  \\vspace{\\baselineskip}\n<br>";

    public Section_Main_Page() {
        this.editor = new Editor_Main_Page(this);
        this.name = "Main Page";
        this.displayCode = FONT_START + loadTex(Path.of("res/TexSections/Main_Page.simplatex")) + FONT_END;
                /*FONT_START +
                "\\begin{titlepage}\n<br>" +
                "\\begin{center}\n<br>" +
                "\n<br>" +
                "\\includegraphics[width=0.4\\textwidth]{ " + element("--PATH TO LOGO--", "red") + " }~ \\\\[2cm]\n<br>" +
                "\n<br>" +
                "{\\Large\n<br>" +
                "  " + element("--LEVEL OF EDUCATION--", "red") + " \\\\[0.1cm]\n<br>" +
                "   " + element( "--FIELD OF STUDY--", "red") + " \\\\[1cm]\n<br>" +
                "}\n<br>" +
                "\n<br>" +
                "\\rule{\\linewidth}{0.5mm} \\\\[0.4cm]\n<br>" +
                "{\\LARGE \\textbf{ " + element( "--TITLE--", "red") + " \\\\[0.4cm]}\n<br>" +
                "\\rule{\\linewidth}{0.5mm}} \\\\[1.5cm]\n<br>" +
                "\n<br>" +

                "  " + multiElement( "--AUTHOR--",1, authorSeperator, "red") +
                "\n<br>" +
                "\n<br>" +
                "\\vfill\n<br>" +
                "\n<br>" +
                "{\\Large " + element( "--DATE--", "red") + "}\n<br>" +
                " \n<br>" +
                "\\end{center}\n<br>" +
                "\\end{titlepage}\n<br>" +
                FONT_END;*/

    }

}
