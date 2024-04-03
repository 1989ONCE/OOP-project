package canvas.btnAction;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import canvas.MyCanvas;
import canvas.shape.CompositeFigure;
import canvas.shape.Figure;
import init.MyFrame;

public class CreateGroupAction extends MouseAdapter implements ButtonAction{
    private Point startPoint;
    private Figure tempGroupFigure;

    @Override
    public void mousePressed(MouseEvent e) {
        this.startPoint = e.getPoint();
        tempGroupFigure = new CompositeFigure(0, 0, 0, 0);
    }

    @Override
    public void mouseReleased(MouseEvent e){
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        Point endPoint = e.getPoint();
        int width = endPoint.x - startPoint.x;
        int height = endPoint.y - startPoint.y;

        // Paint the GroupFigure only if there are more than 1 basicfigure(composite figure not allowed) inside the GroupFigure
        if (((CompositeFigure)tempGroupFigure).getFigures().size() > 1) {
            tempGroupFigure.updatePorts(startPoint.x, startPoint.y, width, height);
            canvas.addFigure(tempGroupFigure);
            canvas.clearAllSelected();
            tempGroupFigure.setSelected(tempGroupFigure, true);
            canvas.setSelectedFigure(tempGroupFigure);
            for (Figure figure : ((CompositeFigure)tempGroupFigure).getFigures()) {
                figure.setParent(tempGroupFigure);
                figure.setPortVisibility(true);
            }
            tempGroupFigure = null;
            canvas.setTempFigure(null);
            canvas.repaint();
        }
        else {
            canvas.clearAllSelected();
            canvas.setTempFigure(null);
            canvas.repaint();
        }
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        Point endPoint = e.getPoint();
        int width = endPoint.x - startPoint.x;
        int height = endPoint.y - startPoint.y;
        // Create a temporary group figure to show the process of creating a group figure
        tempGroupFigure = new CompositeFigure(startPoint.x, startPoint.y, width, height);
        canvas.setTempFigure(tempGroupFigure);

        // Check if the figures are inside the tempGroupFigure
        ArrayList<Figure> allFigures = canvas.getFigures();
        for (Figure figure : allFigures) {
            if(figure.getParent() != null) continue; // Skip the figure that is already in a group
            else if (figure.inSide(figure, startPoint.x, startPoint.y, width, height)) {
                ((CompositeFigure)tempGroupFigure).addFigureToList(figure);
            }
            
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Set the cursor to the Crosshair cursor when entering the canvas
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        canvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }                                   
}