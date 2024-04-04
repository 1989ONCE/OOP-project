package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JMenuItem;

import menu.menuAction.*;

public class MenuItem extends JMenuItem implements ActionListener{
    
    // menuitems' name and corresponding menuActions
    private final Map<String, MenuAction> actions = new HashMap<>();
    {
        actions.put("Open New Canvas", new OpenNewCanvasAction());
        actions.put("Save As...", new SaveAsAction());
        actions.put("Close Window", new CloseWindowAction());
        actions.put("Group", new GroupAction());
        actions.put("UnGroup", new UnGroupAction());
        actions.put("Rename Object", new RenameObjectAction());
    }

    public MenuItem(String menuItemName) {
        super(menuItemName); // set the text of this MenuItem
        this.addActionListener(this); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        // polymorphism of MenuAction
        MenuAction action = actions.get(actionCommand);
        if (action != null) {
            action.execute();
        }
    }
}


