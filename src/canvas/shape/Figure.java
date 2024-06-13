package canvas.shape;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import canvas.line.Line;

public abstract class Figure {
    // x, y: top-left corner of the figure
    // width, height: width and height of the figure
    protected final Color figureColor = Color.LIGHT_GRAY;

    protected int x, y, width, height, depth;
    protected Port topPort, bottomPort, leftPort, rightPort, draggedPort;
    protected String figureName;
    private Figure parent;
    private ArrayList<Line> connectedLines = new ArrayList<>();

    // Constructor
    public Figure(int x, int y, int width, int height, String figureName) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.topPort = new Port(0, 0);
        this.rightPort = new Port(0, 0);
        this.leftPort = new Port(0, 0);
        this.bottomPort = new Port(0, 0);
        this.figureName = figureName;
        updatePorts(x, y, width, height);
    }

    public abstract void draw(Graphics g);
    
    public boolean contains(int pointX, int pointY) {
        return pointX >= x && pointX <= x + width && pointY >= y && pointY <= y + height;
    }

    public boolean inSide(Figure figure, int x, int y, int width, int height) {
        return figure.x >= x && figure.y >= y && figure.x + figure.width <= x + width && figure.y + figure.height <= y + height;
    }

    public void fillPort(Port figureStartPort, Figure figure) {
        figureStartPort.setConnectedFigure(figure);
    }

    /* UseCase E.1 Move objects */
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        // moving won't change width and height
        updatePorts(x, y, width, height);
        
        setPortVisibility(true);

        // Update the connected lines
        for (Line line : connectedLines) {

            if(line.getStartFigure() == this) {
                line.updateConnectedStartPort(dx, dy);
            }
            else if(line.getEndFigure() == this) {
                line.updateConnectedEndPort(dx, dy);
            }
        }
    }

    // Getters
    public Figure getParent() {
        return parent;
    }

    public String getFigureName() {
        return figureName;
    }

    public ArrayList<Line> getConnectedLines() {
        return connectedLines;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDepth() {
        return depth;
    }

    public Port getAvailableStartPort(Figure figure) {
        if (figure == null) {
            return null;
        }
        if (!figure.getRightPort().isConnected()) {
            return figure.getRightPort();
        } else if (!figure.getBottomPort().isConnected()) {
            return figure.getBottomPort();
        } else if (!figure.getTopPort().isConnected()) {
            return figure.getTopPort();
        } else if (!figure.getLeftPort().isConnected()) {
            return figure.getLeftPort();
        }
        return null;
    }

    public Port getAvailableEndPort(Figure figure) {
        if (figure == null) {
            return null;
        }
        if (!figure.getLeftPort().isConnected()) {
            return figure.getLeftPort();
        } else if (!figure.getBottomPort().isConnected()) {
            return figure.getBottomPort();
        } else if (!figure.getTopPort().isConnected()) {
            return figure.getTopPort();
        } else if (!figure.getRightPort().isConnected()) {
            return figure.getRightPort();
        }
        return null;
    }

    public Port getTopPort() { return topPort; }
    public Port getRightPort() { return rightPort; }
    public Port getBottomPort() { return bottomPort; }
    public Port getLeftPort() { return leftPort; }

    // Setters
    public void setParent(Figure parent) {
        this.parent = parent;
    }

    public void setFigureName(String figureName) {
        this.figureName = figureName;
    }

    public void addConnectedLine(Line line) {
        connectedLines.add(line);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDepth(int i) {
        depth = i;
    }

    public void updatePorts(int x, int y, int width, int height) {
        topPort.setX(x + width / 2);
        topPort.setY(y);
        rightPort.setX(x + width);
        rightPort.setY(y + height / 2);
        bottomPort.setX(x + width / 2);
        bottomPort.setY(y + height);
        leftPort.setX(x);
        leftPort.setY(y + height / 2);
    }

    public void setPortVisibility(boolean visible) {
        topPort.setVisible(visible);
        rightPort.setVisible(visible);
        bottomPort.setVisible(visible);
        leftPort.setVisible(visible);
    }
}


