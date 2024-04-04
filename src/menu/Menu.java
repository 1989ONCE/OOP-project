package menu;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Menu extends JMenuBar{
    private final int menuHeight = 30;

    // menu options and corresponding menu items
    private ArrayList<String> menuOption = new ArrayList<String>();
    {
        menuOption.add("File");
        menuOption.add("Edit");
    }
    private ArrayList<String> fileMenuItem = new ArrayList<String>();
    {
        fileMenuItem.add("Open New Canvas");
        fileMenuItem.add("Save As...");
        fileMenuItem.add("Close Window");
    }
    private ArrayList<String> editMenuItem = new ArrayList<String>();
    {
        editMenuItem.add("Group");
        editMenuItem.add("UnGroup");
        editMenuItem.add("Rename Object");
    }
    private ArrayList<ArrayList<String>> grandItem = new ArrayList<>();
    {
        grandItem.add(fileMenuItem);
        grandItem.add(editMenuItem);
    }


    // constructor
    public Menu(int menuWidth) {
        this.setPreferredSize(new Dimension(menuWidth, menuHeight));

        // add menuItem
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

    // Getters
    public int getMenuHeight() {
        return this.menuHeight;
    }
}
