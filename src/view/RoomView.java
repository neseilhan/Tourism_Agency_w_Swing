package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class RoomView extends Layout{
    private JPanel container;
    private JPanel pnl_features;
    private JLabel lbl_room_pension;
    private JComboBox cmb_room_pension;
    private JCheckBox chk_tv;
    private JCheckBox chk_minibar;
    private JCheckBox chk_console;
    private JCheckBox chk_safe;
    private JCheckBox chk_projection;
    private JPanel pnl_button;
    private JButton btn_room_save;
    private JPanel pnl_pricing;
    private JLabel lbl_room_price_adult;
    private JTextField fld_room_price_adult;
    private JLabel lbl_room_price_child;
    private JTextField fld_room_price_child;
    private JPanel pnl_room_info;
    private JLabel lbl_hotel;
    private JLabel lbl_room_type;
    private JComboBox cmb_hotel;
    private JComboBox cmb_room_type;
    private JLabel lbl_bed_count;
    private JTextField fld_bed_count;
    private JLabel lbl_room_area;
    private JTextField fld_room_area;
    private JLabel lbl_room_stock;
    private JTextField fld_room_stock;
    private JLabel lbl_room_season;
    private JComboBox cmb_room_season;
    private JLabel lbl_room;

    private final Room room;
    private final RoomManager roomManager;
    private final HotelManager hotelManager;
    private final PensionManager pensionManager;
    private final SeasonManager seasonManager;

    private final ArrayList<String> roomFeaturesList = new ArrayList<>();

    public RoomView(Room room) {
        this.add(container);
        this.guiInitialize(400, 450);
        this.room = room;
        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();

        this.loadComboBox();

        if (this.room.getRoom_hotelId() != 0) {
            ComboItem defaultHotel = new ComboItem(this.room.getRoom_hotelId(), this.room.getHotel().getHotel_name());
            this.cmb_hotel.getModel().setSelectedItem(defaultHotel);
            this.cmb_hotel.setEnabled(false);
            loadPensionComboBox();
            loadSeasonComboBox();
        }

        this.cmb_hotel.addActionListener(e -> {
            loadPensionComboBox();
            loadSeasonComboBox();
        });

        this.btn_room_save.addActionListener(e -> {

            if (Helper.isFieldListEmpty(new JTextField[] {this.fld_bed_count, this.fld_room_area, this.fld_room_stock,
                    this.fld_room_price_adult, this.fld_room_price_child}) ||
                    Helper.isComboBoxListEmpty(new JComboBox[]{this.cmb_room_pension, this.cmb_hotel, this.cmb_room_type})) {
                Helper.showMsg("fill");
            } else {
                // Set room values from fields
                this.room.setRoom_size(Integer.parseInt(fld_room_area.getText()));
                this.room.setRoom_stock(Integer.parseInt(fld_room_stock.getText()));
                this.room.setRoom_bed(Integer.parseInt(fld_bed_count.getText()));
                this.room.setRoomPriceAdult(fld_room_price_adult.getText());
                this.room.setRoomPriceChild(fld_room_price_child.getText());


                this.room.setRoom_minibar(this.chk_minibar.isSelected());
                this.room.setRoom_tv(this.chk_tv.isSelected());
                this.room.setRoom_projection(this.chk_projection.isSelected());
                this.room.setRoom_game_console(this.chk_console.isSelected());
                this.room.setRoom_safebox(this.chk_safe.isSelected());


                this.room.setRoomType(Room.RoomType.valueOf(cmb_room_type.getSelectedItem().toString()));
                ComboItem selectSeason = (ComboItem) cmb_room_season.getSelectedItem();
                this.room.setSeasonId(selectSeason.getKey());
                this.room.setSeason(this.seasonManager.getById(selectSeason.getKey()));
                ComboItem selectPension = (ComboItem) cmb_room_pension.getSelectedItem();
                this.room.setPensionId(selectPension.getKey());
                this.room.setPension(this.pensionManager.getById(selectPension.getKey()));
                ComboItem selectHotel = (ComboItem) cmb_hotel.getSelectedItem();
                this.room.setRoom_hotelId(selectHotel.getKey());
                this.room.setHotel(this.hotelManager.getById(selectHotel.getKey()));

                // Check if the save is successful
                boolean result = this.roomManager.save(this.room);

                // Show message according to save result
                if (result) {
                    Helper.showMsg("Room is saved successfully");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }

    public void loadComboBox() {
        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_hotel.addItem(new ComboItem(hotel.getId(),hotel.getHotel_name()));
        }
        this.cmb_hotel.setSelectedItem(null);
        this.cmb_room_type.setModel(new DefaultComboBoxModel<>(Room.RoomType.values()));
        this.cmb_room_type.setSelectedItem(null);
        this.cmb_room_season.setModel(new DefaultComboBoxModel<>(new Object[]{"Summer", "Winter"}));
        this.cmb_room_season.setSelectedItem(null);
    }

    public void loadSeasonComboBox() {
        int selectedHotelId = ((ComboItem) this.cmb_hotel.getSelectedItem()).getKey();

        if (selectedHotelId != 0) {
            this.cmb_room_season.removeAllItems();
            for (Season season : this.seasonManager.getByListHotelId(selectedHotelId)) {
                cmb_room_season.addItem(season.getComboItem());
            }
        }
    }

    public void loadPensionComboBox() {
        int selectedHotelId = ((ComboItem) this.cmb_hotel.getSelectedItem()).getKey();

        if (selectedHotelId != 0) {
            this.cmb_room_pension.removeAllItems();
            for (Pension pension : this.pensionManager.getByListHotelId(selectedHotelId)) {
                cmb_room_pension.addItem(pension.getComboItem());
            }
        }

        this.cmb_room_pension.setSelectedItem(null);
    }

}


