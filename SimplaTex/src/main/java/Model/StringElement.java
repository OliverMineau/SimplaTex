package Model;

import java.util.ArrayList;

public class StringElement{

    String id = "";
    String className = "";
    String style = "";
    String text = "";
    String type = "";
    String title = "";
    ArrayList<StringElement> children;

    public StringElement(String id, String className, String title, String style, String text, String type) {
        this.id = id;
        this.className = className;
        this.style = style;
        this.text = text;
        this.type = type;
        this.title = title;
        this.children = new ArrayList<>();
    }

    public StringElement(String text) {
        this.text = text;
        this.type = "PlainText";
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<StringElement> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<StringElement> children) {
        this.children = children;
    }

    public void addChild(StringElement child) {
        this.children.add(child);
    }

    public void removeChild() {
        if(children.size()==0) return;
        this.children.remove(children.size()-1);
    }

    public String toHTML(){
        String txt = text;
        txt = txt.replace("\n","<br>");
        txt = txt.replace(" ","&nbsp;");
        txt = txt.replace("\t","&nbsp;&nbsp;");
        return txt;
    }

    @Override
    public String toString() {

        String output = "";
        output += ("\nId : " + id);
        output += ("\nClass name : " + className);
        output += ("\nStyle : " + style);
        output += ("\nText : " + text);
        output += ("\nType : " + type);
        output += ("\nTitle : " + title);

        return output;
    }

}