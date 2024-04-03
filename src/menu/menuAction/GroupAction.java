package menu.menuAction;

import java.awt.event.MouseAdapter;

import canvas.MyCanvas;
import canvas.shape.Figure;
import canvas.shape.GroupFigure;
import init.MyFrame;

public class GroupAction extends MouseAdapter implements MenuAction{
    @Override
    public void execute() {
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        Figure tempGroupFigure = MyFrame.getFrame().getCanvas().getTempFigure();    
        if(tempGroupFigure != null) {
            canvas.clearAllSelected();
            tempGroupFigure.setPortVisibility(true);
            canvas.getFigures().add(tempGroupFigure);
            canvas.setTempFigure(null);
            for (Figure figure : ((GroupFigure)tempGroupFigure).getInsideFigures()) {
                figure.setParent(tempGroupFigure);
            }
        }
        canvas.repaint();
    }    
}
