package menu.menuAction;

import java.io.IOException;
import canvas.MyCanvas;
import init.MyFrame;

public class SaveAsAction implements MenuAction {
    @Override
    public void execute() {
        MyCanvas canvas = MyFrame.getFrame().getCanvas();
        try {
            // call saveCanvas for user to save the current canvas as .png file
            canvas.saveCanvas(canvas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}