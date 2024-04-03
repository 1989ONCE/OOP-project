package canvas.shape;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import canvas.MyCanvas;
import init.MyFrame;

public class Port implements MouseMotionListener {
    private int x, y;
    private int size = 10; // Default size
    private boolean visible = false; // Default visibility

    public Port(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void draw(Graphics g) {
        if (visible) {
            g.fillRect(x - size / 2, y - size / 2, size, size);
        }
    }

    public void setY(int newY) {
        y = newY;
    }

    public void setX(int newX) {
        x = newX;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        canvas.getSelectedFigure().getDraggedPort().setX(e.getX());
        canvas.getSelectedFigure().getDraggedPort().setY(e.getY());
        canvas.repaint();
        // this.setX(e.getX());
        // this.setY(e.getY());
        // Point endPoint = e.getPoint();
        // ClassFigure tempFigure = new ClassFigure(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y - startPoint.y);
        // canvas.setTempClassFigure(tempFigure);
        // canvas.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

    public Figure getFigure() {
        Figure figure = MyFrame.getFrame().getCanvas().getSelectedFigure();
        return figure;
    }

}
