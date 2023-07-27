package View;

import Controller.Controller;
import Controller.DragListener;
import Model.Manager;
import Patterns.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

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

        JLabel imageLabel = createImage("/move.png", 30, 30);

        //Set the Image
        if(draggable){
            JLabel binImg = createImage("/bin.png", 30, 30);
            binImg.setBorder(new EmptyBorder(40, 40, 40, 40));
            itemPanel.add(binImg, BorderLayout.LINE_END);

            Component me = this;
            binImg.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {

                    if(manager.getSectionManager().getCurrentSections().size() == 1) return;

                    Container parent = getParent();
                    parent.remove(me);

                    controller.deleteSection(index);
                }

                @Override
                public void mousePressed(MouseEvent mouseEvent) {}
                @Override
                public void mouseReleased(MouseEvent mouseEvent) {}
                @Override
                public void mouseEntered(MouseEvent mouseEvent) {}
                @Override
                public void mouseExited(MouseEvent mouseEvent) {}
            });
        }


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

    private JLabel createImage(String path, int width, int height){
        URL url = getClass().getResource(path);

        ImageIcon icon = new ImageIcon(url);
        // Resize the image
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        return new JLabel(resizedIcon);
    }
}
