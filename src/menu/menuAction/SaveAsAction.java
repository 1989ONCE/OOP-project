package menu.menuAction;

import java.io.IOException;
import canvas.MyCanvas;
import init.MyFrame;

public class SaveAsAction implements MenuAction {
    @Override
    public void execute() {
        MyFrame frame = MyFrame.getFrame();
        MyCanvas canvas = frame.getCanvas();
        try {
            canvas.saveCanvas(canvas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}