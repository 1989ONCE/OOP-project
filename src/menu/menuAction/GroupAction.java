package menu.menuAction;

import java.awt.event.MouseAdapter;

import init.MyFrame;

public class GroupAction extends MouseAdapter implements MenuAction{
    @Override
    public void execute() {
        System.out.println("start executing GroupAction");
        String actionName = MyFrame.getFrame().getCanvas().getActionName();
        if("SelectAction".equals(actionName)) {
            MyFrame.getFrame().getCanvas().setAction("Group");
            System.out.println("Set Action edit action name to Group");
        }
        
    }    
}
