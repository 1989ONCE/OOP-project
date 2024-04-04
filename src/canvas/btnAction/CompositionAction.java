package canvas.btnAction;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import canvas.MyCanvas;
import canvas.line.CompositionLine;
import canvas.line.Line;
import canvas.shape.GroupFigure;
import canvas.shape.Figure;
import canvas.shape.Port;
import init.MyFrame;

/* UseCase B.1 Creating a UML connection line
 * Alternative 1.a
 * 使用者 mouse pressed 的座標，不在任何 class 或 use case 物件，則從 mouse pressed -> mouse drag -> mouse released 都不會有任何作用。
 * Alternative 3.a 使用者 mouse released 的座標，不在任何 class 或 use case 物件， 則不建立任何 connection line 物件。
 */
public class CompositionAction extends MouseAdapter implements ButtonAction {
    private Line tempLine;
    private Figure startFigure = null;
    private Figure endFigure = null;
    private Port figureStartPort = null;
    private Port figureEndPort = null;

    @Override
    public void mousePressed(MouseEvent e) {
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        startFigure = null;
        endFigure = null;
        figureStartPort = null;
        figureEndPort = null;

        // Iterate backwards to start from the top figure
        for(int i = canvas.getFigures().size() - 1; i >= 0; i--) {
            Figure figure = canvas.getFigures().get(i);
            if(figure.contains(e.getX(), e.getY())) {
                startFigure = figure;
                canvas.clearAllSelected();
                startFigure.setPortVisibility(true);
                break;
            }
        }

        // if startFigure is a group figure, disable the create line action
        if(startFigure instanceof GroupFigure || startFigure == null){
            canvas.clearAllSelected();
            startFigure.setPortVisibility(false);
            startFigure = null;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        Point endPoint = e.getPoint();

        // Iterate backwards to start from the top figure
        for(int i = canvas.getFigures().size() - 1; i >= 0; i--) {
            Figure figure = canvas.getFigures().get(i);
            if(figure.contains(endPoint.x, endPoint.y)) {
                endFigure = figure;
                endFigure.setPortVisibility(true);
                break;
            }
        }
        
        // if endFigure is a group figure or startFigure and endFigure it's the same figure,
        // disable the create line action
        if(endFigure instanceof GroupFigure || startFigure == endFigure){
            tempLine = null;
            canvas.setTempLine(null);
            endFigure = null;
            return;
        }
        
        // tempLine != null : if the startFigure is not null(set in mouseDragged method)
        // endFigure != null : if the endFigure is not null(set in mouseReleased method)
        // figureStartPort != null : if the startFigure has available port
        if (tempLine != null && endFigure != null && figureStartPort != null) {
            figureEndPort = endFigure.getAvailableEndPort(endFigure);
            System.out.println("End port: " + figureEndPort);
            
            tempLine.updatePorts(figureStartPort.getX(), figureStartPort.getY(), figureEndPort.getX(), figureEndPort.getY());
            tempLine.setStartFigure(startFigure);
            tempLine.setEndFigure(endFigure);

            startFigure.fillPort(figureStartPort, startFigure);
            endFigure.fillPort(figureEndPort, endFigure);

            startFigure.addConnectedLine(tempLine);
            endFigure.addConnectedLine(tempLine);

            tempLine.setStartPort(figureStartPort);
            tempLine.setEndPort(figureEndPort);
            canvas.addLine(tempLine);
        }
        canvas.setTempLine(null);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MyCanvas canvas = MyFrame.getFrame().getCanvas();

        if(startFigure == null){
            return;
        }

        figureStartPort = startFigure.getAvailableStartPort(startFigure);
        System.out.println(figureStartPort);
        if(figureStartPort == null){
            return;
        }
        tempLine = new CompositionLine(startFigure.getX(), startFigure.getY());
        tempLine.updatePorts(figureStartPort.getX(), figureStartPort.getY(), e.getX(), e.getY());
        canvas.setTempLine(tempLine);
        canvas.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        canvas.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
    }
}
