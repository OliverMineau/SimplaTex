package Model;

public class Section {

    private String name;

    public Section(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}