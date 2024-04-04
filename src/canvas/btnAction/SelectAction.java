package canvas.btnAction;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import canvas.MyCanvas;
import canvas.shape.Figure;
import canvas.shape.GroupFigure;
import init.MyFrame;

public class SelectAction extends MouseAdapter implements ButtonAction {
    private Figure selectedFigure;
    private Point startPoint;
    private Point lastmousePosition;
    private Figure tempGroupFigure;

    @Override
    public void mousePressed(MouseEvent e) {
        /** UseCase C.1 Select/Unselect a single objects 
         * 
         * Alternative 1.a 使用者點選的座標，不在任何基本物件內
         * Alternative 2.a 若有其他物件處於被 select 的狀態，取消它們被 select 的狀態。
         */        
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        ArrayList<Figure> figures = canvas.getFigures();
        this.startPoint = e.getPoint();
        System.out.println(canvas.getSelectedFigure());
        // if startPoint is in the tempGroupFigure, select the tempGroupFigure
        if (canvas.getTempFigure() != null && canvas.getTempFigure().contains(startPoint.x, startPoint.y)) {
            System.out.println("select action first if: " + canvas.getSelectedFigure());
            canvas.clearAllSelected();
            this.selectedFigure = tempGroupFigure;
            canvas.setSelectedFigure(selectedFigure);
            selectedFigure.setPortVisibility(true);
            return;
        }

        // Iterate backwards to start from the top figure
        for (int i = figures.size() - 1; i >= 0; i--) {
            Figure figure = figures.get(i);
            if (figure.contains(e.getX(), e.getY())) {
                // The mouse click is within this figure, select it
                System.out.println("select action second if: " + canvas.getSelectedFigure());
                canvas.clearAllSelected();
                this.selectedFigure = figure;
                canvas.setSelectedFigure(selectedFigure);
                selectedFigure.setPortVisibility(true);
                return;
            }
        }
        System.out.println("select action third section: " + canvas.getSelectedFigure());

        canvas.clearAllSelected();
        selectedFigure = null;
        tempGroupFigure = null;
        canvas.setSelectedFigure(null);
        canvas.setTempFigure(tempGroupFigure);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        lastmousePosition = null;
        // if no figure is selected, create a temp group figure
        if(tempGroupFigure != null && selectedFigure == null) {
            MyCanvas canvas = MyFrame.getFrame().getCanvas();
            Point endPoint = e.getPoint();
            int width = endPoint.x - startPoint.x;
            int height = endPoint.y - startPoint.y;

            // Paint the GroupFigure only if there are more than 1 figure(including groupFigure) inside the GroupFigure
            if (((GroupFigure)tempGroupFigure).getInsideFigures().size() > 1) {
                tempGroupFigure.updatePorts(startPoint.x, startPoint.y, width, height);
                tempGroupFigure.setFigureName("Group");                
                tempGroupFigure.setPortVisibility(true);
                selectedFigure = tempGroupFigure;
                canvas.setSelectedFigure(tempGroupFigure);
                for (Figure figure : ((GroupFigure)tempGroupFigure).getInsideFigures()) {
                    figure.setPortVisibility(true);
                }
                return;
            }
            else {
                tempGroupFigure = null;
                canvas.setTempFigure(null);
            }
        }
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
        // if no figure is selected, create a temp group figure
        if(selectedFigure == null) { 
            MyCanvas canvas = MyFrame.getFrame().getCanvas();
            Point endPoint = e.getPoint();
            int width = endPoint.x - startPoint.x;
            int height = endPoint.y - startPoint.y;
            // Create a temporary group figure to show the process of creating a group figure
            tempGroupFigure = new GroupFigure(startPoint.x, startPoint.y, width, height);
            canvas.setTempFigure(tempGroupFigure);

            // Check if the figures are inside the tempGroupFigure
            ArrayList<Figure> allFigures = canvas.getFigures();
            for (Figure figure : allFigures) {
                if(figure.getParent() != null) continue; // Skip the figure that is already in a group
                else if (figure.inSide(figure, startPoint.x, startPoint.y, width, height)) {
                    ((GroupFigure)tempGroupFigure).addFigureToList(figure);
                }
            }
            return;
        }
        
        // if a figure is selected, move the figure
        if (lastmousePosition != null) {
            int dx = e.getX() - lastmousePosition.x;
            int dy = e.getY() - lastmousePosition.y;
            selectedFigure.move(dx, dy);
        }
        // update the last mouse position
        lastmousePosition = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Set the cursor to the default cursor when entering the canvas
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}
