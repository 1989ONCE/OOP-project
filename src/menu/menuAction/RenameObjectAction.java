package menu.menuAction;

import javax.swing.JOptionPane;

import canvas.shape.Figure;
import init.MyFrame;

/* UseCase F.1 Change Object Name 
 * Alternative 3.a 取消更改
 * 1. 使用者按下cancel鍵，然後小視窗消失。
*/
public class RenameObjectAction implements MenuAction{
    @Override
    public void execute() {
        Figure selectedFigure = MyFrame.getFrame().getCanvas().getSelectedFigure();
        
        // if no figure is selected, do nothing and return
        if(selectedFigure == null) return;
        
        String newName = JOptionPane.showInputDialog("Enter the new name of the object", selectedFigure.getFigureName());
        // if the user clicks cancel, newName will be null
        if(newName != null){ 
            selectedFigure.setFigureName(newName);
        }
    }

}
