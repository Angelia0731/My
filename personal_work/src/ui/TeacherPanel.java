package ui;

import javax.swing.*;

public class TeacherPanel extends JPanel {
    private SystemFrame systemFrame;
    
    public TeacherPanel(SystemFrame systemFrame){
        this.systemFrame=systemFrame;
        
        this.setBounds(0, 0, SystemFrame.JFRAME_WIDTH, SystemFrame.JFRAME_HIGHT);
        this.setLayout(null);
        this.setVisible(true);

        
        systemFrame.setTitle("ΩÃ ¶");
        
        
       
    }

}
