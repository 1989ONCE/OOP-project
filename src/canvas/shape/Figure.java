package canvas.shape;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import canvas.MyCanvas;
import canvas.line.Line;
import init.MyFrame;

public abstract class Figure {
    // x, y: top-left corner of the figure
    // width, height: width and height of the figure
    protected int x, y, width, height;
    protected Color figureColor = Color.LIGHT_GRAY;
    protected Port topPort;
    protected Port bottomPort;
    protected Port leftPort;
    protected Port rightPort;
    protected Port draggedPort;
    protected int depth;
    protected String figureName;
    private Figure parent;
    private ArrayList<Line> connectedLines = new ArrayList<>();

    // Constructor
    public Figure(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.topPort = new Port(0, 0);
        this.rightPort = new Port(0, 0);
        this.leftPort = new Port(0, 0);
        this.bottomPort = new Port(0, 0);
        updatePorts(x, y, width, height);
    }

    public void draw(Graphics g){}

    public Port getTopPort() { return topPort; }
    public Port getRightPort() { return rightPort; }
    public Port getBottomPort() { return bottomPort; }
    public Port getLeftPort() { return leftPort; }

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

    public Port getDraggedPort() {
        return draggedPort;
    }

    public void setDraggedPort(Port draggedPort) {
        this.draggedPort = draggedPort;
    }

    // public abstract void resizeBasedOn(Port port);

    public boolean contains(int pointX, int pointY) {
        return pointX >= x && pointX <= x + width && pointY >= y && pointY <= y + height;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int i) {
        depth = i;
    }

    /* UseCase E.1 Move objects */
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        // moving won't change width and height
        updatePorts(x, y, width, height);
        
        setPortVisibility(true);

        System.out.println("Connected lines: " + connectedLines.size());
        // Update the connected lines
        for (Line line : connectedLines) {

            if(line.getStartFigure() == this) {
                line.updateConnectedStartPort(dx, dy);
            }
            else if(line.getEndFigure() == this) {
                System.out.println("Line: " + line);
                line.updateConnectedEndPort(dx, dy);
            }
        }
    }

    public boolean inSide(Figure figure, int x, int y, int width, int height) {
        return figure.x >= x && figure.y >= y && figure.x + figure.width <= x + width && figure.y + figure.height <= y + height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Port getAvailableStartPort(Figure figure) {
        if (figure == null) {
            System.out.println("Figure is null");
            return null;
        }
        if (!figure.getRightPort().isConnected()) {
            System.out.println("Right port is available");
            return figure.getRightPort();
        } else if (!figure.getBottomPort().isConnected()) {
            System.out.println("Bottom port is available");
            return figure.getBottomPort();
        } else if (!figure.getTopPort().isConnected()) {
            System.out.println("Top port is available");
            return figure.getTopPort();
        } else if (!figure.getLeftPort().isConnected()) {
            System.out.println("Left port is available");
            return figure.getLeftPort();
        }
        System.out.println("No port is available");
        return null;
    }

    public Port getAvailableEndPort(Figure figure) {
        if (figure == null) {
            System.out.println("Figure is null");
            return null;
        }
        if (!figure.getLeftPort().isConnected()) {
            System.out.println("Right port is available");
            return figure.getLeftPort();
        } else if (!figure.getBottomPort().isConnected()) {
            System.out.println("Bottom port is available");
            return figure.getBottomPort();
        } else if (!figure.getTopPort().isConnected()) {
            System.out.println("Top port is available");
            return figure.getTopPort();
        } else if (!figure.getRightPort().isConnected()) {
            System.out.println("Left port is available");
            return figure.getRightPort();
        }
        System.out.println("No port is available");
        return null;
    }

    public void fillPort(Port figureStartPort, Figure figure) {
        figureStartPort.setConnectedFigure(figure);
        System.out.println(figureStartPort + " is connected to " + figure.getFigureName());
    }

    // Add getters and setters for x, y, width, height here...
    // Add methods for handling ports here...

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


    public void addConnectedLine(Line line) {
        connectedLines.add(line);
    }

    public ArrayList<Line> getConnectedLines() {
        return connectedLines;
    }

    public void setFigureName(String figureName) {
        this.figureName = figureName;
    }

    public String getFigureName() {
        return figureName;
    }

    public Figure getParent() {
        return parent;
    }
    
    public void setParent(Figure parent) {
        this.parent = parent;
    }

    public boolean intersectsLine(int x2, int y2, int midX, int midY) {
        return x2 >= x && x2 <= x + width && y2 >= y && y2 <= y + height;
    }
}


