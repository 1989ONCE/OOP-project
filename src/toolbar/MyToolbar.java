package toolbar;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import init.MyFrame;

public class MyToolbar extends JToolBar {
    private final int toolBarWidth = 100;

    // btn options and images
    private ArrayList<String> btnOption = new ArrayList<String>(Arrays.asList("Select", "Association Line", "Generalization Line", "Composition Line", "Create Class", "Create Use Case"));
    private ArrayList<String> btnUnselectedImg = new ArrayList<String>(
            Arrays.asList("btnImg/select.png", "btnImg/assoLine.png", "btnImg/general.png", "btnImg/compo.png", "btnImg/class.png", "btnImg/usecase.png")
    );
    private ArrayList<String> btnSelectedImg = new ArrayList<String>(
            Arrays.asList("btnImg/select_antiwhite.png", "btnImg/assoLine_antiwhite.png", "btnImg/general_antiwhite.png", "btnImg/compo_antiwhite.png", "btnImg/class_antiwhite.png", "btnImg/usecase_antiwhite.png")
    );

    private final Color toolBarColor = Color.LIGHT_GRAY;
    private FunctionBtn selectedBtn;



    // constructor
    public MyToolbar(int toolBarHeight) {
        this.setToolBar(this);
        this.setPreferredSize(new Dimension(toolBarWidth, toolBarHeight));
        this.setBackground(toolBarColor);
        this.setFloatable(false); // user can't move the toolbar

        // add buttons
        for (int i = 0; i < btnOption.size(); i++) {
            FunctionBtn functionBtn = new FunctionBtn(btnOption.get(i), btnUnselectedImg.get(i), btnSelectedImg.get(i));
            if (i == 0) { // set the first button as default selected
                this.selectedBtn = functionBtn;
                this.selectedBtn.setSelected(true);
            }
            this.add(functionBtn);
        }
        this.setOrientation(SwingConstants.VERTICAL); // set the the toolbar vertically
    }



    // set functions
    public void setToolBar(MyToolbar inputToolbar) {
       MyFrame.getFrame().setToolBar(inputToolbar);
    }

    public void setSelectedBtn(FunctionBtn btn) {
        btn.setSelected(true);
        this.selectedBtn = btn;
    }

    // get functions
    public int getToolBarWidth() {
        return this.toolBarWidth;
    }

    public FunctionBtn getSelectedBtn() {
        return this.selectedBtn;
    }
    
}
