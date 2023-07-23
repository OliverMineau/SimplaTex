package Patterns;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Observable {

    List<Observer> observers;

    public Observable() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void update() {
        Iterator<Observer> it;

        it = observers.iterator();
        while (it.hasNext()) {
            Observer o = it.next();
            o.update();
        }
    }

    public void updateEditor() {
        Iterator<Observer> it;

        it = observers.iterator();
        while (it.hasNext()) {
            Observer o = it.next();
            o.updateEditor();
        }
    }

}
