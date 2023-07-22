package Controller;
import Model.Manager;

public class Controller {

    private Manager manager;

    public Controller(Manager manager) {
        this.manager = manager;
    }

    public void Save(){
        System.out.println("App is Saving");
    }

    public void SectionsOrderChanged(int indexA,int indexB){
        System.out.println("Scetion " + indexA + " <> " + indexB);
        manager.SectionsOrderChanged(indexA, indexB);
    }


}
