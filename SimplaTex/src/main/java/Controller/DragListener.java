package Controller;

import java.awt.*;
import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

public class DragListener extends MouseInputAdapter
{
    private Point location;
    private MouseEvent pressed;
    private MouseEvent dragged;
    private MouseEvent dropped;
    private LayoutManager layout;
    private Rectangle originalBounds;
    private int originalZOrder;
    private Component spacer;
    private int indexA, indexB;

    private Controller controller;

    public DragListener(Controller controller) {
         this.controller = controller;
    }

    @Override
    public void mousePressed(MouseEvent me)
    {
        pressed = me;
        Component component = me.getComponent();
        Container parent = component.getParent();

        indexA = -1;
        for (int i = 0; i < parent.getComponentCount(); i++) {
            if(component == parent.getComponent(i)){
                indexA = i;
                break;
            }
        }

        originalBounds = component.getBounds();
        originalZOrder = parent.getComponentZOrder(component);
        parent.setPreferredSize(parent.getPreferredSize());
        layout = parent.getLayout();
        parent.setLayout(null);

        JPanel j = (JPanel) component;
        spacer = j.getComponent(1);

        parent.setComponentZOrder(component, 0);

        component.requestFocusInWindow();
    }

    @Override
    public void mouseDragged(MouseEvent me)
    {
        spacer.setVisible(false);

        JComponent source = (JComponent) me.getComponent();
        JComponent parent = (JComponent) source.getParent();

        Point p = me.getPoint();
        p = SwingUtilities.convertPoint(source, p, parent);

        Rectangle bounds = source.getBounds();
        bounds.setLocation(p);

        bounds.x -= pressed.getX();
        bounds.y -= pressed.getY();
        source.setLocation(0, bounds.y);
        parent.scrollRectToVisible(bounds);
    }

    @Override
    public void mouseReleased(MouseEvent me)
    {
        boolean moved = false;
        Component component = me.getComponent();
        Container parent = component.getParent();
        Point location = component.getLocation();

        if (location.y < 0)
        {
            parent.add(component, 0);
            controller.SectionsOrderChanged(indexA,0);
            moved = true;
        }
        else
        {
            for (int i = 0; i < parent.getComponentCount(); i++)
            {
                Component c = parent.getComponent(i);
                Rectangle bounds = c.getBounds();

                if (c == component)
                    bounds = originalBounds;

                //  Component is released in the space originally occupied
                //  by the component or over an existing component

                if (bounds.contains(0, location.y))
                {
                    if (c == component)
                    {
                        parent.setComponentZOrder(component, originalZOrder);
                    }
                    else
                    {
                        parent.add(component, i);
                    }

                    indexB = i;
                    if(indexB == 0) indexB = indexA;
                    controller.SectionsOrderChanged(indexA,indexB);
                    moved = true;
                    break;
                }
            }
        }

        //  Component is positioned below all components in the container

        if (!moved)
        {
            parent.add(component, parent.getComponentCount() - 1);
            controller.SectionsOrderChanged(indexA,parent.getComponentCount() - 1);
        }

        //  Restore layout manager
        parent.setPreferredSize( null );
        //parent.setBounds(originalBounds);
        parent.setLayout(layout);
        spacer.setVisible(true);
        parent.validate();
        parent.repaint();

        component.requestFocusInWindow();
    }

}