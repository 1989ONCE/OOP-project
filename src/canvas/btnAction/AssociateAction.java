package canvas.btnAction;

import java.awt.event.MouseListener;

import java.awt.event.MouseEvent;


public class AssociateAction implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        // Implement the onClick method here
        System.out.println("Associate Line");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Implement the onClick method here
        System.out.println("press associate line");
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
