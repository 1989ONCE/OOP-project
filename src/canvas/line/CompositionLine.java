package canvas.line;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class CompositionLine extends Line {

    public CompositionLine(int startX, int startY) {
        super(startX, startY);
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.lineColor);
        drawDimondLine(g2, this.endPort.getX(), this.endPort.getY());  
    }

    private void drawDimondLine(Graphics g, int x, int y) {
        int dimondSize = 5;
        g.drawLine(this.startPort.getX(), this.startPort.getY(), x - dimondSize*4, y);
        
        // start to draw the dimond
        // two left most lines（左邊兩條線）
        g.drawLine(x - dimondSize*4, y, x - dimondSize*2, y + dimondSize);
        g.drawLine(x - dimondSize*4, y, x - dimondSize*2, y - dimondSize);
        // two right most lines(右邊兩條線)
        g.drawLine(x, y, x - dimondSize*2, y + dimondSize);
        g.drawLine(x, y, x - dimondSize*2, y - dimondSize);
    }
}