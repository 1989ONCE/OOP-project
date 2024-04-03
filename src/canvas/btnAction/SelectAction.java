package canvas.btnAction;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import canvas.MyCanvas;
import canvas.shape.Figure;
import init.MyFrame;

public class SelectAction extends MouseAdapter implements ButtonAction {
    private Figure selectedFigure;
    private Point lastMousePosition;
    private Point startPoint;

    @Override
    public void mousePressed(MouseEvent e) {
        /** UseCase C.1 Select/Unselect a single objects 
         * 
         * Alternative 1.a 使用者點選的座標，不在任何基本物件內
         * Alternative 2.a 若有其他物件處於被 select 的狀態，取消它們被 select 的狀態。
         */        
        this.startPoint = e.getPoint();
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        ArrayList<Figure> figures = canvas.getFigures();
        // Iterate backwards to start from the top figure
        for (int i = figures.size() - 1; i >= 0; i--) {
            Figure figure = figures.get(i);
            if (figure.contains(e.getX(), e.getY())) {
                // The mouse click is within this figure, select it
                canvas.clearAllSelected();
                this.selectedFigure = figure;
                selectedFigure.setPortVisibility(true);
                return;
            }
        }
        canvas.clearAllSelected();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // release the mouse, lastMousePosition should be set to null to start a new drag
        lastMousePosition = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Implement the mouseExited method here
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (lastMousePosition != null) {
            int dx = e.getX() - lastMousePosition.x;
            int dy = e.getY() - lastMousePosition.y;
            selectedFigure.move(dx, dy);
        }
        // update the last mouse position
        lastMousePosition = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Set the cursor to the default cursor when entering the canvas
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}
