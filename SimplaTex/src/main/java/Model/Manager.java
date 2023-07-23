package Model;

public class Manager {

    private SectionManager sectionManager;
    private EditorManager editorManager;

    public Manager() {
        Initialise();
    }

    private void Initialise(){
        sectionManager = new SectionManager();
    }


    public void SectionsOrderChanged(int indexA,int indexB){
        sectionManager.SectionsOrderChanged(indexA, indexB);
        displaySelectedSectionCode();
    }

    public SectionManager getSectionManager() {
        return sectionManager;
    }

    public int getSelectedCurrentSection() {
        return sectionManager.getSelectedCurrentSection();
    }

    public void setSelectedCurrentSection(int selectedCurrentSection) {
        sectionManager.setSelectedCurrentSection(selectedCurrentSection);
    }

    public void addToCurrentSection(int index){
        sectionManager.addToCurrentSection(index);
        displaySelectedSectionCode();
    }

    public Section getCurrentSelectedSection(){
        return sectionManager.getCurrentSelectedSection();
    }

    public void displaySelectedSectionCode(){
        //editorManager.displaySelectedCode(getCurrentSelectedSection());
    }
}
