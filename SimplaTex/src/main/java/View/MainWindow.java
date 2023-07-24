package View;

import Controller.Controller;
import Model.Manager;

import javax.swing.*;
import javax.xml.stream.events.EndDocument;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Path;

public class MainWindow{

    JFrame frame;
    Manager manager;
    Controller controller;
    PDFViewManager pdfViewManager;

    JSplitPane leftSplitPanel,editorSplitPanel,middleSplitPanel,rightSplitPanel;


    public MainWindow(JFrame frame, Manager manager, Controller controller) {

        this.controller = controller;
        this.manager = manager;
        this.frame = frame;

        frame.setJMenuBar(createMenuBar());

        //ScrollPanel
        //Current sections
        JPanel leftTopPanel = new JPanel();
        leftTopPanel.setLayout(new BorderLayout());
        leftTopPanel.add(new CurrentSectionsScrollPane(manager, controller));

        //Available sections
        JPanel leftBottomPanel = new JPanel();
        leftBottomPanel.setLayout(new BorderLayout());
        leftBottomPanel.add(new AvailableSectionsScrollPane(manager, controller));

        leftSplitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                true,
                leftTopPanel,
                leftBottomPanel);
        leftSplitPanel.setResizeWeight(0.7);
        leftSplitPanel.setDividerSize(50);
        leftSplitPanel.setMinimumSize(new Dimension(450,1));
        leftTopPanel.setMinimumSize(new Dimension(1,500));
        leftBottomPanel.setMinimumSize(new Dimension(1,500));


        //TextEditor & Simple Editor
        Editors editors = new Editors(manager, controller);
        JPanel simpleEditor = editors.getSimpleEditor();
        JPanel textEditor = editors.getTextEditor();
        editorSplitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                true,
                simpleEditor,
                textEditor);
        editorSplitPanel.setResizeWeight(0.3);


        //String filePath = "/home/oliver/Downloads/SimplaTex/exampleTexMex.pdf";

        //PDF
        pdfViewManager = new PDFViewManager();
        middleSplitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                true,
                leftSplitPanel,
                editorSplitPanel);

        middleSplitPanel.setResizeWeight(0);

        rightSplitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                true,
                middleSplitPanel,
                pdfViewManager.getPanel());

        rightSplitPanel.setResizeWeight(1);
        rightSplitPanel.setDividerLocation(0.7);

        frame.add(rightSplitPanel);

        //pdfViewManager.openPDF(docPath);

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

    private JButton createSaveMenu() {
        JButton saveButton = new JButton("Save");
        saveButton.setMnemonic(KeyEvent.VK_S);
        saveButton.addActionListener(this::mnuNewListener);
        return saveButton;
    }

    private JButton createResetViewMenu() {
        JButton resetButton = new JButton("Reset View");
        resetButton.setMnemonic(KeyEvent.VK_R);
        resetButton.addActionListener(this::mnuNewListener);
        return resetButton;
    }

    private JButton compileMenu() {
        JButton resetButton = new JButton("Create PDF");
        resetButton.setMnemonic(KeyEvent.VK_C);
        resetButton.addActionListener(this::mnuNewListener);
        return resetButton;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createEditMenu());
        menuBar.add(createSaveMenu());
        menuBar.add(createResetViewMenu());
        menuBar.add(compileMenu());
        return menuBar;
    }

    public void mnuNewListener( ActionEvent event ) {
        System.out.println("Clicked");

        JButton menuItem = (JButton) event.getSource();
        switch(menuItem.getMnemonic()){
            case KeyEvent.VK_S:
                controller.Save();
                break;
            case KeyEvent.VK_R:
                resetViews();
                break;
            case KeyEvent.VK_C:
                Path path = controller.createPDF();
                pdfViewManager.openPDF(path);
                break;
        }

    }

    private void resetViews(){
        leftSplitPanel.resetToPreferredSizes();
        editorSplitPanel.resetToPreferredSizes();
        middleSplitPanel.resetToPreferredSizes();
        rightSplitPanel.setDividerLocation(0.7);
        rightSplitPanel.resetToPreferredSizes();

    }

}
