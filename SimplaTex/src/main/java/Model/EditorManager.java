package Model;

import Patterns.Observable;

public class EditorManager extends Observable {

    public void displaySelectedCode(Section section){
        updateEditor();
    }


}
