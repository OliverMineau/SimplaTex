package View;

import Controller.Controller;
import Controller.DragListener;
import Model.Manager;
import Patterns.Observer;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SectionPanel extends JPanel implements Observer {

    JPanel itemPanel;
    Manager manager;
    private boolean draggable;

    public SectionPanel(String name, int index, Manager manager, Controller controller, boolean draggable) {
        this.manager = manager;
        this.draggable = draggable;

        //Subscribe to observer
        if(draggable){
            //manager.getSectionManager().addObserver(this);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //JPanel itemPanel = new RoundedCornerPanel(new FlowLayout(FlowLayout.LEADING), 30);
        itemPanel = new JPanel(new BorderLayout());


        if(draggable){
            DragListener drag = new DragListener(controller);
            addMouseListener(drag);
            addMouseMotionListener(drag);

            addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent focusEvent) {
                    setSelected();
                }

                @Override
                public void focusLost(FocusEvent focusEvent) {
                    setUnSelected();
                }
            });

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

        setBounds(new Rectangle(new Dimension(1920,120)));
    }

    public void setSelected(){
        itemPanel.setBorder(new LineBorder(Color.BLACK,4, true));
    }

    private void setUnSelected(){
        itemPanel.setBorder(null);
    }

    @Override
    public void update() {

    }
}
