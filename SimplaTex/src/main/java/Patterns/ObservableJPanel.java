package Patterns;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObservableJPanel extends JPanel {

    List<Observer> observers;

    public ObservableJPanel() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer o) {

        if(!observers.contains(o)) observers.add(o);
    }

    public void update() {
        Iterator<Observer> it;

        it = observers.iterator();
        while (it.hasNext()) {
            Observer o = it.next();
            o.updateJPanel();
        }
    }

}
