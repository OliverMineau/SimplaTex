package View;

import Controller.Controller;
import Controller.DragListener;
import Model.Manager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SectionPanel extends JPanel {

    public SectionPanel(String name, int index, Manager manager, Controller controller, boolean draggable) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //JPanel itemPanel = new RoundedCornerPanel(new FlowLayout(FlowLayout.LEADING), 30);
        JPanel itemPanel = new JPanel(new BorderLayout());


        if(draggable){
            DragListener drag = new DragListener(controller);
            addMouseListener(drag);
            addMouseMotionListener(drag);
        }else{
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {

                    controller.selectedAvailableSection(index);
                }
            });
        }

        //Set Background color
        itemPanel.setBackground(Color.GRAY);

        //Set the text
        JLabel label = new JLabel(name);
        label.setFont(new Font("Arial", Font.BOLD, 20));

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

        itemPanel.add(imageLabel, BorderLayout.LINE_START);
        itemPanel.add(label, BorderLayout.CENTER);

        add(itemPanel);

        add(Box.createVerticalStrut(20));

        setMaximumSize(new Dimension(1920,120));
    }
}
