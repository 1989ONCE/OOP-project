package canvas.shape;
import java.awt.*;

public class ClassFigure extends Figure {

    // Constructor
    public ClassFigure(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    // polymorphism
    @Override
    public void draw(Graphics g) {
        g.setColor(this.figureColor);
        int levelHeight = height / 3;
        g.drawRect(x, y, width, levelHeight);
        g.drawRect(x, y + levelHeight, width, levelHeight);
        g.drawRect(x, y + 2 * levelHeight, width, levelHeight);

        FontMetrics fm = g.getFontMetrics();
        // Calculate the width of the string
        int stringWidth = fm.stringWidth(this.figureName);
        // Calculate the x-coordinate for the string
        int midX = x + (width - stringWidth) / 2;
        // Calculate the y-coordinate for the string
        int midY = y + levelHeight / 2;

        g.drawString(this.figureName, midX, midY);

        topPort.draw(g);
        rightPort.draw(g);
        bottomPort.draw(g);
        leftPort.draw(g);
    }
}