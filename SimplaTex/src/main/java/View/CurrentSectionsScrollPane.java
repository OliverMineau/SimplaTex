package View;


import Controller.DragListener;
import Model.Manager;
import Model.Section;
import Controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CurrentSectionsScrollPane extends JScrollPane{

    Manager manager;
    Controller controller;

    public CurrentSectionsScrollPane(Manager manager, Controller controller) {

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

        ArrayList<Section> sections = manager.getSectionManager().getCurrentSections();
        for(int i = 0; i < sections.size(); i++) {

            Section section = sections.get(i);

            JPanel sectionPanel = new SectionPanel(section.getName(), i, manager, controller, true);

            contentPanel.add(sectionPanel);
        }
    }
}

