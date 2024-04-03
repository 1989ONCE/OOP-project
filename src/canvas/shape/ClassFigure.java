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
    
    // @Override
    // public void resizeBasedOn(Port port) {
    //     Figure selectedFigure = port.getFigure();
    //     // Adjust the width and height based on the position of the port
    //     if(port == this.topPort) {
    //         this.height = this.y + this.height - port.getY();
    //         this.y = port.getY();
    //     }
    //     else if(port == this.bottomPort){
    //         this.height = Math.abs(port.getY() - this.y);
    //     }
    //     else if(port == this.leftPort) {
    //         this.width = this.x + this.width - port.getX();
    //         this.x = port.getX();
    //     }
    //     else if(port == this.rightPort) {
    //         this.width = Math.abs(port.getX() - this.x);
    //     }
    //     selectedFigure.updatePorts(this.x, this.y, this.width, this.height);
    //     selectedFigure.setPortVisibility(true);
    // }
}