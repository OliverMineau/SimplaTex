package View;

import Controller.Controller;
import Model.Manager;

import javax.swing.*;

public class Editors {

    Manager manager;
    Controller controller;
    SimpleEditor simpleEditor;
    TextEditor textEditor;

    public Editors(Manager manager, Controller controller) {
        this.manager = manager;
        this.controller = controller;

        simpleEditor = new SimpleEditor(manager, controller);
        textEditor = new TextEditor(manager, controller);

    }

    public JPanel getSimpleEditor() {
        return simpleEditor;
    }

    public JPanel getTextEditor() {
        return textEditor;
    }
}
