package Sections;

import Model.LatexDocument;
import Model.Section;
import View.SimpleEditor;
import View.SimpleEditors.EditorBase;
import View.SimpleEditors.Editor_Code_Block;
import View.SimpleEditors.Editor_Title;
import View.SimpleEditors.SimpleEditorHelper;

import java.nio.file.Path;

public class CustomSection extends Section {

    public CustomSection(String name, String header, String code) {
        this.latexDocument = new LatexDocument(loadPlainTex(Path.of(code)));
        this.header = (header!=null)?loadPlainTex(Path.of(header)):"";
        this.editor = new EditorBase(this);
        this.name = name;
    }
}
