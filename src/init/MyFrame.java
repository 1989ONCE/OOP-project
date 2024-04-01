package init;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import menu.Menu;
import toolbar.MyToolbar;


public class MyFrame extends JFrame{

    // set final to prevent from being changed
    private final String title = "UML Editor by Susan Chen";
    private final int frameWidth = 800;
    private final int frameHeight = 680;
    private MyCanvas canvas;
    private Menu menuBar;
    private MyToolbar toolBar;
    private static MyFrame frame;

    // constructor
    public MyFrame() {
        this.setFrame(this);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(this.frameWidth, this.frameHeight);
        this.setVisible(true);
    }

    public void initSection(MyFrame frame) {
        // add menu
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        this.menuBar = new Menu(frameWidth);

        // add toolBar
        int toolBarHeight = frameHeight - this.menuBar.getMenuHeight();
        this.toolBar = new MyToolbar(toolBarHeight);

        //add canvas
        int canvaWidth = frameWidth - this.toolBar.getToolBarWidth();
        int canvaHeight = frameHeight - this.menuBar.getMenuHeight();
        this.canvas = new MyCanvas(canvaWidth, canvaHeight);        
        
        // add components to frame
        frame.add(this.canvas, BorderLayout.CENTER);
        frame.add(this.toolBar, BorderLayout.WEST);
        frame.setJMenuBar(this.menuBar);
    }

    public void setFrame(MyFrame frame){
        MyFrame.frame = frame;
    }

    public static MyFrame getFrame(){
        return frame;
    }

    public int getFrameWidth() {
        return frameWidth;
    }
    public int getFrameHeight() {
        return frameHeight;
    }

    public MyCanvas getCanvas(){
        return this.canvas;
    }
    public Menu getMenu(){
        return this.menuBar;
    }

    public MyToolbar getToolBar(){
        return this.toolBar;
    }
}
