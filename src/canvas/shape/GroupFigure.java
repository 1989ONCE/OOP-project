package canvas.shape;

import java.awt.Graphics;
import java.util.ArrayList;
import canvas.btnAction.GroupInterface;

public class GroupFigure extends Figure implements GroupInterface {
    public GroupFigure(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    private ArrayList<Figure> compositeFigures = new ArrayList<>();
    
    @Override
    public void draw(Graphics g) {
        g.setColor(this.figureColor);
        // int levelHeight = height / 3;
        g.drawRect(x, y, width, height);
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
