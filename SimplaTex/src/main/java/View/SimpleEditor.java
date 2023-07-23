package View;

import Controller.Controller;
import Model.Manager;
import Patterns.Observer;
import View.Editors.Editor_Main_Page;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;
import java.awt.*;
import java.io.File;

public class SimpleEditor extends JPanel implements Observer {

    Manager manager;
    Controller controller;

    public SimpleEditor(Manager manager, Controller controller) {
        this.manager = manager;
        this.controller = controller;

        setLayout(new GridLayout(1,2));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(new JScrollPane(new Editor_Main_Page()), BorderLayout.CENTER);

        JFileChooser rightPanel = new JFileChooser();
        rightPanel.setDragEnabled(true);
        rightPanel.setControlButtonsAreShown(false);
        rightPanel.setCurrentDirectory(controller.getDownloadPath().toFile());
        rightPanel.setFileSelectionMode(JFileChooser.FILES_ONLY);

        //TODO Marche pas affichage icons
        rightPanel.setFileView(new FileView(){
            public Icon getIcon(File f)
            {
                return FileSystemView.getFileSystemView().getSystemIcon(f);
            }
        });


        //rightPanel.setFileFilter( new FolderFilter());

        add(leftPanel);
        add(rightPanel);
    }


    private class FolderFilter extends javax.swing.filechooser.FileFilter {
        @Override
        public boolean accept( File file ) {
            return file.isDirectory();
        }

        @Override
        public String getDescription() {
            return "We only take directories";
        }
    }

}


