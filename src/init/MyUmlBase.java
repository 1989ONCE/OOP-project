package init;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

//  main frame initializing, including three parts: canvas, toolbar and menu
public class MyUmlBase { 
    public static void main(String[] args) { 
        /** 因為Java Swing的component不是thread-safe的,
        SwingUtilities.invokeLater()可以讓程式在Event-Dispatching Thread（EDT）上執行
        EDT可以確保Swing component在同一個thread上建立、更新，避免thread同步的問題 **/

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                init();
            }
        });
    }

    public static void init() {
        MyFrame myFrame = new MyFrame(); // 初始化Frame
        JOptionPane.showMessageDialog(myFrame, "Welcome to UML Editor by Susan Chen");
        
        myFrame.initSection(myFrame); // 初始化Canvas, Toolbar, Menu
        myFrame.revalidate(); // 因為JOptonPane會導致畫面重繪問題，所以使用revalidate(), repaint()重新繪製畫面
        myFrame.repaint();
    }
}
