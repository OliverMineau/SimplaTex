package Sections;

import Model.Section;

import java.awt.*;

public class Section_Main_Page extends Section {

    public Section_Main_Page() {
        this.name = "Main Page";
        this.displayCode =
                FONT_START +
                "\\begin{titlepage}\n<br>" +
                "\\begin{center}\n<br>" +
                "\n<br>" +
                "\\includegraphics[width=0.4\\textwidth]{ " + RED + "--PATH TO LOGO--" + END + " }~ \\\\[2cm]\n<br>" +
                "\n<br>" +
                "{\\Large\n<br>" +
                "  " + RED + "--LEVEL OF EDUCATION--" + END + " \\\\[0.1cm]\n<br>" +
                "   " + RED + "--FIELD OF STUDY--" + END + " \\\\[1cm]\n<br>" +
                "}\n<br>" +
                "\n<br>" +
                "\\rule{\\linewidth}{0.5mm} \\\\[0.4cm]\n<br>" +
                "{\\LARGE \\textbf{ " + RED + "--TITLE--" + END + " \\\\[0.4cm]}\n<br>" +
                "\\rule{\\linewidth}{0.5mm}} \\\\[1.5cm]\n<br>" +
                "\n<br>" +
                "{\\Large\n<br>" +
                "  " + RED + "--NAME--" + END + " \\\\\n<br>" +
                "  \\vspace{\\baselineskip}\n<br>" +
                "}\n<br>" +
                "\n<br>" +
                "\\vfill\n<br>" +
                "\n<br>" +
                "{\\Large " + RED + "--DATE--" + END + "}\n<br>" +
                " \n<br>" +
                "\\end{center}\n<br>" +
                "\\end{titlepage}\n<br>" +
                FONT_END;

    }
}
