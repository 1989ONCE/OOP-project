package menu.menuAction;

import canvas.MyCanvas;
import init.MyFrame;

public class CloseWindowAction implements MenuAction {
    @Override
    public void execute() {
        MyFrame frame = MyFrame.getFrame();
        MyCanvas canvas = frame.getCanvas();

        // keepOpen would be false if user click "Don't Save" in the confirm dialog
        boolean keepOpen = canvas.showConfirmDialog(canvas);
        if (!keepOpen){
            // close the window and exit the program
            frame.dispose();
            System.exit(0);
        }
    }
}
