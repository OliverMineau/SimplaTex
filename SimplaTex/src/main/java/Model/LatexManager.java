package Model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class LatexManager {

    String HEADER = "\\documentclass{article}\n" +
            "\\usepackage{graphicx} % Required for inserting images\n" +
            "\n" +
            "\\begin{document}";

    String FOOTER = "\\end{document}";
    Path downloadFolderPath;

    public LatexManager(Path downloadFolderPath){

        this.downloadFolderPath = downloadFolderPath;


        //String fileName = "exampleTex";
        /*String latexCode = "\\documentclass{article}\n" +
                "\\begin{document}\n" +
                "Document Test 3, \\LaTeX!\n" +
                "\\end{document}";*/

        /*String imagePath  = downloadFolderPath.resolve("Untitled.png").toString();

        //Change command based on operating system
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // Windows
            imagePath = imagePath.replace("\\","/");
        }

        System.out.println(imagePath);

        String latexCode = "\\documentclass{article}\n" +
                "\\usepackage{graphicx} % Required for inserting images\n" +
                "\n" +
                "\\title{tt}\n" +
                "\\author{Oliver Mineau}\n" +
                "\\date{July 2023}\n" +
                "\n" +
                "\\begin{document}\n" +
                "\\begin{titlepage}\n" +
                "\\begin{center}\n" +
                "\n" +
                "\\includegraphics[width=0.4\\textwidth]{" + imagePath + "}~ \\\\[2cm]\n" +
                "\n" +
                "{\\Large\n" +
                "  LICENCE 3 \\\\[0.1cm]\n" +
                "  INFORMATIQUE \\\\[1cm]\n" +
                "}\n" +
                "\n" +
                "\\rule{\\linewidth}{0.5mm} \\\\[0.4cm]\n" +
                "{\\LARGE \\textbf{Dossier IA Groupe n°6 \\\\[0.4cm]}\n" +
                "\\rule{\\linewidth}{0.5mm}} \\\\[1.5cm]\n" +
                "\n" +
                "{\\Large\n" +
                "  MINEAU Oliver \\\\\n" +
                "  \\vspace{\\baselineskip}\n" +
                "}\n" +
                "\n" +
                "\\vfill\n" +
                "\n" +
                "{\\Large Mai 2023}\n" +
                " \n" +
                "\\end{center}\n" +
                "\\end{titlepage}\n" +
                "\n" +
                "\n" +
                "\n" +
                "\\end{document}\n";

        SaveToPDF(latexCode, fileName, downloadFolderPath);*/
    }

    public Path SaveToPDF(String latexCode, String fileName){

        try {
            //Create folder if doesn't exist
            Files.createDirectories(downloadFolderPath);

            //Delete last files
            Files.deleteIfExists(downloadFolderPath.resolve(fileName + ".pdf"));
            Files.deleteIfExists(downloadFolderPath.resolve(fileName + ".tex"));

            //Create the paths from the file name
            Path texPath = Paths.get(fileName + ".tex");

            //Write the latex to the file
            Files.write(texPath, latexCode.getBytes());

            //Change command based on operating system
            String os = System.getProperty("os.name").toLowerCase();

            //Change the output directory
            String cmd = "pdflatex -halt-on-error -output-directory " + downloadFolderPath + " " + texPath;

            String shell;
            String option;

            if (os.contains("win")) {
                // Windows
                shell = "cmd";
                option = "/c";
            } else {
                // Unix-based systems (Linux, macOS)
                shell = "bash";
                option = "-c";
            }

            // Compile the .tex file to generate a PDF
            ProcessBuilder processBuilder = new ProcessBuilder(shell, option, cmd);

            // Compile the .tex file to generate a PDF
            processBuilder.redirectErrorStream(true);

            File thisFile = new File(downloadFolderPath + "/error.ext");
            processBuilder.redirectError(thisFile);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            process.waitFor();

            // Move the generated Tex to the download folder
            Files.move(texPath, downloadFolderPath.resolve(texPath.getFileName()), REPLACE_EXISTING);

        } catch (IOException | InterruptedException e) {
            System.err.println("Error, PDF was not created !");
            e.printStackTrace();
            return null;
        }

        return downloadFolderPath.resolve(fileName + ".pdf");
    }

    public String Merge(ArrayList<Section> sections){

        String imagePath  = downloadFolderPath.resolve("Untitled.png").toString();
        String output = HEADER;

        for (Section section : sections) {
            output += section.getLatex();
        }

        output += FOOTER;

        output = output.replace("--PATH TO LOGO--", imagePath);

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
