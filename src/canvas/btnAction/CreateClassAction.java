package canvas.btnAction;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import canvas.MyCanvas;
import canvas.shape.ClassFigure;
import init.MyFrame;

// UseCase A.1 Creating a UML object
// < UseCase A.1 Alternatives 1.a > See also: MyCanvas.java
public class CreateClassAction extends MouseAdapter implements ButtonAction{
    private Point startPoint;
    private ClassFigure tempFigure;

    @Override // only implement mousePressed because mouseClicked is same as mousePressed
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        Point endPoint = e.getPoint();
        if (tempFigure != null) {
            // Create a new class figure after releasing the mouse
            tempFigure.updatePorts(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y - startPoint.y);
            canvas.addFigure(tempFigure);
            canvas.clearAllSelected();
            tempFigure.setSelected(tempFigure, true); // Show the ports
            canvas.setSelectedFigure(tempFigure);
            tempFigure = null;
            canvas.setTempFigure(null);
            canvas.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Implement the mouseEntered method here

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Implement the mouseExited method here
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        Point endPoint = e.getPoint();
        
        // showing the process of creating a class through tempFigure
        tempFigure = new ClassFigure(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y - startPoint.y);
        canvas.setTempFigure(tempFigure);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Set the cursor to the Crosshair cursor when entering the canvas
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        canvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }  
}
