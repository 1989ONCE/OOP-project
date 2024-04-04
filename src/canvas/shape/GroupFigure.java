package canvas.shape;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import canvas.btnAction.GroupInterface;

public class GroupFigure extends Figure implements GroupInterface {
    // setting of dashed line
    private final static float dash1[] = { 10.0f };
    private final static BasicStroke dashed = new BasicStroke(1.0f,
      BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);

    public GroupFigure(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    private ArrayList<Figure> compositeFigures = new ArrayList<>();
    
    @Override
    public void draw(Graphics g_) {
        Graphics2D g = (Graphics2D) g_;
        g.setColor(this.figureColor);

        // Draw a dashed rectangle
        g.setStroke(dashed);
        g.drawRect(x, y, width, height);

        // Draw ports
        topPort.draw(g);
        rightPort.draw(g);
        bottomPort.draw(g);
        leftPort.draw(g);
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        for (Figure figure : compositeFigures) {
            figure.move(dx, dy);
        }
    }
    // @Override
    // public void resizeBasedOn(Port port) {
    //     for (Figure figure : compositeFigures) {
    //         figure.resizeBasedOn(port);
    //     }
    // }

    @Override
    public void addFigureToList(Figure figure) {
        this.compositeFigures.add(figure);
    }
    
    // Add getters and setters for x, y, width, height here...
    // Add methods for handling ports here...

    public ArrayList<Figure> getInsideFigures() {
        return compositeFigures;
    }
}
