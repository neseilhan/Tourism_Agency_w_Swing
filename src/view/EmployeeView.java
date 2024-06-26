package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class EmployeeView extends Layout {

    private User user;
    private HotelManager hotelManager;
    private Hotel hotel;
    private JPopupMenu hotel_menu = new JPopupMenu();
    private Object[] col_hotel;
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
    private JPanel pnl_hotel_info;
    private JPanel pnl_hotel_features;
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


    public EmployeeView(User user){
        this.add(container);
        this.guiInitialize(1000, 500);
        this.hotelManager = new HotelManager();

        this.lbl_welcome.setText("Welcome " + user.getUsername());
        loadComponent();
        loadHotelTable(null);
        loadHotelComponent();
    }

    private void loadComponent() {
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
                    loadHotelTable(null);
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
                    loadHotelTable(null);
                }
            });
        });

        this.hotel_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectedModelId = this.getTableSelectedRow(tbl_hotel, 0);
                if (this.hotelManager.delete(selectedModelId)) {
                    Helper.showMsg("done");
                    loadHotelTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_hotel.setComponentPopupMenu(hotel_menu);

    }

    private void loadHotelTable(ArrayList<Object[]> hotelList)  {
//        Object[] column = {"ID", "Name", "City", "Region", "Address", "Mail", "Star",
//                "Car Park", "Spa", "Room Service", "Pool", "Wifi", "Fitness","Concierge"};
//        if(hotelList == null){
//            hotelList = this.hotelManager.findAll();
//        }
//        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_hotel.getModel();
//        clearModel.setRowCount(0);
//        this.tmdl_hotel.setColumnIdentifiers(column);
        col_hotel = new Object[]{"ID", "Name", "City", "Region", "Address", "Mail", "Star",
               "Car Park", "Spa", "Room Service", "Pool", "Wifi", "Fitness","Concierge"};
        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(this.col_hotel.length, this.hotelManager.findAll());
        }
        this.createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);

//        for (Hotel hotel : hotelList){
//            Object[] obj = {hotel.getId(), hotel.getHotel_name(), hotel.getHotel_city(),hotel.getHotel_region(),
//                    hotel.getHotel_address(), hotel.getHotel_mail(), hotel.getHotel_star(), Helper.changeBoolean(hotel.isHotel_carpark()) ,Helper.changeBoolean(hotel.isHotel_spa()) ,Helper.changeBoolean(hotel.isHotel_room_service()) ,
//                    Helper.changeBoolean(hotel.isHotel_pool()) ,Helper.changeBoolean(hotel.isHotel_wifi()) ,Helper.changeBoolean(hotel.isHotel_fitness()) ,Helper.changeBoolean(hotel.isHotel_concierge()) };
//            this.tmdl_hotel.addRow(obj);
//            tbl_hotel.setModel(tmdl_hotel);
//            tbl_hotel.getTableHeader().setReorderingAllowed(false);
//            tbl_hotel.getColumnModel().getColumn(0).setMaxWidth(50);
            tbl_hotel.setEnabled(true);
//        }


    }
}
