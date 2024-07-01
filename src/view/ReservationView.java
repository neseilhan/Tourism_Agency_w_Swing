package view;

import business.HotelManager;
import business.PensionManager;
import business.ReservationManager;
import business.RoomManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ReservationView extends Layout {
    private JPanel container;
    private JPanel pnl_hotel_info;
    private JLabel lbl_reservation;
    private JLabel lbl_hotel;
    private JTextField fld_hotel;
    private JLabel lbl_pension;
    private JTextField fld_pension_type;
    private JLabel lbl_hotel_features;
    private JLabel lbl_room_features;
    private JLabel lbl_room_type;
    private JTextField fld_room_type;
    private JTextArea txta_room_features;
    private JTextArea txta_hotel_features;
    private JPanel pnl_res_info;
    private JLabel lbl_adult;
    private JComboBox cmb_adult;
    private JLabel lbl_name;
    private JTextField fld_name;
    private JLabel lbl_email;
    private JTextField fld_email;
    private JLabel lbl_phone;
    private JTextField fld_phone;
    private JLabel lbl_res_note;
    private JTextArea txta_res_note;
    private JLabel lbl_tc;
    private JTextField fld_tc;
    private JLabel lbl_child;
    private JComboBox cmb_child;
    private JLabel lbl_checkin;
    private JTextField fld_checkin;
    private JLabel lbl_checkout;
    private JTextField fld_checkout;
    private JLabel lbl_cost;
    private JTextField fld_cost;
    private JPanel pnl_button;
    private JButton btn_save;
    private JButton btn_calculate;
    private JFormattedTextField fld_hotel_name_res;
    private JFormattedTextField fld_room_type_res;
    private JFormattedTextField fld_pension_type_res;
    private JFormattedTextField fld_checkout_res;
    private JFormattedTextField fld_checkin_res;
    private JFormattedTextField fld_total_amount_res;
    private JFormattedTextField fld_bed_capacity_res;
    private JFormattedTextField fld_square_meter_res;
    private JRadioButton rdo_tv_res;
    private JRadioButton rdo_gameconsole_res;
    private JRadioButton rdo_projection_res;
    private JRadioButton rdo_safebox_res;
    private JRadioButton rdo_carpark;
    private JRadioButton rdo_wifi;
    private JRadioButton rdo_pool;
    private JRadioButton rdo_fitness;
    private JRadioButton rdo_concierge;
    private JRadioButton rdo_spa;
    private JRadioButton rdo_room_service;
    private JFormattedTextField fld_city_res;
    private JFormattedTextField fld_star_res;
    private JFormattedTextField fld_guest_count_res;
    private JFormattedTextField fld_guest_name_res;
    private JFormattedTextField fld_citizen_id_res;
    private JFormattedTextField fld_email_res;
    private JFormattedTextField fld_phone_res;
    private JButton btn_addreservation_res;

    private DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private Hotel hotel;
    private Room room;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private PensionManager pensionManager;
    private Reservation reservation;
    private ReservationManager reservationManager;
    private EmployeeView employeeView;

    public ReservationView(Reservation reservation){

        this.add(this.container);
        this.guiInitialize(800,500);
        this.reservation = reservation;
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();
        this.hotelManager = new HotelManager();

        if (this.reservation.getRoomId() != 0) {
            setHotelValues(this.reservation);
            loadRoomComboBox(this.reservation);
            if (this.reservation.getId() != 0) {
                setReservationValues(this.reservation);
            }
        }

        this.btn_calculate.addActionListener(e -> {
            if (Helper.isComboBoxListEmpty(new JComboBox[]{this.cmb_adult, this.cmb_child})) {
                Helper.showMsg("Please add applicable guest count");
            } else {
                if (checkGuestCount()) {
                    ComboItem selectAdult = (ComboItem) this.cmb_adult.getSelectedItem();
                    ComboItem selectChild = (ComboItem) this.cmb_child.getSelectedItem();

                    int adultCount = Integer.parseInt(selectAdult.getValue());
                    int childCount = Integer.parseInt(selectChild.getValue());

                    int adultPricePerDay = Integer.parseInt(this.roomManager.getById(this.reservation.getRoomId()).getRoomPriceAdult()) * adultCount;
                    int childPricePerDay = Integer.parseInt(this.roomManager.getById(this.reservation.getRoomId()).getRoomPriceChild()) * childCount;

                    int totalPrice = (adultPricePerDay + childPricePerDay) * calculateDays();

                    this.fld_cost.setText(String.valueOf(totalPrice));
                }
            }
        });

        this.btn_save.addActionListener(e -> {

            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_name, this.fld_email, this.fld_phone,
                    this.fld_checkin, this.fld_checkout, this.fld_tc}) ||
                    Helper.isComboBoxListEmpty(new JComboBox[]{this.cmb_adult, this.cmb_child})) {
                Helper.showMsg("fill");
            } else {
                if (Helper.isFieldEmpty(this.fld_cost)) {
                    Helper.showMsg("Please calculate your total cost");
                } else {
                    boolean result;

                    if (checkGuestCount()) {


                        this.reservation.setGuestName(fld_name.getText());
                        this.reservation.setGuestMail(fld_email.getText());
                        this.reservation.setGuestPhone(fld_phone.getText());
                        this.reservation.setGuestCitizenId(fld_tc.getText());
                        this.reservation.setCheckInDate(LocalDate.parse(fld_checkin.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                        this.reservation.setCheckOutDate(LocalDate.parse(fld_checkout.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                        this.reservation.setTotalPrice(fld_cost.getText());
                        this.reservation.setReservationNote(txta_res_note.getText());


                        if (this.reservation.getId() != 0) { // Update
                            result = this.reservationManager.update(this.reservation);
                        } else {
                            result = this.reservationManager.save(this.reservation);

                            if (result) {
                                int roomStock = this.roomManager.getById(this.reservation.getRoomId()).getRoom_stock();
                                roomStock -= 1;
                                this.roomManager.updateRoomStock(roomStock, this.roomManager.getById(this.reservation.getRoomId()));
                            }
                        }

                        if (result) {
                            Helper.showMsg("done");
                            dispose();
                        } else {
                            Helper.showMsg("error");
                        }
                    }
                }
            }
        });

    }


    public boolean checkGuestCount() {
        boolean result = true;
        ComboItem selectAdult = (ComboItem) this.cmb_adult.getSelectedItem();
        ComboItem selectChild = (ComboItem) this.cmb_child.getSelectedItem();

        int guestCount = Integer.parseInt(selectAdult.getValue()) + Integer.parseInt(selectChild.getValue());

        if (guestCount > this.roomManager.getById(this.reservation.getRoomId()).getRoom_bed()) {
            Helper.showMsg("Not enough bed for the guests");
            result = false;
            this.cmb_adult.setSelectedItem(null);
            this.cmb_child.setSelectedItem(null);
        }
        return result;
    }
    public int calculateDays() {
        LocalDate reservationStartDate = LocalDate.parse(fld_checkin.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate reservationEndDate = LocalDate.parse(fld_checkout.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return (int) ChronoUnit.DAYS.between(reservationStartDate, reservationEndDate);
    }
    public void setReservationValues(Reservation reservation) {
        this.fld_name.setText(reservation.getGuestName());
        this.fld_tc.setText(reservation.getGuestCitizenId());
        this.fld_email.setText(reservation.getGuestMail());
        this.fld_phone.setText(reservation.getGuestPhone());
        this.txta_res_note.setText(reservation.getReservationNote());
        this.fld_cost.setText(reservation.getTotalPrice());
    }
    public void loadRoomComboBox(Reservation reservation) {

        int bedCount = this.roomManager.getById(reservation.getRoomId()).getRoom_bed();
        for (int i = 0; i <= bedCount; i++) {
            this.cmb_adult.addItem(new ComboItem(i, String.valueOf(i)));
        }

        for (int i = 0; i < bedCount; i++) {
            this.cmb_child.addItem(new ComboItem(i, String.valueOf(i)));
        }

        this.cmb_adult.setSelectedItem(null);
        this.cmb_child.setSelectedItem(null);
    }

    public void setHotelValues(Reservation reservation) {
        Room room = this.roomManager.getById(reservation.getRoomId());

        this.fld_hotel.setText(room.getHotel().getHotel_name());
        this.fld_pension_type.setText(room.getPension().getPensionType());
        this.fld_room_type.setText(room.getRoomType().toString());
        this.fld_checkin.setText(reservation.getCheckInDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        this.fld_checkout.setText(reservation.getCheckOutDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        // Room features
        boolean isTvAvailable = room.isRoom_tv();
        String tvStatus = Helper.Utils.booleanToYesNoWithPrefix(isTvAvailable, "TV: ");

        boolean isMinibarAvailable = room.isRoom_minibar();
        String minibarStatus = Helper.Utils.booleanToYesNoWithPrefix(isMinibarAvailable, "Minibar: ");

        boolean isGameConsoleAvailable = room.isRoom_game_console();
        String gameConsoleStatus = Helper.Utils.booleanToYesNoWithPrefix(isGameConsoleAvailable, "Game Console: ");

        boolean isSafeboxAvailable = room.isRoom_safebox();
        String safeboxStatus = Helper.Utils.booleanToYesNoWithPrefix(isSafeboxAvailable, "Safebox: ");

        boolean isProjectionAvailable = room.isRoom_projection();
        String projectionStatus = Helper.Utils.booleanToYesNoWithPrefix(isProjectionAvailable, "Projection: ");

        StringBuilder roomFeaturesBuilder = new StringBuilder();
        roomFeaturesBuilder.append(tvStatus).append("\n");
        roomFeaturesBuilder.append(minibarStatus).append("\n");
        roomFeaturesBuilder.append(gameConsoleStatus).append("\n");
        roomFeaturesBuilder.append(safeboxStatus).append("\n");
        roomFeaturesBuilder.append(projectionStatus);

        this.txta_room_features.setText(roomFeaturesBuilder.toString());

        // Hotel features
        Hotel hotel = room.getHotel();
        boolean isCarparkAvailable = hotel.isHotel_carpark();
        String carparkStatus = Helper.Utils.booleanToYesNoWithPrefix(isCarparkAvailable, "Carpark: ");

        boolean isSpaAvailable = hotel.isHotel_spa();
        String spaStatus = Helper.Utils.booleanToYesNoWithPrefix(isSpaAvailable, "Spa: ");

        boolean isPoolAvailable = hotel.isHotel_pool();
        String poolStatus = Helper.Utils.booleanToYesNoWithPrefix(isPoolAvailable, "Pool: ");

        boolean isWifiAvailable = hotel.isHotel_wifi();
        String wifiStatus = Helper.Utils.booleanToYesNoWithPrefix(isWifiAvailable, "Wifi: ");

        boolean isFitnessAvailable = hotel.isHotel_fitness();
        String fitnessStatus = Helper.Utils.booleanToYesNoWithPrefix(isFitnessAvailable, "Fitness: ");

        boolean isConciergeAvailable = hotel.isHotel_concierge();
        String conciergeStatus = Helper.Utils.booleanToYesNoWithPrefix(isConciergeAvailable, "Concierge: ");

        boolean isRoomServiceAvailable = hotel.isHotel_room_service();
        String roomServiceStatus = Helper.Utils.booleanToYesNoWithPrefix(isRoomServiceAvailable, "Room Service: ");

        StringBuilder hotelFeaturesBuilder = new StringBuilder();
        hotelFeaturesBuilder.append(carparkStatus).append("\n");
        hotelFeaturesBuilder.append(spaStatus).append("\n");
        hotelFeaturesBuilder.append(poolStatus).append("\n");
        hotelFeaturesBuilder.append(wifiStatus).append("\n");
        hotelFeaturesBuilder.append(fitnessStatus).append("\n");
        hotelFeaturesBuilder.append(conciergeStatus).append("\n");
        hotelFeaturesBuilder.append(roomServiceStatus);

        this.txta_hotel_features.setText(hotelFeaturesBuilder.toString());

        // Disable text areas and fields
        this.fld_hotel.setEnabled(false);
        this.fld_pension_type.setEnabled(false);
        this.fld_room_type.setEnabled(false);
        this.fld_checkin.setEnabled(false);
        this.fld_checkout.setEnabled(false);
        this.txta_room_features.setEnabled(false);
        this.txta_hotel_features.setEnabled(false);
    }

}
