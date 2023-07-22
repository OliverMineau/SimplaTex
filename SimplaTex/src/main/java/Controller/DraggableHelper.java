package Controller;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DraggableHelper {

    private Point initialMouseLocation;
    private Point initialComponentLocation;

    public DraggableHelper(Component component) {

        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialMouseLocation = e.getLocationOnScreen();
                initialComponentLocation = component.getLocation();
            }
        });

        component.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point finalMouseLocation = e.getLocationOnScreen();
                int deltaY = finalMouseLocation.y - initialMouseLocation.y;
                Point newLocation = new Point(initialComponentLocation.x, initialComponentLocation.y + deltaY);
                component.setLocation(newLocation);
            }
        });
    }

}