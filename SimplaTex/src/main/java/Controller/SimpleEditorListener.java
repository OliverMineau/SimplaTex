package Controller;

import Patterns.Observable;
import View.SimpleEditors.SimpleEditorHelper;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SimpleEditorListener implements DocumentListener {

    SimpleEditorHelper simpleEditorHelper;
    public SimpleEditorListener(SimpleEditorHelper simpleEditorHelper) {
        this.simpleEditorHelper = simpleEditorHelper;
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        simpleEditorHelper.notifyAndSave();
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        simpleEditorHelper.notifyAndSave();
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        simpleEditorHelper.notifyAndSave();

    }
}
