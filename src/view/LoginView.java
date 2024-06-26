package view;

import business.UserManager;
import core.Helper;
import entity.User;
import entity.UserRole;


import javax.swing.*;

public class LoginView extends Layout {
    private JPanel w_top;
    private JLabel lbl_welcome2;
    private JLabel lbl_welcome;
    private JPanel w_bottom;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JLabel lbl_pass;
    private JButton btn_login;
    private JPanel container;
    private JButton btn_exit;
    private JPasswordField fld_pass;
    private final UserManager userManager;

    public LoginView(){ //loginView constructor.

        this.userManager = new UserManager();
        this.add(container);
        this.guiInitialize(400,400);


        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_username, this.fld_pass};
            if(Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");
            }
            else{
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(), this.fld_pass.getText());
                if(loginUser == null ){
                    Helper.showMsg("notFound");
                }
            else{
                UserRole role = loginUser.getRole();
                switch (role){
                    case ADMIN -> openAdminView(loginUser);
                    case EMPLOYEE -> openEmployeeView(loginUser);
                    default -> throw new IllegalArgumentException();
                }
            }
        }
        });

        btn_exit.addActionListener(e -> {
            System.exit(0);
        });
    }
    private void openAdminView(User user){
        AdminView adminView = new AdminView(user);
        this.setVisible(true);
        dispose();
    }

    private void openEmployeeView(User user){
        EmployeeView employeeView = new EmployeeView(user);
        this.setVisible(true);
        dispose();
    }
}
