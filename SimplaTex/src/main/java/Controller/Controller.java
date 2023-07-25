package Controller;
import Model.LatexManager;
import Model.Manager;

import java.nio.file.Path;

public class Controller {

    private Manager manager;
    LatexManager latexManager;

    public Controller(Manager manager) {
        this.manager = manager;
        latexManager = new LatexManager(getDownloadPath());
    }

    public Path getDownloadPath(){
        String home = System.getProperty("user.home");
        String filePath = home+"/Documents/SimplaTex/";
        return Path.of(filePath);
    }

    public String getFileName(){
        return "exampleTex";
    }

    public void Save(){
        System.out.println("App is Saving");
    }

    public void SectionsOrderChanged(int indexA,int indexB){
        System.out.println("Section " + indexA + " <> " + indexB);
        manager.SectionsOrderChanged(indexA, indexB);
    }

    public void selectedAvailableSection(int index){
        manager.addToCurrentSection(index);
    }

    public Path createPDF(){
        return latexManager.SaveToPDF(latexManager.Merge(manager.getSectionManager().getCurrentSections()), getFileName());
    }

    public void deleteSection(int index){
        manager.getSectionManager().deleteSection(index);
    }


}
