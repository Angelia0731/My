package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

import personalwork.TeachingSystem;

public class SystemFrame extends JFrame {
    private TeachingSystem system = new TeachingSystem();

    public static final int JFRAME_WIDTH = 821;
    public static final int JFRAME_HIGHT = 547;
    /**
     * ����ϵͳ��Frame
     */
    private int screenWidth;
    private int screenHeight;
    private int locationX;
    private int locationY;

    public SystemFrame() {
        this.setTitle("ѧ������ϵͳ");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(JFRAME_WIDTH, JFRAME_HIGHT);
        this.setResizable(false);
        // ����Ϊ����
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        this.screenWidth = screen.width;
        this.screenHeight = screen.height;
        this.locationX = (screen.width - this.getWidth()) / 2;
        this.locationY = (screen.height - this.getHeight()) / 2 - 16;
        this.setLocation(this.locationX, this.locationY);
        
        this.setVisible(true);

    }

}
