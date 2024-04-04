package canvas.shape;
import java.awt.*;

public class UseCaseFigure extends Figure {
    
    // Constructor
    public UseCaseFigure(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    // polymorphism
    @Override
    public void draw(Graphics g) {
        g.setColor(this.figureColor);
        g.drawOval(x, y, width, height);

        FontMetrics fm = g.getFontMetrics();

        // Calculate the width of the string
        int stringWidth = fm.stringWidth(this.figureName);
        // Calculate the x-coordinate for the string
        int midX = x + (width - stringWidth) / 2;
        int midY = y + height / 2;

        g.drawString(this.figureName, midX, midY);
        topPort.draw(g);
        rightPort.draw(g);
        bottomPort.draw(g);
        leftPort.draw(g);
    }
}