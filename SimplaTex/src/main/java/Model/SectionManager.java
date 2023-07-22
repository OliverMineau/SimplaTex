package Model;

import java.util.ArrayList;
import java.util.List;


public class SectionManager {

    private ArrayList<Section> currentSections;
    private int selectedCurrentSection;
    private List<Section> availableSections;


    public SectionManager() {
        LoadAvailableSections();
        LoadCurrentSections();
        selectedCurrentSection = 0;
    }

    private void LoadAvailableSections(){
        availableSections = new ArrayList<>();
        availableSections.add(new Section("Main Page"));
        availableSections.add(new Section("Title"));
        availableSections.add(new Section("Description"));
        availableSections.add(new Section("Short Code"));
        availableSections.add(new Section("Code Block"));
        availableSections.add(new Section("Image"));
        availableSections.add(new Section("Credits"));
        availableSections.add(new Section("Installation"));
        availableSections.add(new Section("Example"));

    }

    private void LoadCurrentSections(){
        currentSections = new ArrayList<>();
        currentSections.add(new Section("Main Page"));
        currentSections.add(new Section("Title"));
    }

    public ArrayList<Section> getCurrentSections() {
        return currentSections;
    }
    public List<Section> getAvailableSections() {
        return availableSections;
    }


    public void SectionsOrderChanged(int indexA, int indexB){
        availableSections.add(indexB, availableSections.remove(indexA));
        System.out.println("New sections order : " + availableSections);
    }

    public int getSelectedCurrentSection() {
        return selectedCurrentSection;
    }

    public void setSelectedCurrentSection(int selectedCurrentSection) {
        this.selectedCurrentSection = selectedCurrentSection;
    }

    public void addToCurrentSection(int index){

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
    }
}
