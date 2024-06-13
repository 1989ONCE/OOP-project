package toolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import init.MyFrame;

public class MyToolbar extends JToolBar {
    private final int toolBarWidth = 100;
    private final Color toolBarColor = Color.LIGHT_GRAY;
    private final int toolBarOrientation = SwingConstants.VERTICAL;
    
    private FunctionBtn selectedBtn;
    // btn options and images
    private ArrayList<String> btnOption = new ArrayList<String>();
    {
        btnOption.add("Select");
        btnOption.add("Association Line");
        btnOption.add("Generalization Line");
        btnOption.add("Composition Line");
        btnOption.add("Dependency Line");
        btnOption.add("Create Class");
        btnOption.add("Create Use Case");
    }
    private ArrayList<String> btnUnselectedImg = new ArrayList<String>();
    {
        btnUnselectedImg.add("btnImg/select.png");
        btnUnselectedImg.add("btnImg/assoLine.png");
        btnUnselectedImg.add("btnImg/general.png");
        btnUnselectedImg.add("btnImg/compo.png");
        btnUnselectedImg.add("btnImg/dependency.png");
        btnUnselectedImg.add("btnImg/class.png");
        btnUnselectedImg.add("btnImg/usecase.png");
    }
    
    private ArrayList<String> btnSelectedImg = new ArrayList<String>();
    {
        btnSelectedImg.add("btnImg/select_antiwhite.png");
        btnSelectedImg.add("btnImg/assoLine_antiwhite.png");
        btnSelectedImg.add("btnImg/general_antiwhite.png");
        btnSelectedImg.add("btnImg/compo_antiwhite.png");
        btnSelectedImg.add("btnImg/dependency_antiwhite.png");
        btnSelectedImg.add("btnImg/class_antiwhite.png");
        btnSelectedImg.add("btnImg/usecase_antiwhite.png");
    }

    // constructor
    public MyToolbar(int toolBarHeight) {
        this.setToolBar(this);
        this.setPreferredSize(new Dimension(toolBarWidth, toolBarHeight));
        this.setBackground(this.toolBarColor);
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
        this.setOrientation(toolBarOrientation); // set the the toolbar vertically
    }

    // Setters
    public void setToolBar(MyToolbar inputToolbar) {
       MyFrame.getFrame().setToolBar(inputToolbar);
    }

    public void setSelectedBtn(FunctionBtn btn) {
        btn.setSelected(true);
        this.selectedBtn = btn;
    }

    // Getters
    public int getToolBarWidth() {
        return this.toolBarWidth;
    }

    public FunctionBtn getSelectedBtn() {
        return this.selectedBtn;
    }
    
}
