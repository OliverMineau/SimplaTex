package View;

import Controller.Controller;
import Model.Manager;
import Patterns.Observer;
import View.SimpleEditors.SimpleEditorHelper;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;
import java.awt.*;
import java.io.File;

public class SimpleEditor extends JPanel implements Observer {

    Manager manager;
    Controller controller;
    SimpleEditorHelper selectedSimpleEditor;
    JScrollPane scrollPane;

    public SimpleEditor(Manager manager, Controller controller) {
        this.manager = manager;
        this.controller = controller;

        manager.getSectionManager().addObserver(this);

        setLayout(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel(new BorderLayout());

        // Create the Editor_Main_Page inside a JScrollPane
        scrollPane = new JScrollPane();

        // Set the scroll pane size policy to fill the available space
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        leftPanel.add(scrollPane, BorderLayout.CENTER);
        add(leftPanel);

        JFileChooser rightPanel = new JFileChooser();
        rightPanel.setDragEnabled(true);
        rightPanel.setControlButtonsAreShown(false);
        rightPanel.setCurrentDirectory(controller.getDownloadPath().toFile());
        rightPanel.setFileSelectionMode(JFileChooser.FILES_ONLY);

        //TODO Marche pas affichage icons
        rightPanel.setFileView(new FileView() {
            public Icon getIcon(File f) {
                return FileSystemView.getFileSystemView().getSystemIcon(f);
            }
        });

        add(rightPanel);
    }


    @Override
    public void update() {
        SimpleEditorHelper editor = manager.getSectionManager().getCurrentSelectedSection().editor;
        //editor.updateViews();
        scrollPane.setViewportView(editor);
    }

}
