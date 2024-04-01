package canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import canvas.btnAction.AssociateAction;
import canvas.btnAction.CompositionAction;
import canvas.btnAction.CreateClassAction;
import canvas.btnAction.CreateUseCaseAction;
import canvas.btnAction.GeneralAction;
import canvas.btnAction.SelectAction;
import canvas.shape.UseCaseFigure;

public class MyCanvas extends JPanel {
    private MouseListener action;
    private Object currentFigure;
    private List<Object> figures = new ArrayList<>();
    private final String defaultAction = "Select";
    private final Color canvasBgColor = Color.WHITE;
    private final Map<String, MouseListener> functions = new HashMap<>();
    // private MyCanvas canvas = MyFrame.getFrame().getCanvas();
    {
        functions.put("Select", new SelectAction());
        functions.put("Association Line", new AssociateAction());
        functions.put("Generalization Line", new GeneralAction());
        functions.put("Composition Line", new CompositionAction());
        functions.put("Create Class", new CreateClassAction());
        functions.put("Create Use Case", new CreateUseCaseAction());
    }
    
    // constructor
    public MyCanvas(int canvasWidth, int canvasHeight) {
        this.action = functions.get(defaultAction); // default action
        this.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        this.setBackground(canvasBgColor);

        
        // System.out.println(action);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                action.mousePressed(e);
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                action.mouseClicked(e);
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                repaint();
            }
            
        });
    }

    public void newCanvas(MyCanvas canvas){
        canvas.setBackground(canvasBgColor);
        canvas.removeAll();
        canvas.repaint();
    }

    public boolean saveCanvas(MyCanvas canvas) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".png", "png"));
        fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[1]);
        int result = fileChooser.showSaveDialog(canvas);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            file = new File(file.getParentFile(), file.getName() + "." + "png");
            BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            canvas.paint(g2d);
            g2d.dispose();
            ImageIO.write(image, "png", file);
            return true;
        }
        return false;

    }

    public boolean showConfirmDialog(MyCanvas canvas){
        String[] options = {"Save", "Cancel", "Don't Save"};
        int dialogResult = JOptionPane.showOptionDialog(canvas, "Your changes will be lost if you don't save them.", "Do you want to save the changes you made to this canvas?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        switch(dialogResult) {
            case JOptionPane.YES_OPTION: // save and open new canvas
                try { 
                    boolean isSaved = canvas.saveCanvas(canvas);
                    if(isSaved){
                        canvas.newCanvas(canvas);
                        System.out.println("Save and Open New Canvas");
                        return false;
                    }
                    else{
                        return true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            case JOptionPane.NO_OPTION: // open new canvas / close window
                break;
            case JOptionPane.CANCEL_OPTION: // cancel
                canvas.newCanvas(canvas);
                return false;
        }
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Object figure : figures) {
            ((UseCaseFigure)figure).draw(g);
        }
        if (currentFigure != null) {
            ((UseCaseFigure)currentFigure).draw(g);
        }
    }

    public void setCurrentFigure(Object figure) {
        this.currentFigure = figure;
    }

    public Object getCurrentFigure() {
        return this.currentFigure;
    }

    public void addFigure(Object figure) {
        this.figures.add(figure);
    }

    // update action if user change the button function
    public void setAction(String btnName) {
        // polymorphism of MouseListener
        MouseListener action = functions.get(btnName);
        this.action = action;
    }
}
