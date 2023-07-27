package Sections;

import Model.LatexDocument;
import Model.Section;
import View.SimpleEditors.EditorBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class CustomSection extends Section {

    public CustomSection(String name, String header, String code) {

        String simplatex = "";
        try (InputStream in = getClass().getResourceAsStream(code);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            simplatex = reader.lines().collect(Collectors.joining("\n"));
            // Use resource
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String hsimplatex = "";
        if(header != null){
            try (InputStream in = getClass().getResourceAsStream(header);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                hsimplatex = reader.lines().collect(Collectors.joining("\n"));
                // Use resource
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        this.latexDocument = new LatexDocument(simplatex);
        this.header = (header!=null)?hsimplatex:"";
        this.editor = new EditorBase(this);
        this.name = name;
    }
}
