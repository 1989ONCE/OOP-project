package init;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MyToolbar extends JPanel {
    private final int toolBarWidth = 100;

    // constructor
    public MyToolbar(int toolBarHeight) {
        this.setPreferredSize(new Dimension(toolBarWidth, toolBarHeight));
        this.setBackground(Color.LIGHT_GRAY);
    }

    public int getToolBarWidth() {
        return this.toolBarWidth;
    }
}
