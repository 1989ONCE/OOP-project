package canvas.btnAction;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import canvas.MyCanvas;
import canvas.line.AssociateLine;
import canvas.line.CompositionLine;
import canvas.line.GeneralizationLine;
import canvas.line.DependencyLine;
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
public class CreateLineAction extends MouseAdapter implements ButtonAction {
    private String lineType;
    private Line tempLine;
    private Figure startFigure = null;
    private Figure endFigure = null;
    private Port figureStartPort = null;
    private Port figureEndPort = null;
    private Map<String, BiFunction<Integer, Integer, Line>> lineCreators = new HashMap<>();
    {
        lineCreators.put("Association", (startPointX, startPointY) -> new AssociateLine(startPointX, startPointY));
        lineCreators.put("Generalization", (startPointX, startPointY) -> new GeneralizationLine(startPointX, startPointY));
        lineCreators.put("Composition", (startPointX, startPointY) -> new CompositionLine(startPointX, startPointY));
        lineCreators.put("Dependency", (startPointX, startPointY) -> new DependencyLine(startPointX, startPointY));
    }

    // Constructor
    public CreateLineAction(String lineType) {
        this.lineType = lineType;
    }


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
            if(figureEndPort == null){
                canvas.setTempLine(null);
                return;
            }
            
            tempLine.updatePorts(figureStartPort.getX(), figureStartPort.getY(), figureEndPort.getX(), figureEndPort.getY());
            tempLine.setStartFigure(startFigure);
            tempLine.setEndFigure(endFigure);

            startFigure.fillPort(figureStartPort, startFigure);
            endFigure.fillPort(figureEndPort, endFigure);

            startFigure.addConnectedLine(tempLine);
            endFigure.addConnectedLine(tempLine);
            endFigure.setPortVisibility(true);

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
        if(figureStartPort == null){
            return;
        }

        BiFunction<Integer, Integer, Line> createLineFunction = lineCreators.get(lineType);
        tempLine = createLineFunction.apply(startFigure.getX(), startFigure.getY());
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
