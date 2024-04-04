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
        if(tempGroupFigure != null) {
            canvas.clearAllSelected();
            ((GroupFigure)tempGroupFigure).getInsideFigures().clear();
            canvas.setTempFigure(null);
            canvas.repaint();
            return;
        }

        if(selectedFigure != null && selectedFigure instanceof GroupFigure && actionName.equals("SelectAction")) {
            canvas.clearAllSelected();
            boolean ans = canvas.getFigures().remove(selectedFigure);
            System.out.println("Remove group figure: " + ans);  
            System.out.println("size of figures: " + canvas.getFigures().size());
            canvas.setSelectedFigure(null);
            for (Figure figure : ((GroupFigure)selectedFigure).getInsideFigures()) {
                System.out.println("Remove inside figure: " + figure);
                figure.setParent(null);
            }
            ((GroupFigure)selectedFigure).getInsideFigures().clear();
            canvas.repaint();
            return;
        }

        
    }
    
}
