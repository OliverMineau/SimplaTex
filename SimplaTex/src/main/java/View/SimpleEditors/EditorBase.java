package View.SimpleEditors;

import Model.Section;
import Model.StringElement;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.ArrayList;
import java.util.HashMap;

public class EditorBase extends SimpleEditorHelper{

    Section section;
    HashMap<String, JTextComponent> components;
    public EditorBase(Section section) {

        this.section = section;

        /**
         -Get the elements from the code
            section.getLatexDocument().getElements
         -Store the elements
         -For each element get its type and create the corresponding element
            add to components
         */


        /** Example
        for (StringElement elm : section.getLatexDocument().getElements()) {
            if(elm.getType().equals("TextField")){
                components.put(elm.getId(), createTextPanel(elm.getTitle()));

            }else if(elm.getType().equals("TextArea")){
                int index = 0;
                //Bien passer components pour le mouse click
                for(JTextField txt : createTextAreaPanel(elm.getTitle(), components)){
                    components.put(elm.getId() + "-" + index, txt);
                }

            }else if(elm.getType().equals("Image")){
                components.put(elm.getId(), createImagePanel(elm.getTitle()));
            }
        }*/


    }

    @Override
    public void updateViews() {

        /*HashMap<String, String> info = section.getInfo();

        String value;
        if((value=info.get("1")) != null) title.setText(value);
        if((value=info.get("2")) != null) language.setText(value);
        if((value=info.get("3")) != null) codeBlock.setText(value);
        */

        /** Example
         HashMap<String, String> info = section.getInfo();

         for (String key: info.keySet()) {
            String value;
            if((value=info.get(key)) != null) components.get(key).setText(value);
         }

         revalidate();
         repaint();
         */

    }

    @Override
    public void saveViews() {
        /*section.addInfo("1", language.getText());
        section.addInfo("2", title.getText());
        section.addInfo("3", codeBlock.getText());*/

        /** Example
         for (String key: components.keySet()) {
            section.addInfo(key, components.get(key).getText());
         }
         */
    }
}
