package canvas.btnAction;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectAction implements MouseListener {
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // Implement the onClick method here
        System.out.println("Select on click");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Implement the onClick method here
        System.out.println("onpress select");
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
}
