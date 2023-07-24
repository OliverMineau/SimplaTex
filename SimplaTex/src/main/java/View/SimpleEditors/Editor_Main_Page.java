package View.SimpleEditors;

import Model.Section;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Editor_Main_Page extends SimpleEditorHelper{

    JTextField imagePath;
    JTextField edLevel;
    JTextField fieldStdy;
    JTextField title;
    ArrayList<JTextField> authors;
    JTextField date;

    Section section;

    public Editor_Main_Page(Section section) {

        this.section = section;

        imagePath = createImagePanel("Logo Image Path");
        edLevel = createTextPanel("Level of Education :");
        fieldStdy = createTextPanel("Field of Study :");
        title = createTextPanel("Title :");
        authors = createMultipleTexts("Authors :");
        date = createTextPanel("Date :");
    }

    @Override
    public void updateViews() {

        HashMap<String, String> info = section.getInfo();

        String value;

        if((value=info.get("ImagePath")) != null) imagePath.setText(value + "YAYAY");
        if((value=info.get("EdLevel")) != null) edLevel.setText(value);
        if((value=info.get("FieldStudy")) != null) fieldStdy.setText(value);
        if((value=info.get("Title")) != null) title.setText(value);
        if((value=info.get("Date")) != null) date.setText(value);

        int maxIndex = authors.size(), index = 0;
        String multVal = "";
        while (index < maxIndex && multVal != null){
            if((multVal=info.get("Author" + index)) != null) authors.get(index).setText(multVal);
            index++;
        }

        System.out.println("Updated SimpleEditor");
        revalidate();
        repaint();
    }

    @Override
    public void saveViews() {

        section.addInfo("1", imagePath.getText());
        section.addInfo("2", edLevel.getText());
        section.addInfo("3", fieldStdy.getText());
        section.addInfo("4", title.getText());

        ArrayList<String> authorsText = new ArrayList<>();
        for (int i = 0; i < authors.size(); i++) {
            authorsText.add(authors.get(i).getText());
        }
        //section.addInfos("5", authorsText);
        section.addInfo("5", authorsText.get(0));

        section.addInfo("6", date.getText());

        System.out.println("Saved SimpleEditor");

    }

}
