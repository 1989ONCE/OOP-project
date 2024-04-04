package canvas.line;

import java.awt.Color;
import java.awt.Graphics;

import canvas.shape.Figure;
import canvas.shape.Port;

public abstract class Line {
    protected int startX, startY, depth;
    protected Port startPort, endPort, draggedPort;
    private Figure startFigure, endFigure;

    protected final Color lineColor = Color.GRAY;

    // Constructor
    public Line(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public abstract void draw(Graphics g);

    // Getters
    public Figure getStartFigure() {
        return startFigure;
    }

    public Figure getEndFigure() {
        return endFigure;
    }

    public Port getDraggedPort() {
        return draggedPort;
    }

    // Setters
    public void updatePorts(int x1, int y1, int x2, int y2) {
        startPort = new Port(x1, y1);
        endPort = new Port(x2, y2);
    }

    public void setPortVisibility(boolean visible) {
        endPort.setVisible(visible);
        startPort.setVisible(visible);
    }

    public void setDraggedPort(Port draggedPort) {
        this.draggedPort = draggedPort;
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


