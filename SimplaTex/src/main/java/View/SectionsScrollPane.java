package View;


import Controller.DragListener;
import Controller.DraggableHelper;
import Model.Manager;
import Model.Section;
import Controller.Controller;

import javax.print.attribute.standard.JobImpressions;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class SectionsScrollPane extends JScrollPane{

    Manager manager;
    Controller controller;

    public SectionsScrollPane(Manager manager, Controller controller) {

        this.manager = manager;
        this.controller = controller;

        // Create components to be put inside the JScrollPane
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        //Set the items
        SetSections(contentPanel);

        //Set the content
        setViewportView(contentPanel);

        //Options
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    }

    private void SetSections(JPanel contentPanel){

        for(Section section: manager.getSectionManager().getSections()) {

            JPanel item = new JPanel();
            item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));

            //JPanel itemPanel = new RoundedCornerPanel(new FlowLayout(FlowLayout.LEADING), 30);
            JPanel itemPanel = new JPanel(new BorderLayout());


            //DraggableHelper draggableHelper = new DraggableHelper(itemPanel);
            DragListener drag = new DragListener(controller);
            item.addMouseListener(drag);
            item.addMouseMotionListener(drag);


            //Set Background color
            itemPanel.setBackground(Color.GRAY);

            //Set the text
            JLabel label = new JLabel(section.getName());
            label.setFont(new Font("Arial", Font.BOLD, 30));

            //Set the Image
            ImageIcon icon = new ImageIcon("res/move.png");
            // Resize the image
            int imageWidth = 30;
            int imageHeight = 30;
            Image image = icon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(image);
            JLabel imageLabel = new JLabel(resizedIcon);

            //Set the padding
            int paddingBetweenComponents = 40;
            label.setBorder(new EmptyBorder(paddingBetweenComponents, paddingBetweenComponents, paddingBetweenComponents, paddingBetweenComponents));
            imageLabel.setBorder(new EmptyBorder(paddingBetweenComponents, paddingBetweenComponents, paddingBetweenComponents, paddingBetweenComponents));

            //Set spacing between sections
            int verticalSpacing = 10;
            //contentPanel.add(Box.createVerticalStrut(verticalSpacing));

            itemPanel.add(imageLabel, BorderLayout.LINE_START);
            itemPanel.add(label, BorderLayout.CENTER);

            item.add(itemPanel);

            Component space = Box.createVerticalStrut(10);
            item.add(Box.createVerticalStrut(50));

            contentPanel.add(item);
        }

    }
}

