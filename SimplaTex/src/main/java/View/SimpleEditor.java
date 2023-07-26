package View;

import Controller.Controller;
import Model.Manager;
import Patterns.Observer;
import View.SimpleEditors.SimpleEditorHelper;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class SimpleEditor extends JPanel implements Observer {

    Manager manager;
    Controller controller;
    SimpleEditorHelper selectedSimpleEditor;
    JScrollPane scrollPane;
    JPanel leftPanel;

    public SimpleEditor(Manager manager, Controller controller) {
        this.manager = manager;
        this.controller = controller;

        manager.getSectionManager().addObserver(this);

        setLayout(new GridLayout(1, 2));

        leftPanel = new JPanel(new BorderLayout());

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
        scrollPane.setViewportView(editor);

        boolean enabled = manager.getSectionManager().getCurrentSelectedSection().simpleEditorEnabled;
        HashMap<String, JTextComponent> components = manager.getSectionManager().getCurrentSelectedSection().editor.components;
        for (JTextComponent c : components.values()) {
            c.setEnabled(enabled);
            c.setDisabledTextColor(Color.gray);
            c.setBackground((enabled)?Color.white:null);
        }
        editor.setEnabled(enabled);
        leftPanel.setEnabled(enabled);
        scrollPane.setEnabled(enabled);
        scrollPane.getHorizontalScrollBar().setEnabled(enabled);
        scrollPane.getVerticalScrollBar().setEnabled(enabled);
        scrollPane.getViewport().getView().setEnabled(enabled);
        scrollPane.setWheelScrollingEnabled(enabled);

        //editor.updateViews();
    }

}
