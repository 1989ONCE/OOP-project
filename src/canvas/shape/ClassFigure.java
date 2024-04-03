package canvas.shape;
import java.awt.*;

public class ClassFigure extends Figure {
    
    
    // Add other properties like ports here...

    public ClassFigure(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(this.figureColor);
        int levelHeight = height / 3;
        g.drawRect(x, y, width, levelHeight);
        g.drawRect(x, y + levelHeight, width, levelHeight);
        g.drawRect(x, y + 2 * levelHeight, width, levelHeight);
        topPort.draw(g);
        rightPort.draw(g);
        bottomPort.draw(g);
        leftPort.draw(g);
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

    @Override
    public void resizeBasedOn(Port port) {
        Figure selectedFigure = port.getFigure();
        // Adjust the width and height based on the position of the port
        if(port == this.topPort) {
            this.height = this.y + this.height - port.getY();
            this.y = port.getY();
        }
        else if(port == this.bottomPort){
            this.height = Math.abs(port.getY() - this.y);
        }
        else if(port == this.leftPort) {
            this.width = this.x + this.width - port.getX();
            this.x = port.getX();
        }
        else if(port == this.rightPort) {
            this.width = Math.abs(port.getX() - this.x);
        }
        selectedFigure.updatePorts(this.x, this.y, this.width, this.height);
        selectedFigure.setPortVisibility(true);
    }
}