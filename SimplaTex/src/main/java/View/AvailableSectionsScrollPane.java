package View;


import Controller.Controller;
import Model.Manager;
import Model.Section;

import javax.swing.*;
import java.util.List;

public class AvailableSectionsScrollPane extends JScrollPane{

    Manager manager;
    Controller controller;

    public AvailableSectionsScrollPane(Manager manager, Controller controller) {

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

        List<Section> sections = manager.getSectionManager().getAvailableSections();
        for(int i = 0; i < sections.size(); i++) {

            Section section = sections.get(i);

            JPanel sectionPanel = new SectionPanel(section.getName(), i, manager, controller, false);

            contentPanel.add(sectionPanel);
        }
    }
}

