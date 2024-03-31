package init;
import javax.swing.*;


public class Frame extends JFrame{
    private final String title = "umlEditor by Susan Chen";
    private final int width = 800;
    private final int height = 700;
    
    // constructor
    public Frame() {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(this.width, this.height);
        this.setVisible(true);
    }
}
