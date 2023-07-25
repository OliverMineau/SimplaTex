package Model;

public class StringElement{

    String id = "";
    String className = "";
    String style = "";
    String text = "";

    public StringElement(String id, String className, String style, String text) {
        this.id = id;
        this.className = className;
        this.style = style;
        this.text = text;
    }

    public StringElement() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}