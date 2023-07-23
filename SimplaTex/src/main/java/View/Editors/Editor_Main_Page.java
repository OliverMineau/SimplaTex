package View.Editors;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Editor_Main_Page extends SimpleEditorHelper {



    public Editor_Main_Page() {

        JTextField imagePath = createImagePanel("Logo Image Path");
        JTextField edLevel = createTextPanel("Level of Education :");
        JTextField fieldStdy = createTextPanel("Field of Study :");
        JTextField title = createTextPanel("Title :");
        ArrayList<JTextField> authors = createMultipleTexts("Authors :");
        JTextField date = createTextPanel("Date :");
    }



}
