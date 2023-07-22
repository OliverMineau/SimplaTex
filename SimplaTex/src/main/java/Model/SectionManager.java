package Model;

import java.util.ArrayList;

public class SectionManager {

    private ArrayList<Section> sections;

    public SectionManager() {
        LoadSections();
    }

    private void LoadSections(){
        sections = new ArrayList<>();
        sections.add(new Section("Main Page"));
        sections.add(new Section("Title"));
        sections.add(new Section("Description"));
        sections.add(new Section("Short Code"));
        sections.add(new Section("Code Block"));
        sections.add(new Section("Image"));
        sections.add(new Section("Credits"));
        sections.add(new Section("Installation"));
        sections.add(new Section("Example"));
        /*sections.add(new Section("Main Page"));
        sections.add(new Section("Title"));
        sections.add(new Section("Description"));
        sections.add(new Section("Short Code"));
        sections.add(new Section("Code Block"));
        sections.add(new Section("Image"));
        sections.add(new Section("Credits"));
        sections.add(new Section("Installation"));
        sections.add(new Section("Example"));
        sections.add(new Section("Main Page"));
        sections.add(new Section("Title"));
        sections.add(new Section("Description"));
        sections.add(new Section("Short Code"));
        sections.add(new Section("Code Block"));
        sections.add(new Section("Image"));
        sections.add(new Section("Credits"));
        sections.add(new Section("Installation"));
        sections.add(new Section("Example"));
        sections.add(new Section("Main Page"));
        sections.add(new Section("Title"));
        sections.add(new Section("Description"));
        sections.add(new Section("Short Code"));
        sections.add(new Section("Code Block"));
        sections.add(new Section("Image"));
        sections.add(new Section("Credits"));
        sections.add(new Section("Installation"));
        sections.add(new Section("Example"));*/

    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void SectionsOrderChanged(int indexA, int indexB){
        sections.add(indexB, sections.remove(indexA));
        System.out.println("New sections order : " + sections);
    }

}
