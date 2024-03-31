package init;
import javax.swing.*;


public class Frame {
    private String title = "umlEditor by Susan Chen";

    public Frame() {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setVisible(true);
    }
}
