package Sections;

import Model.Section;
import View.SimpleEditors.Editor_Title;

import java.nio.file.Path;

public class Section_Title extends Section {

    public Section_Title() {
        this.editor = new Editor_Title(this);
        this.name = "Title";
        this.displayCode = FONT_START + loadTex(Path.of("res/TexSections/Title.simplatex")) + FONT_END;
    }
}
