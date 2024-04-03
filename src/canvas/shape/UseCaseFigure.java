package canvas.shape;
import java.awt.*;

public class UseCaseFigure extends Figure {
    

    public UseCaseFigure(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(this.figureColor);
        g.drawOval(x, y, width, height);
        topPort.draw(g);
        rightPort.draw(g);
        bottomPort.draw(g);
        leftPort.draw(g);
        // Draw ports here...
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    // Add getters and setters for x, y, width, height here...
    // Add methods for handling ports here...

    public void setPortSize(int size) {
        topPort.setSize(size);
        rightPort.setSize(size);
        bottomPort.setSize(size);
        leftPort.setSize(size);
    }

    public void setPortVisibility(boolean visible) {
        topPort.setVisible(visible);
        rightPort.setVisible(visible);
        bottomPort.setVisible(visible);
        leftPort.setVisible(visible);
    }

    @Override
    public void resizeBasedOn(Port port) {
        throw new UnsupportedOperationException("Unimplemented method 'resizeBasedOn'");
    }
}