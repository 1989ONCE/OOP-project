package toolbar;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import canvas.MyCanvas;
import init.MyFrame;

public class FunctionBtn extends JButton {
    // serialVersionUID is a version control in a Serializable class, declaring because JButton implements Serializable
    private static final long serialVersionUID = 1L; 

    private final int btnWidth = 90;
    private final int btnHeight = 90;
    private final int btnMarginY = 10;
    private final int btnMarginX = 10;
    private final Color btnBgColor = Color.WHITE;

    // Constructor
    public FunctionBtn(String btnName, String unselectedImagePath, String selectedImagePath) {
        this.setMargin(new Insets(btnMarginY, btnMarginX, btnMarginY, btnMarginX));
        this.setToolTipText(btnName);
        // L&F (Look and Feel) customization
        this.setBackground(btnBgColor);
        this.setOpaque(true);
        this.setBorderPainted(false);

        this.setPreferredSize(new Dimension(btnWidth, btnHeight));
        URL unselectedImageURL = getClass().getResource(unselectedImagePath);
        if (unselectedImageURL != null) {
            this.setIcon(new ImageIcon(unselectedImageURL));
        }
    
        URL selectedImageURL = getClass().getResource(selectedImagePath);
        if (selectedImageURL != null) {
            this.setSelectedIcon(new ImageIcon(selectedImageURL));
            this.setPressedIcon(new ImageIcon(selectedImageURL));
        }

        // center the icon
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);

        this.repaint();
        this.addActionListener(e -> {
            MyToolbar toolbar = MyFrame.getFrame().getToolBar();
            MyCanvas canvas = MyFrame.getFrame().getCanvas();
            toolbar.getSelectedBtn().setSelected(false);
            toolbar.setSelectedBtn(this);
            canvas.setAction(toolbar.getSelectedBtn().getBtnName());
        });
    }

    public String getBtnName() {
        return this.getToolTipText();
    }

}
