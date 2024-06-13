package canvas.shape;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
// import canvas.btnAction.GroupInterface;

public class GroupFigure extends Figure {
    // setting of dashed line
    private final float dash1[] = { 10.0f };
    private final BasicStroke dashed = new BasicStroke(1.0f,
      BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
    private ArrayList<Figure> compositeFigures = new ArrayList<>();
    
    // Constructor
    public GroupFigure(int x, int y, int width, int height, String figureName) {
        super(x, y, width, height, figureName);
    }

    // polymorphism
    @Override
    public void draw(Graphics g_) {
        Graphics2D g = (Graphics2D) g_;
        g.setColor(this.figureColor);

        // Save the original stroke
        Stroke originalStroke = g.getStroke();

        // Draw a dashed rectangle
        g.setStroke(dashed);
        g.drawRect(x, y, width, height);
        
        // Calculate the x-coordinate for the string
        int midX = x + 5;
        int midY = y - 5;

        g.drawString(this.figureName, midX, midY);

        // Draw ports
        topPort.draw(g);
        rightPort.draw(g);
        bottomPort.draw(g);
        leftPort.draw(g);
        g.setStroke(originalStroke);
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        for (Figure figure : compositeFigures) {
            figure.move(dx, dy);
        }
    }
    
    public void addFigureToList(Figure figure) {
        this.compositeFigures.add(figure);
    }

    public ArrayList<Figure> getInsideFigures() {
        return compositeFigures;
    }
}
