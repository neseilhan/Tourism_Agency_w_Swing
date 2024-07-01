package view;

import business.HotelManager;
import business.PensionManager;
import business.SeasonManager;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Season;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
    private JRadioButton ultraAllInclusiveRadiobutton;
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
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private Hotel hotel;

    private final ArrayList<String> hotelPensionList = new ArrayList<>();
    private final ArrayList<String> hotelSeasonList = new ArrayList<>();

    public HotelView(Hotel hotel) {
        this.hotel = hotel;
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.loadPensionCheckBoxListener();
        this.loadSeasonCheckBoxListener();
        this.add(container);
        this.guiInitialize(500, 500);
        populateHotelDetails();

        this.cmb_hotel_star.setModel(new DefaultComboBoxModel<>());
        String[] stars = {"1", "2", "3", "4", "5"};

//        for (int i = 0; i < stars.length; i++) {
//            cmb_hotel_star.addItem(stars[i]);
//        }
        for (String star : stars) {
            cmb_hotel_star.addItem(star);
        }



        this.btn_save_hotel.addActionListener(e -> {

            // Pension türlerini kontrol etmek için
            boolean isPensionEmpty = true;
            if (ultraAllInclusiveRadiobutton.isSelected() ||
                    allInclusiveRadioButton.isSelected() ||
                    roomBreakfastRadioButton.isSelected() ||
                    fullPensionRadioButton.isSelected() ||
                    halfPensionRadioButton.isSelected() ||
                    justBedRadioButton.isSelected() ||
                    excludingAlcoholFullCreditRadioButton.isSelected()) {
                isPensionEmpty = false;
            }
            // Season seçimlerini kontrol etmek için
            boolean isSeasonEmpty = true;
            if (summerRadioButton.isSelected() || winterRadioButton.isSelected()) {
                isSeasonEmpty = false;
            }

            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_hotel_name, this.fld_hotel_city, this.fld_hotel_region, this.fld_hotel_mail }) ||
                    Helper.isComboBoxListEmpty(new JComboBox[]{this.cmb_hotel_star}) ||
                    isPensionEmpty || isSeasonEmpty) {
                Helper.showMsg("Fill all required fields.");
            } else {
                boolean result = false;

                // Update hotel object with UI inputs
                this.hotel.setHotel_name(fld_hotel_name.getText());
                this.hotel.setHotel_city(fld_hotel_city.getText());
                this.hotel.setHotel_region(fld_hotel_region.getText());
                this.hotel.setHotel_address(fld_hotel_address.getText());
                this.hotel.setHotel_phone(fld_hotel_mail.getText());
                this.hotel.setHotel_star(cmb_hotel_star.getSelectedItem().toString());
                this.hotel.setHotel_spa(this.rdo_spa.isSelected());
                this.hotel.setHotel_concierge(this.rdo_concierge.isSelected());
                this.hotel.setHotel_wifi(this.rdo_wifi.isSelected());
                this.hotel.setHotel_pool(this.rdo_pool.isSelected());
                this.hotel.setHotel_room_service(this.rdo_roomservice.isSelected());
                this.hotel.setHotel_fitness(this.rdo_fitness.isSelected());
                this.hotel.setHotel_carpark(this.rdo_carpark.isSelected());

                int hotelId = this.hotel.getId();

                // Determine if the hotel already exists (has an ID) or is new (ID is 0)
                if (hotelId == 0) {
                    // Save new hotel
                    hotelId = this.hotelManager.saveAndGetHotelId(this.hotel);

                    if (hotelId != 0) {
//                        // Add Pension and Season for new hotel
                        savePension(hotelId);
                        saveSeason(hotelId);
                        result = true;
                    }
                } else {
                    // Update existing hotel
                    result = this.hotelManager.update(this.hotel);

                    if (result) {
                        // Update Pension and Season if needed
//                        savePension(hotelId);
//                        saveSeason(hotelId);
                    }
                }

                if (result) {
                    Helper.showMsg("Hotel information saved successfully.");
                    dispose(); // Close the window after successful save or update
                } else {
                    Helper.showMsg("Error occurred while saving hotel information.");
                }
            }
        });

    }

    private void populateHotelDetails() { //To fill the fields for hotel update screen.
        this.fld_hotel_name.setText(this.hotel.getHotel_name());
        this.fld_hotel_city.setText(this.hotel.getHotel_city());
        this.fld_hotel_region.setText(this.hotel.getHotel_region());
        this.fld_hotel_address.setText(this.hotel.getHotel_address());
        this.fld_hotel_mail.setText(this.hotel.getHotel_phone());
        this.cmb_hotel_star.setSelectedItem(this.hotel.getHotel_star());
        this.rdo_spa.setSelected(this.hotel.isHotel_spa());
        this.rdo_concierge.setSelected(this.hotel.isHotel_concierge());
        this.rdo_wifi.setSelected(this.hotel.isHotel_wifi());
        this.rdo_pool.setSelected(this.hotel.isHotel_pool());
        this.rdo_roomservice.setSelected(this.hotel.isHotel_room_service());
        this.rdo_fitness.setSelected(this.hotel.isHotel_fitness());
        this.rdo_carpark.setSelected(this.hotel.isHotel_carpark());
        // this.ultraAllInclusiveRadiobutton.setSelected();

            // Set pension fields
        ArrayList<Pension> pensions = this.pensionManager.getByHotelId(this.hotel.getId());
        for (Pension pension : pensions) {
            switch (pension.getPensionType()) {
                case "Ultra All Inclusive":
                    this.ultraAllInclusiveRadiobutton.setSelected(true);
                    break;
                case "All Inclusive":
                    this.allInclusiveRadioButton.setSelected(true);
                    break;
                case "Room Breakfast":
                    this.roomBreakfastRadioButton.setSelected(true);
                    break;
                case "Full Pension":
                    this.fullPensionRadioButton.setSelected(true);
                    break;
                case "Half Pension":
                    this.halfPensionRadioButton.setSelected(true);
                    break;
                case "Just Bed":
                    this.justBedRadioButton.setSelected(true);
                    break;
                case "Excluding Alcohol Full Credit":
                    this.excludingAlcoholFullCreditRadioButton.setSelected(true);
                    break;
                // Add cases for other pension types if needed
            }
        }

//        // Populate season checkboxes based on existing data
        ArrayList<Season> seasons = this.seasonManager.getByHotelId(this.hotel.getId());
        for (Season season : seasons) {
            switch (season.getSeasonName()) {
                case "Winter":
                    winterRadioButton.setSelected(true);
                    break;
                case "Summer":
                    summerRadioButton.setSelected(true);
                    break;
                default:
                    break;
            }
        }
    }
        public void savePension ( int hotelId){
            for (String pensionType : hotelPensionList) {
                Pension pension = new Pension();
                pension.setPensionType(pensionType);
                pension.setHotelId(hotelId);
                this.pensionManager.save(pension);
            }
        }


        public void saveSeason ( int hotelId){
            ArrayList<Season> seasonList = new ArrayList<>();
            for (String seasonName : hotelSeasonList) {
                Season season = new Season();
                if (seasonName.equals("Winter")) {
                    season.setSeasonName(seasonName);
                    season.setSeasonStartDate(LocalDate.parse("01.10.2024", DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                    season.setSeasonEndDate(LocalDate.parse("30.03.2025", DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                    season.setHotelId(hotelId);
                } else if (seasonName.equals("Summer")) {
                    season.setSeasonName(seasonName);
                    season.setSeasonStartDate(LocalDate.parse("01.04.2024", DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                    season.setSeasonEndDate(LocalDate.parse("30.09.2024", DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                    season.setHotelId(hotelId);
                }
                this.seasonManager.save(season);
                seasonList.add(season);
            }

        }

    public void loadPensionCheckBoxListener() {
        this.ultraAllInclusiveRadiobutton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    hotelPensionList.add(ultraAllInclusiveRadiobutton.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    hotelPensionList.remove(ultraAllInclusiveRadiobutton.getText());
                }
            }
        });
        this.allInclusiveRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    hotelPensionList.add(allInclusiveRadioButton.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    hotelPensionList.remove(allInclusiveRadioButton.getText());
                }
            }
        });
        this.roomBreakfastRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    hotelPensionList.add(roomBreakfastRadioButton.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    hotelPensionList.remove(roomBreakfastRadioButton.getText());
                }
            }
        });
        this.fullPensionRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    hotelPensionList.add(fullPensionRadioButton.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    hotelPensionList.remove(fullPensionRadioButton.getText());
                }
            }
        });
        this.halfPensionRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    hotelPensionList.add(halfPensionRadioButton.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    hotelPensionList.remove(halfPensionRadioButton.getText());
                }
            }
        });
        this.justBedRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    hotelPensionList.add(justBedRadioButton.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    hotelPensionList.remove(justBedRadioButton.getText());
                }
            }
        });
        this.excludingAlcoholFullCreditRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    hotelPensionList.add(excludingAlcoholFullCreditRadioButton.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    hotelPensionList.remove(excludingAlcoholFullCreditRadioButton.getText());
                }
            }
        });

    }

    public void loadSeasonCheckBoxListener() {
        this.winterRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    hotelSeasonList.add(winterRadioButton.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    hotelSeasonList.remove(winterRadioButton.getText());
                }
            }
        });

        this.summerRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    hotelSeasonList.add(summerRadioButton.getText());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    hotelSeasonList.remove(summerRadioButton.getText());
                }
            }
        });
    }
    }

