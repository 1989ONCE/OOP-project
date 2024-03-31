package menu.menuAction;
import init.MyFrame;
import init.MyCanvas;

public class CloseWindowAction implements MenuAction {
    @Override
    public void execute() {
        MyFrame frame = MyFrame.getFrame();
        MyCanvas canvas = frame.getCanvas();
        boolean keepOpen = canvas.showConfirmDialog(canvas);
        if (!keepOpen){
            frame.dispose();
        }
    }
}
