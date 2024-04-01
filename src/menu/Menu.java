package menu;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Menu extends JMenuBar{
    private final int menuHeight = 30;
    private ArrayList<String> menuOption = new ArrayList<String>(Arrays.asList("File", "Edit"));
    
    private ArrayList<String> fileMenuItem = new ArrayList<String>(Arrays.asList("Open New Canvas", "Save As...", "Close Window"));
    private ArrayList<String> editMenuItem = new ArrayList<String>(Arrays.asList("Group", "UnGroup", "Rename Object"));
    private ArrayList<ArrayList<String>> grandItem = new ArrayList<>(Arrays.asList(fileMenuItem, editMenuItem));
    
    // constructor
    public Menu(int menuWidth) {
        this.setPreferredSize(new Dimension(menuWidth, menuHeight));

        for (int i = 0; i < menuOption.size(); i++) {
            JMenu jmenu = new JMenu(menuOption.get(i));
            System.out.println("MenuOption.get(i): " + menuOption.get(i));
            for (int j = 0; j < grandItem.get(i).size(); j++) {
                MenuItem menuItem = new MenuItem(grandItem.get(i).get(j));
                System.out.println("MenuOption.get(i): " + grandItem.get(i).get(j));
                jmenu.add(menuItem);
            }
            this.add(jmenu);
        }
       
    }
    public int getMenuHeight() {
        return this.menuHeight;
    }
}
