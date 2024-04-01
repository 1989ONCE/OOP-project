package init;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MyCanvas extends JPanel{
    private final Color canvasBgColor = Color.WHITE;
    
    // constructor
    public MyCanvas(int canvaWidth, int canvaHeight) { //初始化關於Canvas的相關設定

        this.setPreferredSize(new Dimension(canvaWidth, canvaHeight));//建立一個視窗
        this.setBackground(canvasBgColor);
        this.setLayout(new BorderLayout());

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

}
