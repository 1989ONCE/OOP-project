package menu.menuAction;

import javax.swing.JOptionPane;

import canvas.MyCanvas;
import canvas.shape.Figure;
import canvas.shape.GroupFigure;
import init.MyFrame;

/* UseCase F.1 Change Object Name 
 * Alternative 3.a 取消更改
 * 1. 使用者按下cancel鍵，然後小視窗消失。
*/
public class RenameObjectAction implements MenuAction{
    @Override
    public void execute() {
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        Figure selectedFigure = MyFrame.getFrame().getCanvas().getSelectedFigure();
        Figure tempGroupFigure = MyFrame.getFrame().getCanvas().getTempFigure();
        // if no figure is selected or there is a tempGroupFigure, do nothing and return
        if(selectedFigure == null || tempGroupFigure != null){
            canvas.clearAllSelected();
            ((GroupFigure)tempGroupFigure).getInsideFigures().clear();
            canvas.setTempFigure(null);
            canvas.repaint();
            return;
        }
        
        String newName = JOptionPane.showInputDialog("Enter the new name of the object", selectedFigure.getFigureName());
        // if the user clicks cancel, newName will be null
        if(newName != null){ 
            selectedFigure.setFigureName(newName);
        }
        MyFrame.getFrame().getCanvas().repaint();
    }

}
