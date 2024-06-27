package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class EmployeeView extends Layout {

    private User user;
    private HotelManager hotelManager;
    private Hotel hotel;
    private JPopupMenu hotel_menu = new JPopupMenu();
    private Object[] col_hotel;
    private DefaultTableModel tmdl_hotel_season = new DefaultTableModel();
    private DefaultTableModel tmdl_hotel_pension = new DefaultTableModel();
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();

    private JPanel container;
    private JPanel pnl_welcome;
    private JLabel lbl_welcome;
    private JButton btn_logout_employee;
    private JPanel pnl_tab;
    private JTabbedPane tab_menu;
    private JPanel pnl_hotel;
    private JScrollPane scrl_hotel;
    private JTable tbl_hotel;
    private JPanel pnl_hotel_button;
    private JButton btn_hotel_add;
    private JPanel pnl_hotel_bot;
    private JPanel pnl_hotel_pension;
    private JPanel pnl_hotel_season;
    private JPanel pnl_room;
    private JScrollPane scrl_room;
    private JTable tbl_room;
    private JButton btn_room_add;
    private JButton btn_room_res_add;
    private JPanel pnl_filter_room;
    private JLabel lbl_filter_hotel;
    private JComboBox cmb_filter_hotel;
    private JLabel lbl_filter_city;
    private JComboBox cmb_filter_city;
    private JLabel lbl_filter_region;
    private JComboBox cmb_filter_region;
    private JLabel lbl_filter_checkin;
    private JTextField fld_filter_checkin;
    private JLabel lbl_filter_checkout;
    private JTextField fld_filter_checkout;
    private JLabel lbl_filter_bed;
    private JTextField fld_filter_bed;
    private JButton btn_reset;
    private JButton btn_search;
    private JPanel pnl_reserevation;
    private JScrollPane scrl_reservations;
    private JTable tbl_reservations;
    private JPanel pnl_res_button;
    private JScrollPane scrl_hotel_pension;
    private JTable tbl_hotel_pension;
    private JTable tbl_hotel_season;
    private JScrollPane scrl_hotel_season;


    public EmployeeView(User user){
        this.add(container);
        this.guiInitialize(1000, 500);
        this.hotelManager = new HotelManager();

        this.lbl_welcome.setText("Welcome " + user.getUsername());
        loadComponent();
        loadHotelTable();
        loadHotelComponent();
    }

    private void loadComponent() {
        this.selectHotelRow(this.tbl_hotel);

        this.btn_logout_employee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView  loginView = new LoginView();
            }
        });
    }
    public void loadHotelComponent(){

        this.btn_hotel_add.addActionListener(e -> {
            HotelView hotelView = new HotelView(new Hotel());
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });
        });


//        this.tableRowSelect(this.tbl_hotel, hotel_menu); //when its enable the row cannot being choose
        this.hotel_menu = new JPopupMenu();
        this.hotel_menu.add("Update").addActionListener(e ->{

            int selectHotelId = this.getTableSelectedRow(tbl_hotel, 0);
            HotelView hotelView = new HotelView(this.hotelManager.getById(selectHotelId));
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) { //Tables that need to be updated dynamically
                    loadHotelTable();
                }
            });
        });

        this.hotel_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectedModelId = this.getTableSelectedRow(tbl_hotel, 0);
                if (this.hotelManager.delete(selectedModelId)) {
                    Helper.showMsg("done");
                    loadHotelTable();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_hotel.setComponentPopupMenu(hotel_menu);

    }

    private void loadHotelTable()  {
        Object[] col_hotel  ={"ID", "Name", "City", "Region", "Address", "Mail", "Star",
               "Car Park", "Spa", "Room Service", "Pool", "Wifi", "Fitness","Concierge"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        this.createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
    }

    public void loadHotelSeasonTable() {
        int selectHotelId = this.getTableSelectedRow(this.tbl_hotel, 0);
        Object[] colHotelSeason = {"Season", "Season Start Date", "Season End Date"};
        ArrayList<Object[]> seasonList = this.hotelManager.getForSeasonTable(colHotelSeason.length, selectHotelId);
        this.createTable(this.tmdl_hotel_season, this.tbl_hotel_season, colHotelSeason, seasonList);
    }

    public void loadHotelPensionTable() {
        int selectHotelId = this.getTableSelectedRow(this.tbl_hotel, 0);
        Object[] colHotelPension = {"Hotel's Pension"};
        ArrayList<Object[]> hotelPensionList = this.hotelManager.getForPensionTable(colHotelPension.length, selectHotelId);
        this.createTable(this.tmdl_hotel_pension, this.tbl_hotel_pension, colHotelPension, hotelPensionList);
    }
    public void selectHotelRow(JTable table) {
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int selected_row = table.rowAtPoint(e.getPoint());
                    table.setRowSelectionInterval(selected_row, selected_row);
                    loadHotelSeasonTable();
                    loadHotelPensionTable();
                    resizeTable(tbl_hotel_pension, 200, 145, 100);
                }
            });
    }
}
