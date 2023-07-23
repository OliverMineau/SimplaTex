package Model;

import java.util.ArrayList;

public class LatexCodeMerger {

    ArrayList<Section> sections;
    String HEADER = "\\documentclass{article}\n" +
            "\\usepackage{graphicx} % Required for inserting images\n" +
            "\n" +
            "\\begin{document}";

    String FOOTER = "\\end{document}";

    public LatexCodeMerger(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public String Merge(){

        String output = HEADER;

        for (Section section : sections) {
            output += section.getLatex();
        }

        output += FOOTER;

        return output;
    }


    /**
     *
     * Manage Images and different OS
     *
     *
     *  String imagePath  = downloadFolderPath.resolve("Untitled.png").toString();
     *
     *         //Change command based on operating system
     *         String os = System.getProperty("os.name").toLowerCase();
     *         if (os.contains("win")) {
     *             // Windows
     *             imagePath = imagePath.replace("\\","/");
     *         }
     */


}
