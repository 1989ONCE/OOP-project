package canvas.line;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import canvas.shape.Figure;
import canvas.shape.Port;

public abstract class Line {
    protected int startX, startY;
    protected Color lineColor = Color.GRAY;
    protected Port startPort;
    protected Port endPort;
    protected Port draggedPort;
    protected int depth;
    protected Point turningPoint1;
    protected Point turningPoint2;
    private Figure startFigure;
    private Figure endFigure;

    // Constructor
    public Line(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public void draw(Graphics g){}

    public void updatePorts(int x1, int y1, int x2, int y2) {
        startPort = new Port(x1, y1);
        endPort = new Port(x2, y2);
    }

    public void setPortVisibility(boolean visible) {
        endPort.setVisible(visible);
        startPort.setVisible(visible);
    }

    public Port getDraggedPort() {
        return draggedPort;
    }

    public void setDraggedPort(Port draggedPort) {
        this.draggedPort = draggedPort;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int i) {
        depth = i;
    }

    public boolean inSide(Figure figure, int x, int y, int width, int height) {
        return figure.getX() >= x && figure.getY() >= y && figure.getY() + figure.getWidth() <= x + width && figure.getY() + figure.getHeight() <= y + height;
    }

    public void updateTurningPoint() {
        int midY1 = (startPort.getY() + endPort.getY()) / 2;
        int midY2 = endPort.getY() - 10;

        // Keep the x coordinates the same as the start and end points
        int midX1 = startPort.getX();
        int midX2 = endPort.getX();

        // Update the turning points
        turningPoint1 = new Point(midX1, midY1);
        turningPoint2 = new Point(midX2, midY2);
    }

    public Figure getStartFigure() {
        return startFigure;
    }

    public Figure getEndFigure() {
        return endFigure;
    }

    public void setStartFigure(Figure figure) {
        this.startFigure = figure;
    }

    public void setEndFigure(Figure figure) {
        this.endFigure = figure;
    }

    public void setStartPort(Port port) {
        startPort = port;
    }

    public void setEndPort(Port port) {
        endPort = port;
    }

    public void updateConnectedStartPort(int dx, int dy) {
        startPort.setX(startPort.getX() + dx);
        startPort.setY(startPort.getY() + dy);
    }

    public void updateConnectedEndPort(int dx, int dy) {
        endPort.setX(endPort.getX() + dx);
        endPort.setY(endPort.getY() + dy);
    }
}


