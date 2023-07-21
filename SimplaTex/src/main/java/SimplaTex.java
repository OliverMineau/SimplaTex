import Model.LatexManager;
import View.MainWindow;
import javax.swing.*;
import java.nio.file.Path;


public class SimplaTex implements Runnable{

    static Path docPath;

    public void run() {
        JFrame frame = new JFrame("SimplaTex");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setVisible(true);
        //Enable Mouse and Keyboard interaction with app
        frame.setFocusable(true);
        //Position window to center
        frame.setLocationRelativeTo(null);

        //TODO Name of file is generic
        MainWindow mainWindow = new MainWindow(frame, docPath.resolve("exampleTex.pdf"));


    }

    public static void main(String[] args) {

        //Get document path
        String home = System.getProperty("user.home");
        String filePath = home+"/Documents/SimplaTex/";
        docPath = Path.of(filePath);

        LatexManager lm = new LatexManager(docPath);

        //Launch Swing Window
        SwingUtilities.invokeLater(new SimplaTex());

    }

}