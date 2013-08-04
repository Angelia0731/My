package ui;

import javax.swing.JPanel;

public class StudentPanel extends JPanel{
 private SystemFrame systemFrame;
    
    public StudentPanel(SystemFrame systemFrame){
        this.systemFrame=systemFrame;
        
        this.setBounds(0, 0, SystemFrame.JFRAME_WIDTH, SystemFrame.JFRAME_HIGHT);
        this.setLayout(null);
        this.setVisible(true);

        
        systemFrame.setTitle("Ñ§Éú");
        
        
       
    }
    
    
    

}
