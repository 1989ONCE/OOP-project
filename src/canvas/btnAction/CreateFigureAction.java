package canvas.btnAction;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import canvas.MyCanvas;
import canvas.shape.UseCaseFigure;
import canvas.shape.ClassFigure;
import canvas.shape.Figure;
import init.MyFrame;


/* UseCase A.1 Creating a UML object
 * Alternatives 1.a 使用者按其他按鈕，則切換到其他按鈕的 mode
 * See also: MyCanvas.java setAction()
 */
public class CreateFigureAction extends MouseAdapter implements ButtonAction {
    private Point startPoint;
    private Figure tempFigure;
    private String figureType;
    private Map<String, BiFunction<Point, Point, Figure>> figureCreators = new HashMap<>();
    {
        figureCreators.put("UseCase", (startPoint, endPoint) -> new UseCaseFigure(startPoint.x, startPoint.y, Math.abs(endPoint.x - startPoint.x), Math.abs(endPoint.y - startPoint.y), "UseCase"));
        figureCreators.put("Class", (startPoint, endPoint) -> new ClassFigure(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y - startPoint.y, "Class"));
    }

    // Constructor
    public CreateFigureAction(String figureType) {
        this.figureType = figureType;
    }

    @Override
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
            canvas.clearAllSelected(); // Clear all selected figures before creating a new one
            tempFigure.setPortVisibility(true); // Show the ports
            canvas.setSelectedFigure(tempFigure);
            tempFigure = null;
            canvas.setTempFigure(null);
            canvas.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        Point endPoint = e.getPoint();
        
        // Get the figure from the creator map
        BiFunction<Point, Point, Figure> createFigureFunction = figureCreators.get(figureType);

        // showing the process of creating a use case through tempFigure
        if (endPoint.x < startPoint.x) {
            tempFigure = createFigureFunction.apply(endPoint, startPoint);
        }
        else{
            tempFigure = createFigureFunction.apply(startPoint, endPoint);
        }
        
        canvas.setTempFigure(tempFigure);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Set the cursor to the Crosshair cursor when entering the canvas
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        canvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }  
}