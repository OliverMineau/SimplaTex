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
        availableSections.add(new Section_Main_Page());
        availableSections.add(new Section_Title());
        availableSections.add(new Section_Description());
        availableSections.add(new Section_Short_Code());
        availableSections.add(new Section_Code_Block());
        availableSections.add(new Section_Image());
        availableSections.add(new Section_Credits());
        availableSections.add(new Section_Installation());
        availableSections.add(new Section_Example());

    }

    private void LoadCurrentSections(){
        currentSections = new ArrayList<>();
        currentSections.add(new Section_Main_Page());
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
            currentSections.add(availableSections.get(index));
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
}
