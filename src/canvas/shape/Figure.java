package canvas.shape;
import java.awt.Color;
import java.awt.Graphics;

import canvas.MyCanvas;
import init.MyFrame;

public abstract class Figure {
    protected int x, y, width, height;
    protected Color figureColor = Color.LIGHT_GRAY;
    protected Port topPort;
    protected Port bottomPort;
    protected Port leftPort;
    protected Port rightPort;
    protected Port draggedPort;
    protected int depth;
    private Figure parent;

    public Figure getParent() {
        return parent;
    }
    
    public void setParent(Figure parent) {
        this.parent = parent;
    }
    public Figure(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.topPort = new Port(0, 0);
        this.rightPort = new Port(0, 0);
        this.bottomPort = new Port(0, 0);
        this.leftPort = new Port(0, 0);
    }

    public void draw(Graphics g){}

    public Port getTopPort() { return topPort; }
    public Port getRightPort() { return rightPort; }
    public Port getBottomPort() { return bottomPort; }
    public Port getLeftPort() { return leftPort; }
    public void updatePorts(int x, int y, int width, int height) {
        topPort = new Port(x + width / 2, y);
        rightPort = new Port(x + width, y + height / 2);
        bottomPort = new Port(x + width / 2, y + height);
        leftPort = new Port(x, y + height / 2);
    }

    public Port getPortAt(int x, int y) {
        
        if (x == topPort.getX() && y == topPort.getY()) {
            return topPort;
        } else if (x == rightPort.getX() && y == rightPort.getY()) {
            return rightPort;
        } else if (x == bottomPort.getX() && y == bottomPort.getY()) {
            return bottomPort;
        } else if (x == leftPort.getX() && y == leftPort.getY()){
            return leftPort;
        }
        return null;
    }
    public void setPortVisibility(boolean visible) {
        topPort.setVisible(visible);
        rightPort.setVisible(visible);
        bottomPort.setVisible(visible);
        leftPort.setVisible(visible);
    }

    public void setSelected(Figure figure, boolean selected) {
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        figure.setPortVisibility(selected);
        canvas.setSelectedFigure(figure);
        canvas.repaint();
    }

    public Port getDraggedPort() {
        return draggedPort;
    }

    public void setDraggedPort(Port draggedPort) {
        this.draggedPort = draggedPort;
    }

    public abstract void resizeBasedOn(Port port);

    public boolean contains(int pointX, int pointY) {
        return pointX >= x && pointX <= x + width && pointY >= y && pointY <= y + height;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int i) {
        depth = i;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        // moving won't change width and height
        updatePorts(x, y, width, height);
        setPortVisibility(true);
    }

    public boolean inSide(Figure figure, int x, int y, int width, int height) {
        return figure.x >= x && figure.y >= y && figure.x + figure.width <= x + width && figure.y + figure.height <= y + height;
    }
}


