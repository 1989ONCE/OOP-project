package canvas.line;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DependencyLine extends Line {

    public DependencyLine(int startX, int startY) {
        super(startX, startY);
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.lineColor);
        // Draw arrowhead line
        int arrowSize = 10;
        int headAngle = 30; // degree of the arrow head
        drawArrowHead(g2, this.startPort.getX(), this.startPort.getY(), this.endPort.getX(), this.endPort.getY(),arrowSize, headAngle);  

    }

    private void drawArrowHead(Graphics g, int x0, int y0, int x1, int y1, int headLength, int headAngle) {
        double offs = headAngle * Math.PI / 180.0;
        double angle = Math.atan2(y0 - y1, x0 - x1);
        int[] xs = { x1 + (int) (headLength * Math.cos(angle + offs)), x1,
                    x1 + (int) (headLength * Math.cos(angle - offs)) };
        int[] ys = { y1 + (int) (headLength * Math.sin(angle + offs)), y1,
                    y1 + (int) (headLength * Math.sin(angle - offs)) };
        
        Graphics2D g2d = (Graphics2D) g;
        // 新功能：Dependency Line
		// Dashed Line Reference: http://www.java2s.com/Tutorials/Java/Graphics/Graphics_Settings/Use_dashed_stroke_to_draw_dashed_line_in_Java.htm
		float[] dash1 = { 2f, 0f, 2f };
		BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
		g2d.setStroke(bs1);
        g2d.drawLine(x0, y0, x1, y1);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawPolyline(xs, ys, 3);
    }
}