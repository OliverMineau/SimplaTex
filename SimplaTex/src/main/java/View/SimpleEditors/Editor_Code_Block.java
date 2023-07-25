package View.SimpleEditors;

import Model.Section;

import javax.swing.*;
import java.util.HashMap;

public class Editor_Code_Block extends SimpleEditorHelper{

    JTextArea codeBlock;
    JTextField title;
    JTextField language;

    Section section;

    public Editor_Code_Block(Section section) {

        this.section = section;
        language = createTextPanel("Language");
        title = createTextPanel("Title");
        codeBlock = createTextAreaPanel("Code Block");
    }

    @Override
    public void updateViews() {

        /*HashMap<String, String> info = section.getInfo();

        String value;
        if((value=info.get("1")) != null) title.setText(value);
        if((value=info.get("2")) != null) language.setText(value);
        if((value=info.get("3")) != null) codeBlock.setText(value);

        revalidate();
        repaint();*/
    }

    @Override
    public void saveViews() {
        /*section.addInfo("1", language.getText());
        section.addInfo("2", title.getText());
        section.addInfo("3", codeBlock.getText());*/
    }

}
