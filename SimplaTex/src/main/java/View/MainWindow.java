package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.nio.file.Path;

public class MainWindow{

    public MainWindow(JFrame frame, Path docPath) {

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLUE);

        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(Color.RED);


        //String filePath = "/home/oliver/Downloads/SimplaTex/exampleTexMex.pdf";


        PDFViewManager pdfViewManager = new PDFViewManager();

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(1,3));
        container.add(leftPanel);
        container.add(middlePanel);
        container.add(pdfViewManager.getPanel());

        frame.add(container);

        pdfViewManager.openPDF(docPath);

    }

}
