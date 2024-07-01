package view;

import business.*;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Reservation;
import entity.Room;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EmployeeView extends Layout {

    private User user;
    private Reservation reservation;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private ReservationManager reservationManager;
    private Hotel hotel;
    private JPopupMenu hotel_menu = new JPopupMenu();
    private JPopupMenu reservation_menu = new JPopupMenu();
    private Object[] col_hotel;
    private Object[] col_room;
    private DefaultTableModel tmdl_hotel_season = new DefaultTableModel();
    private DefaultTableModel tmdl_hotel_pension = new DefaultTableModel();
    private DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();


    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout_employee;
    private JTable tbl_hotel;
    private JButton btn_hotel_add;
    private JPanel pnl_hotel_bot;
    private JPanel pnl_hotel_pension;
    private JTable tbl_hotel_pension;
    private JTable tbl_hotel_season;
    private JTable tbl_reservation;
    private JScrollPane scrl_hotel_season;
    private JPanel pnl_welcome;
    private JButton btn_res_add;
    private JPanel pnl_tab;
    private JTabbedPane tab_menu;
    private JPanel pnl_hotel;
    private JScrollPane scrl_hotel;
    private JPanel pnl_hotel_button;
    private JScrollPane scrl_hotel_pension;
    private JPanel pnl_hotel_season;
    private JPanel pnl_room;
    private JScrollPane scrl_room;
    private JTable tbl_room;
    private JPanel pnl_filter_room;
    private JLabel lbl_filter_hotel;
    private JComboBox cmb_filter_hotel;
    private JLabel lbl_filter_city;
    private JComboBox cmb_filter_city;
    private JLabel lbl_filter_checkin;
    private JTextField fld_filter_checkin;
    private JLabel lbl_filter_checkout;
    private JTextField fld_filter_checkout;
    private JButton btn_reset;
    private JComboBox cmb_filter_region;
    private JButton btn_search;
    private JTextField fld_filter_bed;
    private JLabel lbl_filter_bed;
    private JButton btn_room_add;
    private JPanel pnl_reserevation;
    private JScrollPane scrl_reservations;
    private JPanel pnl_res_button;


    public EmployeeView(User user){
        this.add(container);
        this.guiInitialize(1000, 500);
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();

        this.lbl_welcome.setText("Welcome " + user.getUsername());
        loadComponent();
        loadHotelTable();
        loadHotelComponent();
        loadRoomTable(null);
        loadReservationTable();
        loadReservationComponent();
      //  loadRoomComponent();

        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_filter_hotel.addItem(new ComboItem(hotel.getId(), hotel.getHotel_name()));
        }
        this.cmb_filter_hotel.setSelectedItem(null);

        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_filter_city.addItem(new ComboItem(hotel.getId(), hotel.getHotel_city()));
        }
        this.cmb_filter_city.setSelectedItem(null);

    }

    public void loadReservationTable(){
        Object[] col_res = {"Reservation ID", "Room Id", "Name", "Mail", "Phone", "TC/Passport No", "Start Date", "End Date", "Total Price" , "Reservation Note"};
        ArrayList<Object[]> reservationList = this.reservationManager.getForTable(col_res.length, this.reservationManager.findAll());
        this.createTable(this.tmdl_reservation, this.tbl_reservation, col_res, reservationList);
        tbl_reservation.setModel(tmdl_reservation);
        tbl_reservation.setEnabled(true);

    }
    public void loadReservationComponent(){

//        this.tableRowSelect(this.tbl_reservation, reservation_menu); //when its enable the row cannot being choose
        this.reservation_menu = new JPopupMenu();
        this.reservation_menu.add("Update").addActionListener(e ->{
            int selectReservationId = this.getTableSelectedRow(this.tbl_reservation, 0);
            ReservationView reservationView = new ReservationView(this.reservationManager.getById(selectReservationId));
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) { //Tables that need to be updated dynamically
                    loadReservationTable();
                }
            });
        });

        this.reservation_menu.add("Delete").addActionListener(e -> {
            int selectReservationId = this.getTableSelectedRow(this.tbl_reservation, 0);
            Room room = this.roomManager.getById(this.reservationManager.getById(selectReservationId).getRoomId());
            int roomStock = room.getRoom_stock();

            if (Helper.confirm("sure")) {
                if (this.reservationManager.delete(selectReservationId)) {
                    roomStock += 1;
                    this.roomManager.updateRoomStock(roomStock, room);

                    Helper.showMsg("Reservation deleted successfully.");
                    loadReservationTable();
                    loadRoomTable(null);
                }
            }
        });
        this.tbl_reservation.setComponentPopupMenu(reservation_menu);
    }


    private void loadComponent() {
        this.selectHotelRow(this.tbl_hotel);
        this.selectRow(this.tbl_room);
        this.selectRow(this.tbl_reservation);

        this.btn_logout_employee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView  loginView = new LoginView();
            }
        });
        this.btn_room_add.addActionListener(e -> {
            RoomView roomView = new RoomView(new Room());
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                }
            });
        });
        this.btn_search.addActionListener(e -> {
            String selectedCity = null;
            String selectedHotel = null;
            String filterStartDate = null;
            String filterEndDate = null;
            int filterBed = 0;

            if (!this.fld_filter_bed.getText().isEmpty() && this.fld_filter_bed.getText() != null) {
                filterBed = Integer.parseInt(this.fld_filter_bed.getText());
            }
            if (this.cmb_filter_city.getSelectedItem() != null) {
                selectedCity = ((ComboItem)this.cmb_filter_city.getSelectedItem()).getValue();
            }
            if (this.cmb_filter_hotel.getSelectedItem() != null) {
                selectedHotel = ((ComboItem)this.cmb_filter_hotel.getSelectedItem()).getValue();
            }

            if (this.fld_filter_checkin != null && !this.fld_filter_checkin.getText().isEmpty()){
                if (Helper.isValidDate(fld_filter_checkin.getText(), ("dd.MM.yyyy"))) {
                    filterStartDate = fld_filter_checkin.getText();
                }
            }
            if (this.fld_filter_checkout != null && !this.fld_filter_checkout.getText().isEmpty()){
                if (Helper.isValidDate(fld_filter_checkout.getText(), ("dd.MM.yyyy"))) {
                    filterEndDate = fld_filter_checkout.getText();
                }
            }

            ArrayList<Room> roomListBySearch = this.roomManager.searchForRooms(filterStartDate, filterEndDate, selectedCity, selectedHotel, filterBed);
            ArrayList<Object[]> roomRowListBySearch = this.roomManager.getForTable(this.col_room.length, roomListBySearch);
            loadRoomTable(roomRowListBySearch);
        });
        this.btn_res_add.addActionListener(e -> {
            if (this.fld_filter_checkin.getText().isEmpty() || this.fld_filter_checkout.getText().isEmpty()) {
                Helper.showMsg("Please fill Check-in and Check-out date");
            } else {
                int selectRoomId = this.getTableSelectedRow(this.tbl_room, 0);
                Reservation reservation = new Reservation();
                reservation.setRoomId(selectRoomId);


                if (Helper.isValidDate(fld_filter_checkin.getText(), ("dd.MM.yyyy"))) {
                    reservation.setCheckInDate(LocalDate.parse(fld_filter_checkin.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                }


                if (Helper.isValidDate(fld_filter_checkout.getText(), ("dd.MM.yyyy"))) {
                    reservation.setCheckOutDate(LocalDate.parse(fld_filter_checkout.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                }

                ReservationView reservationView = new ReservationView(reservation);
                reservationView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadReservationTable();
                        loadRoomTable(null);
                    }
                });
            }
        });

        this.btn_reset.addActionListener(e -> {
            this.cmb_filter_hotel.setSelectedItem(null);
            this.cmb_filter_city.setSelectedItem(null);
            this.fld_filter_checkin.setText(null);
            this.fld_filter_checkout.setText(null);
            this.fld_filter_bed.setText(null);
            loadRoomTable(null);
        });

    }

    private void loadRoomTable(ArrayList<Object[]> roomList){

        col_room = new Object[]{"ID", "Hotel", "Pension", "Type", "Stock", "Season",
                    "Adult Price", "Child Price", "Bed Capacity", "Room Size", "Television", "Minibar","Game Console","SafeBox","Projection"};
            if (roomList == null) {
                roomList = this.roomManager.getForTable(col_room.length, this.roomManager.findAll());
            }
            this.createTable(this.tmdl_room, this.tbl_room, col_room, roomList);
        tbl_room.setModel(tmdl_room);
        tbl_room.setEnabled(true);

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
            Hotel selectedHotel = this.hotelManager.getById(selectHotelId);
            HotelView hotelView = new HotelView(selectedHotel);

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

        this.btn_room_add.addActionListener(e -> {
            RoomView roomView = new RoomView(new Room());
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                }
            });
        });


    }
//    public void RoomComponent(){
//
//    }

    private void loadHotelTable()  {
        Object[] col_hotel  ={"ID", "Name", "City", "Region", "Address", "Phone", "Star",
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