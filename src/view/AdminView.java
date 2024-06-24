package view;

import business.UserManager;
import core.Helper;
import entity.User;
import entity.UserRole;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout{
    private User user;
    private JPanel container;
    private JPanel pnl_welcome;
    private JLabel lbl_welcome;
    private JButton btn_logout_admin;
    private JPanel pnl_filter;
    private JLabel lbl_filter_user_role;
//    private JComboBox cmb_filter_user_role;
    private JComboBox<UserRole> cmb_filter_user_role;
    private JButton btn_search_admin;
    private JButton btn_clear_admin;
    private JPanel pnl_user;
    private JScrollPane scrl_user;
    private JTable tbl_user;
    private JButton btn_add_admin;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private Object[] col_user;
    private UserManager userManager;
    private JPopupMenu user_menu;

    public AdminView(User user) {

        this.userManager = new UserManager();
        this.add(container);
        this.guiInitialize(500, 500);
        this.user = user;
        if (this.user == null) {
            dispose();
        }
//        this.lbl_welcome.setText("Welcome " + this.user.getUsername());

        loadUserTable(null);
        loadUserComponent();
        loadUserFilter();


    }

    public void loadUserTable(ArrayList<Object[]> userList){
        this.col_user = new Object[]{"User ID", "Username", "User Password", "User Role"};
        if(userList == null){
            userList = this.userManager.getForTable(this.col_user.length, this.userManager.findAll());
        }
//        if(userList == null){
//            userList = this.userManager.getForTable(this.col_user.length, this.userManager.findAllUsersByRole("EMPLOYEE"));
//        }

        createTable(this.tmdl_user, this.tbl_user, col_user, userList);
    }
    public void loadUserComponent() {

        this.user_menu = new JPopupMenu();
        this.tableRowSelect(this.tbl_user, user_menu);

        this.btn_add_admin.addActionListener(e -> {
            UserView userView = new UserView(new User());
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });

        this.user_menu.add("Update").addActionListener(e -> {

            int selectedUserId = this.getTableSelectedRow(tbl_user, 0);
            UserView userView = new UserView(this.userManager.getById(selectedUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                    loadUserComponent();
                }
            });
        });

        this.user_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectedUserId = this.getTableSelectedRow(tbl_user, 0);
                if (this.userManager.delete(selectedUserId)) {
                    Helper.showMsg("done");
                    loadUserTable(null);
                    loadUserComponent();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_user.setComponentPopupMenu(user_menu);

        this.btn_search_admin.addActionListener(e -> {
            ArrayList<User> userList = this.userManager.filter_role(
                    (UserRole) this.cmb_filter_user_role.getSelectedItem()
            );
            ArrayList<Object[]> filteredUsers = this.userManager.getForTable(this.col_user.length, userList);
            loadUserTable(filteredUsers);
        });

        this.btn_clear_admin.addActionListener(e -> {
            this.cmb_filter_user_role.setSelectedItem(null);

            loadUserTable(null);
            loadUserComponent();
        });
        this.btn_logout_admin.addActionListener(e -> {
            LoginView loginView = new LoginView();
            dispose();
        });
    }
    public void loadUserFilter() {
        this.cmb_filter_user_role.setModel(new DefaultComboBoxModel<>(UserRole.values()));
        this.cmb_filter_user_role.setSelectedItem(null);
    }


}

