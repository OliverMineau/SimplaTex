package Sections;

import Model.Section;
import View.SimpleEditors.Editor_Title;

public class Section_Title extends Section {

    public Section_Title() {
        this.editor = new Editor_Title(this);
        this.name = "Title";
        this.displayCode = "";
    }
}
