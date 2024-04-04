package menu.menuAction;

import java.awt.event.MouseAdapter;

import canvas.MyCanvas;
import canvas.shape.Figure;
import canvas.shape.GroupFigure;
import init.MyFrame;

/*
 * UseCase D.1 Group objects
 * 將處於被選取狀態的基本物件合併成一個composite基本物件
 */
public class GroupAction extends MouseAdapter implements MenuAction{
    @Override
    public void execute() {
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        Figure tempGroupFigure = MyFrame.getFrame().getCanvas().getTempFigure();    
        String actionName = canvas.getActionName();

        /* if there is a tempFigure, and the function button is on "Select"
         * this situation happens when user selects a group figure, and wants to group it
         * after pressing "Group" on the edit menu, the group figure will be added to the canvas
        */
        if(tempGroupFigure != null && actionName.equals("SelectAction")) {
            canvas.clearAllSelected();
            tempGroupFigure.setPortVisibility(true);
            canvas.setSelectedFigure(tempGroupFigure);
            canvas.getFigures().add(tempGroupFigure);
            canvas.setTempFigure(null);
            for (Figure figure : ((GroupFigure)tempGroupFigure).getInsideFigures()) {
                figure.setParent(tempGroupFigure);
            }
        }
        canvas.repaint();
    }    
}
