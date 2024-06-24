package view;

import business.UserManager;
import entity.User;
import core.Helper;
import entity.UserRole;


import javax.swing.*;

public class UserView extends Layout {
    private JPanel container;
    private JLabel lbl_welcome;
    private JLabel lbl_user_name;
    private JLabel lbl_user_password;
    private JLabel lbl_user_role;
    private JPanel pnl_button;
    private JButton btn_save;
    private JTextField fld_user_name;
    private JTextField fld_user_password;
//    private JComboBox cmb_user_role;
    private JComboBox<UserRole> cmb_user_role;
    private UserManager userManager;
    private User user;


    public UserView (User user){
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitialize(500, 500);
        this.user = user;
        if (this.user == null) {
            dispose();
        }
        this.lbl_welcome.setText("User Options " + this.user.getUsername());

        //Define user role for combobox.
        this.cmb_user_role.setModel(new DefaultComboBoxModel<>(UserRole.values()));
        this.cmb_user_role.setSelectedItem(null);


        if(this.user.getId() != 0) {
            this.fld_user_name.setText(this.user.getUsername());
            this.fld_user_password.setText(this.user.getPassword());
//            this.cmb_user_role.getModel().setSelectedItem(this.user.getRole());
            this.cmb_user_role.setModel(new DefaultComboBoxModel<>(UserRole.values()));
        }
        this.btn_save.addActionListener(e -> {

            if (Helper.isFieldListEmpty(new JTextField[]
                    {this.fld_user_name, this.fld_user_password}) ||
                    Helper.isComboBoxEmpty(this.cmb_user_role))
            {
                    Helper.showMsg("fill");
            } else {
                //Set values from fields.
                boolean result = false;
                this.user.setUsername(this.fld_user_name.getText());
                this.user.setPassword(this.fld_user_password.getText());
                this.user.setRole((UserRole) cmb_user_role.getSelectedItem());
//                this.cmb_user_role.setModel(new DefaultComboBoxModel<>(UserRole.values()));


                //If the user object exists, update it, if not, create a new record.
                if (this.user.getId() != 0) {
                    result = this.userManager.update(this.user);
                } else {
                    result = this.userManager.save(this.user);
                }

                //save status messages
                if (result) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}

