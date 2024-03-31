package menu.menuAction;

import init.MyCanvas;
import init.MyFrame;

public class OpenNewCanvasAction implements MenuAction {
    @Override
    public void execute() {
        MyFrame frame = MyFrame.getFrame();
        MyCanvas canvas = frame.getCanvas();

        // confirmation dialog to make sure user wants to open new canvas
        canvas.showConfirmDialog(canvas);
    }
}
