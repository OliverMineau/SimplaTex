package View;


import Controller.DragListener;
import Model.Manager;
import Model.Section;
import Controller.Controller;
import Patterns.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CurrentSectionsScrollPane extends JScrollPane implements Observer {

    Manager manager;
    Controller controller;
    private JPanel contentPanel;
    private SectionPanel lastAdded;

    public CurrentSectionsScrollPane(Manager manager, Controller controller) {

        this.manager = manager;
        this.controller = controller;

        manager.getSectionManager().addObserver(this);


        // Create components to be put inside the JScrollPane
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        //Set the items
        SetSections();

        //Set the content
        setViewportView(contentPanel);

        //Options
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    }

    private void SetSections(){

        ArrayList<Section> sections = manager.getSectionManager().getCurrentSections();
        for(int i = 0; i < sections.size(); i++) {

            Section section = sections.get(i);

            SectionPanel sectionPanel = new SectionPanel(section.getName(), i, manager, controller, true);

            contentPanel.add(sectionPanel);

            if(manager.getSectionManager().getSelectedCurrentSection() == i){
                sectionPanel.setFocusable(true);
                sectionPanel.requestFocusInWindow();
                lastAdded = sectionPanel;
            }
        }
    }

    @Override
    public void update() {

        contentPanel.removeAll();
        SetSections();
        contentPanel.revalidate();
        contentPanel.repaint();

        //TODO : Set selected component in view
        JScrollBar verticalScrollBar = getVerticalScrollBar();
        int val = 120 * manager.getSelectedCurrentSection();
        verticalScrollBar.setValue(val);

    }

}

