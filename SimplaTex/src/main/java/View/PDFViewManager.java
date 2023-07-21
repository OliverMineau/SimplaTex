package View;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import javax.swing.*;
import java.nio.file.Path;

public class PDFViewManager{
    private SwingController controller;
    private SwingViewBuilder factory;
    private JPanel pdfPanel;

    public PDFViewManager(){

        // build a controller
        controller = new SwingController();
        // Build a SwingViewFactory configured with the controller
        factory = new SwingViewBuilder(controller);
        // Use the factory to build a JPanel that is pre-configured
        // with a complete, active Viewer UI.
        pdfPanel = factory.buildViewerPanel();

        // add copy keyboard command
        ComponentKeyBinding.install(controller, pdfPanel);
        // add interactive mouse link annotation support via callback
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));
    }

    public void openPDF(Path filePath){
        // Open a PDF document to view
        System.out.println("PDF path to open : " + filePath.toString());
        controller.openDocument(filePath.toString());
        controller.setPageFitMode(4,true);
    }

    public JPanel getPanel(){
        return pdfPanel;
    }

}
