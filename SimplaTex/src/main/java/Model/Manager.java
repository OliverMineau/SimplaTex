package Model;

public class Manager {

    private SectionManager sectionManager;

    public Manager() {
        Initialise();
    }

    private void Initialise(){
        sectionManager = new SectionManager();
    }


    public void SectionsOrderChanged(int indexA,int indexB){
        sectionManager.SectionsOrderChanged(indexA, indexB);
    }

    public SectionManager getSectionManager() {
        return sectionManager;
    }
}
