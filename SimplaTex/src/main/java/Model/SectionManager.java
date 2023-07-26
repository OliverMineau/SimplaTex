package Model;

import Patterns.Observable;
import Sections.*;

import java.util.ArrayList;
import java.util.List;


public class SectionManager extends Observable {

    private ArrayList<Section> currentSections;
    private int selectedCurrentSection;
    private int previousExchanged;
    private int previousSelectedCurrentSection;
    private List<Section> availableSections;


    public SectionManager() {
        LoadAvailableSections();
        LoadCurrentSections();
        selectedCurrentSection = 0;
    }

    private void LoadAvailableSections(){
        availableSections = new ArrayList<>();
        availableSections.add(newSectionFromName("Main Page"));
        availableSections.add(newSectionFromName("Code Block"));
        availableSections.add(newSectionFromName("Big Space"));
        availableSections.add(newSectionFromName("Image"));
        availableSections.add(newSectionFromName("Space"));
        availableSections.add(newSectionFromName("New Page"));
        availableSections.add(newSectionFromName("Section"));
        availableSections.add(newSectionFromName("Subsection"));
        availableSections.add(newSectionFromName("Section"));
        availableSections.add(newSectionFromName("Table Content"));
        availableSections.add(newSectionFromName("Text"));
    }

    private void LoadCurrentSections(){
        currentSections = new ArrayList<>();
        currentSections.add(newSectionFromName("Main Page"));
    }

    public ArrayList<Section> getCurrentSections() {
        return currentSections;
    }
    public List<Section> getAvailableSections() {
        return availableSections;
    }


    public void SectionsOrderChanged(int indexA, int indexB){
        currentSections.add(indexB, currentSections.remove(indexA));

        previousSelectedCurrentSection = selectedCurrentSection;
        selectedCurrentSection = indexB;
        previousExchanged = indexA;

        //System.out.println("New sections order : " + availableSections);
        System.out.println("selectedCurrentSection : " + selectedCurrentSection);

        update();
    }

    public int getSelectedCurrentSection() {
        return selectedCurrentSection;
    }

    public void setSelectedCurrentSection(int selectedCurrentSection) {
        this.selectedCurrentSection = selectedCurrentSection;
    }

    public int getPreviousSelectedCurrentSection() {
        return previousSelectedCurrentSection;
    }

    public int getPreviousExchanged() {
        return previousExchanged;
    }

    public void addToCurrentSection(int index){

        previousSelectedCurrentSection = selectedCurrentSection;

        int currentIndex = selectedCurrentSection+1;

        if( currentIndex >= currentSections.size())
            currentSections.add(newSectionFromName(availableSections.get(index).name));
        else
            currentSections.add(currentIndex, availableSections.get(index));

        selectedCurrentSection = currentIndex;
        refreshSectionsView();
    }

    public void refreshSectionsView(){
        System.out.println("Current sections : " + currentSections.toString());
        update();
    }

    public Section getCurrentSelectedSection(){
        return currentSections.get(selectedCurrentSection);
    }

    public void deleteSection(int index){
        currentSections.remove(index);

        selectedCurrentSection = (index<currentSections.size())?index:index-1;
        refreshSectionsView();
    }

    public Section newSectionFromName(String name){
        if(name.equals("Main Page")) return new CustomSection("Main Page",null,"res/TexSections/Main_Page.simplatex");
        if(name.equals("Code Block")) return new CustomSection("Code Block","res/TexSections/Code_Block_Header.simplatex","res/TexSections/Code_Block.simplatex");
        if(name.equals("Section")) return new CustomSection("Section",null,"res/TexSections/Section.simplatex");
        if(name.equals("Big Space")) return new CustomSection("Big Space",null,"res/TexSections/Big_Space.simplatex");
        if(name.equals("Image")) return new CustomSection("Image",null,"res/TexSections/Image.simplatex");
        if(name.equals("Space")) return new CustomSection("Space",null,"res/TexSections/Space.simplatex");
        if(name.equals("New Page")) return new CustomSection("New Page",null,"res/TexSections/New_Page.simplatex");
        if(name.equals("Subsection")) return new CustomSection("Subsection",null,"res/TexSections/Subsection.simplatex");
        if(name.equals("Table Content")) return new CustomSection("Table Content",null,"res/TexSections/Table_Of_Contents.simplatex");
        if(name.equals("Text")) return new CustomSection("Text",null,"res/TexSections/Text.simplatex");

        return null;
    }
}
