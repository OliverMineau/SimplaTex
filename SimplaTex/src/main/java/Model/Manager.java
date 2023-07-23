package Model;

import java.nio.file.Path;

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
    }

    public SectionManager getSectionManager() {
        return sectionManager;
    }

    public int getSelectedCurrentSection() {
        return sectionManager.getSelectedCurrentSection();
    }

    public void addToCurrentSection(int index){
        sectionManager.addToCurrentSection(index);
    }

    public Section getCurrentSelectedSection(){
        return sectionManager.getCurrentSelectedSection();
    }

    /*public String getFullLatexCode(){
        LatexCodeMerger latexCodeMerger = new LatexCodeMerger(getSectionManager().getCurrentSections());
        return latexCodeMerger.Merge();
    }*/


}
