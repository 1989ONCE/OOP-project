package canvas.shape;

import java.awt.Graphics;

public class Port {
    private final int size = 10; // Default size
    
    private int x, y;
    private Figure connectedFigure;
    private boolean visible = false; // Default visibility

    // Constructor
    public Port(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        if (visible) {
            // top-corner of the port
            int topCornerX = x - size / 2;
            int topCornerY = y - size / 2;
            // x, y is the exact center of the port, minus half the size can draw the port 
            // fillRect(topCornerX, topCornerY, width, height)
            g.fillRect(topCornerX, topCornerY, this.size, this.size);
        }
    }

    // Getters and Setters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public boolean isConnected() {
        // if port connected to a figure, return true
        return connectedFigure != null;
    }

    // Setters
    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setConnectedFigure(Figure figure) {
        connectedFigure = figure;
    }

}
