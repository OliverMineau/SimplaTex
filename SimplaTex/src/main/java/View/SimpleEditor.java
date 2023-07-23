package View;

import Controller.Controller;
import Model.Manager;
import Patterns.Observer;

import javax.swing.*;

public class SimpleEditor extends JPanel implements Observer {

    Manager manager;
    Controller controller;

    public SimpleEditor(Manager manager, Controller controller) {
        this.manager = manager;
        this.controller = controller;
    }
}
