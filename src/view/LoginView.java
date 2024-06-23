package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

public class LoginView extends Layout {
    private JPanel w_top;
    private JLabel lbl_welcome2;
    private JLabel lbl_welcome;
    private JPanel w_bottom;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JLabel lbl_pass;
    private JTextField fld_pass;
    private JButton btn_login;
    private JPanel container;
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
////                    System.out.println(loginUser.toString());
//                    AdminView adminView = new AdminView(loginUser);
//                    dispose();
                    System.out.println("Login Successful");
                }
            }

        });
    }
}
