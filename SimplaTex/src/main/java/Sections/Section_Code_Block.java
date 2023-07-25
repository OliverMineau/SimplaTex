package Sections;

import Model.Section;
import View.SimpleEditors.Editor_Code_Block;
import View.SimpleEditors.Editor_Title;
import View.SimpleEditors.SimpleEditorHelper;

import java.nio.file.Path;

public class Section_Code_Block extends Section {

    public Section_Code_Block() {
        this.editor = new Editor_Code_Block(this);
        this.name = "Code Block";
        this.header = loadPlainTex(Path.of("res/TexSections/Code_Block_Header.simplatex"));
        //this.displayCode = FONT_START + loadTex(Path.of("res/TexSections/Code_Block.simplatex")) + FONT_END;
    }
}
