package View.SimpleEditors;

import Model.Section;

import javax.swing.*;
import java.util.HashMap;

public class Editor_Title extends SimpleEditorHelper{

    JTextField title;

    Section section;

    public Editor_Title(Section section) {

        this.section = section;
        title = createTextPanel("Title :");
    }

    @Override
    public void updateViews() {

        HashMap<String, String> info = section.getInfo();

        String value;
        if((value=info.get("Title")) != null) title.setText(value);

        revalidate();
        repaint();
    }

    @Override
    public void saveViews() {
        section.addInfo("Title", title.getText());
    }

}
