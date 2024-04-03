package canvas.line;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GeneralizationLine extends Line {

    public GeneralizationLine(int startX, int startY) {
        super(startX, startY);
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.lineColor);
        int triangleSize = 10;
        int headAngle = 30; // degree of the arrow head
        drawArrowHead(g2, this.startPort.getX(), this.startPort.getY(), this.endPort.getX(), this.endPort.getY(), triangleSize, headAngle);  

    }

    private void drawArrowHead(Graphics g, int x0, int y0, int x1, int y1, int headLength, int headAngle) {
        double offs = headAngle * Math.PI / 180.0;
        double angle = Math.atan2(y0 - y1, x0 - x1);
        int x2 = x1 + (int) (headLength * Math.cos(angle));
        int y2 = y1 + (int) (headLength * Math.sin(angle));
        int[] xs = { x1 + (int) (headLength * Math.cos(angle + offs)), x1,
                    x1 + (int) (headLength * Math.cos(angle - offs)) };
        int[] ys = { y1 + (int) (headLength * Math.sin(angle + offs)), y1,
                    y1 + (int) (headLength * Math.sin(angle - offs)) };
        g.drawLine(x0, y0, x2, y2);
        g.drawPolygon(xs, ys, 3);
    }
}