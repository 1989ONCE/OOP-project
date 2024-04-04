package menu.menuAction;

import canvas.MyCanvas;
import canvas.shape.Figure;
import canvas.shape.GroupFigure;
import init.MyFrame;

/* UseCase D.2 UnGroup objects
 * 當唯一 1 個 composite 物件處於被 select 的狀態時
 */
public class UnGroupAction implements MenuAction{
    @Override
    public void execute() {
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        Figure selectedFigure = canvas.getSelectedFigure();
        String actionName = canvas.getActionName();
        Figure tempGroupFigure = MyFrame.getFrame().getCanvas().getTempFigure();

        /* if there is a tempGroupFigure, clear all selected figures and remove the tempGroupFigure
         * this situation happens when user selects group of figures, but not yet grouped them 
         * and then click ungroup button
        */
        if(tempGroupFigure != null) {
            canvas.clearAllSelected();
            ((GroupFigure)tempGroupFigure).getInsideFigures().clear();
            canvas.setTempFigure(null);
            canvas.repaint();
            return;
        }

        
        /* if there is a selectedFigure, and selectedFigure is a group figure, and the function button is on "Select"
         * this situation happens when user selects a group figure, and wants to ungroup it
        */
        if(selectedFigure != null && selectedFigure instanceof GroupFigure && actionName.equals("SelectAction")) {
            canvas.clearAllSelected();
            canvas.getFigures().remove(selectedFigure);
            canvas.setSelectedFigure(null);
            for (Figure figure : ((GroupFigure)selectedFigure).getInsideFigures()) {
                figure.setParent(null);
            }
            ((GroupFigure)selectedFigure).getInsideFigures().clear();
            canvas.repaint();
            return;
        }
    }
    
}
