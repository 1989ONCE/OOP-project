package init;
import javax.swing.SwingUtilities;

//  main frame initializing, including three parts: canvas, toolbar and menu
public class UmlBase { 
    public static void main(String[] args) { 
        /** 因為Java Swing的component不是thread-safe的,
        SwingUtilities.invokeLater()可以讓程式在Event-Dispatching Thread（EDT）上執行
        EDT可以確保Swing component在同一個thread上建立、更新，避免thread同步的問題 **/

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Frame();
            }});
    }
}
