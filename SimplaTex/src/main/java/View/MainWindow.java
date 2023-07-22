package View;

import Controller.Controller;
import Model.Manager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.nio.file.Path;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

public class MainWindow{

    JFrame frame;
    Manager manager;
    Controller controller;
    PDFViewManager pdfViewManager;


    public MainWindow(JFrame frame, Path docPath, Manager manager, Controller controller) {

        this.controller = controller;
        this.manager = manager;
        this.frame = frame;

        frame.setJMenuBar(createMenuBar());

        //ScrollPanel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLUE);
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(new SectionsScrollPane(manager, controller));

        //TextEditor
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setFont( new Font("Courier", Font.PLAIN, 30));
        JScrollPane textEditorScroll = new JScrollPane (textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        middlePanel.add(textEditorScroll);


        //String filePath = "/home/oliver/Downloads/SimplaTex/exampleTexMex.pdf";

        pdfViewManager = new PDFViewManager();


        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                true,
                leftPanel,
                middlePanel);

        sp.setResizeWeight(0.1);

        JSplitPane container = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                true,
                sp,
                pdfViewManager.getPanel());

        container.setResizeWeight(0.8);

        frame.add(container);

        pdfViewManager.openPDF(docPath);

    }

    private JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutItem = new JMenuItem("Cut");
        editMenu.add(cutItem);
        JMenuItem copyItem = new JMenuItem("Copy");
        editMenu.add(copyItem);
        JMenuItem pasteItem = new JMenuItem("Paste");
        editMenu.add(pasteItem);
        return editMenu;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        fileMenu.add(newItem);
        fileMenu.add(new JSeparator());
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(this::mnuNewListener);
        fileMenu.add(saveItem);
        return fileMenu;
    }

    private JMenuItem createSaveMenu() {
        JMenuItem saveButton = new JMenuItem("Save");
        saveButton.setMnemonic( 'S' );
        saveButton.addActionListener(this::mnuNewListener);

        return saveButton;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createEditMenu());
        menuBar.add(createSaveMenu());
        return menuBar;
    }

    public void mnuNewListener( ActionEvent event ) {
        System.out.println("Clicked");

        JMenuItem menuItem = (JMenuItem) event.getSource();
        if(menuItem.getMnemonic() == 'S'){
            controller.Save();
        }
    }

}
