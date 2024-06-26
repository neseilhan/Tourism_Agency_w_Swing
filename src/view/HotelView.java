package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;

import javax.swing.*;

public class HotelView extends Layout {
    private JPanel container;
    private JLabel lbl_add_hotel;
    private JPanel pnl_hotel_add;
    private JLabel lbl_hotel_name;
    private JTextField fld_hotel_name;
    private JLabel lbl_city;
    private JTextField fld_hotel_city;
    private JLabel lbl_region;
    private JTextField fld_hotel_region;
    private JLabel lbl_address;
    private JTextField fld_hotel_address;
    private JLabel fld_star;
    private JRadioButton rdo_carpark;
    private JRadioButton rdo_wifi;
    private JRadioButton rdo_pool;
    private JRadioButton rdo_fitness;
    private JRadioButton rdo_concierge;
    private JRadioButton rdo_spa;
    private JRadioButton rdo_roomservice;
    private JComboBox cmb_hotel_star;
    private JButton btn_save_hotel;
    private JRadioButton rdo_;
    private JRadioButton allInclusiveRadioButton;
    private JRadioButton roomBreakfastRadioButton;
    private JRadioButton fullPensionRadioButton;
    private JLabel lbl_pension;
    private JRadioButton summerRadioButton;
    private JRadioButton winterRadioButton;
    private JRadioButton halfPensionRadioButton;
    private JRadioButton justBedRadioButton;
    private JRadioButton excludingAlcoholFullCreditRadioButton;
    private JTextField fld_hotel_mail;
    private HotelManager hotelManager;
    private Hotel hotel;

    public HotelView(Hotel hotel){
        this.hotel = hotel;
        this.hotelManager = new HotelManager();
        this.add(container);
        this.guiInitialize(500,500);

        this.cmb_hotel_star.setModel(new DefaultComboBoxModel<>());
        String[] stars = {"1","2","3","4","5"};

        for (int i = 0; i<stars.length; i++){
            cmb_hotel_star.addItem(stars[i]);
        }

//        if(this.hotel.getId() == 0) {
//            this.lbl_add_hotel.setText("New Hotel Registration");
//        }else {
//        }
//            this.lbl_add_hotel.setText("Edit Hotel");
//            this.fld_hotel_name.setText(hotel.getHotel_name());
//            this.fld_mail.setText(hotel.getHotel_mail());
//            this.fld_address.setText(hotel.getHotel_address());
//            this.cmb_star.setSelectedItem(hotel.getHotel_star());
//            this.rdo_wifi.setSelected(hotel.isHotel_wifi());
//            this.rdo_spa.setSelected(hotel.isHotel_spa());
//            this.rdo_carpark.setSelected(hotel.isHotel_carpark());
//            this.rdo_concierge.setSelected(hotel.isHotel_concierge());
//            this.rdo_fitness.setSelected(hotel.isHotel_fitness());
//            this.rdo_pool.setSelected(hotel.isHotel_pool());
//            this.rdo_roomservice.setSelected(hotel.isHotel_room_service());
//        }

        this.btn_save_hotel.addActionListener(e -> {
            JTextField[] textfCheckList = {fld_hotel_name,fld_hotel_address,fld_hotel_mail};

            if(Helper.isFieldListEmpty(textfCheckList)){
                Helper.showMsg("fill");
        } else {
            boolean result=false;
            this.hotel.setHotel_name(fld_hotel_name.getText());
            this.hotel.setHotel_city(fld_hotel_city.getText());
            this.hotel.setHotel_region(fld_hotel_region.getText());
            this.hotel.setHotel_address(fld_hotel_address.getText());
            this.hotel.setHotel_mail(fld_hotel_mail.getText());
            this.hotel.setHotel_star(cmb_hotel_star.getSelectedItem().toString());
            this.hotel.setHotel_spa(this.rdo_spa.isSelected());
            this.hotel.setHotel_concierge(this.rdo_concierge.isSelected());
            this.hotel.setHotel_wifi(this.rdo_wifi.isSelected());
            this.hotel.setHotel_pool(this.rdo_pool.isSelected());
            this.hotel.setHotel_room_service(this.rdo_roomservice.isSelected());
            this.hotel.setHotel_fitness(this.rdo_fitness.isSelected());
            this.hotel.setHotel_carpark(this.rdo_carpark.isSelected());

            if(this.hotel.getId() == 0){
                result = this.hotelManager.save(this.hotel);
            }else{
                result = this.hotelManager.update(this.hotel);
            }

            if(result){
                Helper.showMsg("done");
                dispose();
            }else{
                Helper.showMsg("error");
            }
        }

        });

        }
    }
