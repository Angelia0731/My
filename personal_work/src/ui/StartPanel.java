package ui;

import java.awt.event.*;

import javax.swing.*;

import personalwork.IOHelper;
import personalwork.LoginIn;
import personalwork.TeachingSystem;

@SuppressWarnings("serial")
public class StartPanel extends JPanel {
    private SystemFrame systemFrame;

    JLabel userLabel = new JLabel("用户类型");
    JLabel idLabel = new JLabel("用户名");
    JLabel passwordLabel = new JLabel("密码");

    JButton loginButton = new JButton("登录");
    // JButton registerButton = new JButton("注册");
    JButton exitButton = new JButton("退出");

    private String[] userList = { "管理员", "教师", "学生" };
    @SuppressWarnings({ "rawtypes", "unchecked" })
    JComboBox userBox = new JComboBox(userList);

    JTextField idField = new JTextField("请输入用户名");
    JPasswordField passwordField = new JPasswordField("000000");

    public StartPanel(SystemFrame systemFrame) {
        this.systemFrame = systemFrame;
        systemFrame.setIconImage(AllImage.IMG_LOGO);
        this.setBounds(0, 0, SystemFrame.JFRAME_WIDTH, SystemFrame.JFRAME_HIGHT);
        this.setLayout(null);

        userBoxInit();
        labelInit();
        fieldInit();
        buttonInit();

        this.setVisible(true);

        systemFrame.add(this);
        systemFrame.repaint();
    }

    public void userBoxInit() {
        /*
         * 初始化userBox
         */
        userBox.setSize(SystemFrame.JFRAME_WIDTH / 2,
                SystemFrame.JFRAME_HIGHT / 10);
        userBox.setLocation(SystemFrame.JFRAME_WIDTH * 3 / 10,
                SystemFrame.JFRAME_HIGHT / 10);
        userBox.setVisible(true);
        this.add(userBox);

    }

    public void labelInit() {
        userLabel.setSize(SystemFrame.JFRAME_WIDTH / 5,
                SystemFrame.JFRAME_HIGHT / 10);
        userLabel.setLocation(SystemFrame.JFRAME_WIDTH / 8,
                SystemFrame.JFRAME_HIGHT / 10);
        userLabel.setVisible(true);

        idLabel.setSize(SystemFrame.JFRAME_WIDTH / 5,
                SystemFrame.JFRAME_HIGHT / 10);
        idLabel.setLocation(SystemFrame.JFRAME_WIDTH / 8,
                SystemFrame.JFRAME_HIGHT * 3 / 10);
        idLabel.setVisible(true);

        passwordLabel.setSize(SystemFrame.JFRAME_WIDTH / 5,
                SystemFrame.JFRAME_HIGHT / 10);
        passwordLabel.setLocation(SystemFrame.JFRAME_WIDTH / 8,
                SystemFrame.JFRAME_HIGHT / 2);
        passwordLabel.setVisible(true);

        this.add(userLabel);
        this.add(idLabel);
        this.add(passwordLabel);

    }

    public void fieldInit() {
        idField.setSize(SystemFrame.JFRAME_WIDTH / 2,
                SystemFrame.JFRAME_HIGHT / 10);
        idField.setLocation(SystemFrame.JFRAME_WIDTH * 3 / 10,
                SystemFrame.JFRAME_HIGHT * 3 / 10);
        idField.setVisible(true);

        passwordField.setSize(SystemFrame.JFRAME_WIDTH / 2,
                SystemFrame.JFRAME_HIGHT / 10);
        passwordField.setLocation(SystemFrame.JFRAME_WIDTH * 3 / 10,
                SystemFrame.JFRAME_HIGHT / 2);
        passwordField.setVisible(true);

        this.add(passwordField);

        this.add(idField);
    }

    public void buttonInit() {

        loginButton.setSize(SystemFrame.JFRAME_WIDTH / 5,
                SystemFrame.JFRAME_HIGHT / 10);
        loginButton.setLocation(SystemFrame.JFRAME_WIDTH * 3 / 10,
                SystemFrame.JFRAME_HIGHT * 7 / 10);
        LoginButtonListener l1 = new LoginButtonListener();
        loginButton.addActionListener(l1);
        loginButton.setVisible(true);

        // registerButton.setSize(SystemFrame.JFRAME_WIDTH / 10,
        // SystemFrame.JFRAME_HIGHT / 10);
        // registerButton.setLocation(SystemFrame.JFRAME_WIDTH / 2,
        // SystemFrame.JFRAME_HIGHT * 7 / 10);
        // RegisterButtonListener l2 = new RegisterButtonListener();
        // registerButton.addActionListener(l2);
        // registerButton.setVisible(true);

        exitButton.setSize(SystemFrame.JFRAME_WIDTH / 5,
                SystemFrame.JFRAME_HIGHT / 10);
        exitButton.setLocation(SystemFrame.JFRAME_WIDTH * 6 / 10,
                SystemFrame.JFRAME_HIGHT * 7 / 10);
        ExitButtonListener l3 = new ExitButtonListener();
        exitButton.addActionListener(l3);
        exitButton.setVisible(true);

        this.add(loginButton);
        // this.add(registerButton);
        this.add(exitButton);

    }

    class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userType = (String) userBox.getSelectedItem();
            String id = idField.getText();
            String password = String.valueOf(passwordField.getPassword());

            switch (userType) {
            case "管理员":
                if (LoginIn.managerLoginIn(id, password) != null) {
                    showDialog("登陆成功！");
                    systemFrame.setContentPane(new ManagerPanel(systemFrame));
                    systemFrame.repaint();
                    break;
                } else {
                    showDialog("输入错误！");
                    break;
                }
            case "教师":
                if (LoginIn.teacherLoginIn(id, password) != null) {
                    showDialog("登陆成功！");
                    systemFrame.setContentPane(new TeacherPanel(systemFrame));
                    systemFrame.repaint();
                    break;
                } else {
                    showDialog("输入错误！");
                    break;
                }
            case"学生":
                if(LoginIn.studentLoginIn(id, password)!=null){
                    showDialog("登陆成功！");
                    systemFrame.setContentPane(new StudentPanel(systemFrame));
                    systemFrame.repaint();
                    break;
                } else{
                    showDialog("输入错误！");
                    break;
                
                }

            }

        }
    }

    // class RegisterButtonListener implements ActionListener {
    // public void actionPerformed(ActionEvent e) {
    // String userType = (String) userBox.getSelectedItem();
    // String id = idField.getText();
    // String password = String.valueOf(passwordField.getPassword());
    //
    // }
    //
    // }

    class ExitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);

        }
    }

    public void showDialog(String s) {
        JDialog infoDialog = new JDialog();
        infoDialog.setSize(200, 100);
        infoDialog.setLocation(600, 300);
        infoDialog.setVisible(true);

        JLabel infoLabel = new JLabel(s);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setVisible(true);
        infoDialog.add(infoLabel);

    }

}
