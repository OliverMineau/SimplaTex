package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedCornerPanel extends JPanel {

    private int cornerRadius;

    public RoundedCornerPanel(LayoutManager layout, int cornerRadius) {
        super(layout);
        this.cornerRadius = cornerRadius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        // Set anti-aliasing for smoother rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a rounded rectangle shape
        Shape shape = new RoundRectangle2D.Double(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);

        // Clip the graphics to the shape to avoid drawing outside the rounded corners
        g2d.clip(shape);

        // Call the super.paintComponent to draw the components as usual
        super.paintComponent(g2d);

        // Draw the rounded border
        g2d.setColor(getForeground());
        g2d.draw(shape);

        g2d.dispose();
    }

}
