package canvas.btnAction;

import java.awt.event.MouseEvent;

public class CreateClassAction implements ButtonAction{
    

    @Override
    public void mouseClicked(MouseEvent e) {
        // Implement the onClick method here
        System.out.println("create class");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Implement the onClick method here
        System.out.println("onpress create class");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Implement the mouseReleased method here
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Implement the mouseEntered method here
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Implement the mouseExited method here
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }
}
