package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;
import entity.Pension;
import entity.Season;

import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;
    private final PensionManager pensionManager;
    private final SeasonManager seasonManager;

    public HotelManager(){
        this.hotelDao = new HotelDao();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
    }
    public ArrayList<Hotel> findAll(){
        return hotelDao.findAll();
    }

    public Hotel getById(int id) { return this.hotelDao.getById(id); }


    public boolean save(Hotel hotel){
        if (this.getById(hotel.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.hotelDao.save(hotel);
    }

    public boolean update(Hotel hotel){
        if(getById(hotel.getId()) == null){
            Helper.showMsg("User with id number " + hotel.getId() + " could not be found.");
            return false;
        }
        return this.hotelDao.update(hotel);
    }
    public boolean delete(int id){
        if(this.getById(id) == null){
            Helper.showMsg("User with id number " + id + " could not be found.");
            return false;
        }
        return this.hotelDao.delete(id);
    }
    public int saveAndGetHotelId(Hotel hotel) {
        if (hotel.getId() != 0) {
            Helper.showMsg("Hotel could not be saved");
        }
        return this.hotelDao.saveAndGetHotelId(hotel);
    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> modelList){ //To create a query in the table and assign a new value.
        ArrayList<Object[]> hotelObjList = new ArrayList<>();
        for (Hotel obj : modelList) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_name();
            rowObject[i++] = obj.getHotel_city();
            rowObject[i++] = obj.getHotel_region();
            rowObject[i++] = obj.getHotel_address();
            rowObject[i++] = obj.getHotel_phone();
            rowObject[i++] = obj.getHotel_star();
            rowObject[i++] = obj.isHotel_carpark();
            rowObject[i++] = obj.isHotel_spa();
            rowObject[i++] = obj.isHotel_room_service();
            rowObject[i++] = obj.isHotel_pool();
            rowObject[i++] = obj.isHotel_wifi();
            rowObject[i++] = obj.isHotel_fitness();
            rowObject[i++] = obj.isHotel_concierge();

            hotelObjList.add(rowObject);
        }
        return hotelObjList;
    }
    public ArrayList<Object[]> getForSeasonTable(int colSize, int selectedHotelId) { //to pull data from the season table
        ArrayList<Object[]> hotelSeasonRowList = new ArrayList<>();
        for (Season season : this.seasonManager.getByListHotelId(selectedHotelId)) {
            Object[] rowObject = new Object[colSize];
            int i = 0;
            rowObject[i++] = season.getSeasonName();
            rowObject[i++] = season.getSeasonStartDate();
            rowObject[i++] = season.getSeasonEndDate();
            hotelSeasonRowList.add(rowObject);
        }
        return hotelSeasonRowList;
    }

    public ArrayList<Object[]> getForPensionTable(int colSize, int selectedHotelId) { //to pull data from the pension table
        ArrayList<Object[]> hotelPensionRowList = new ArrayList<>();
        for (Pension pension : this.pensionManager.getByListHotelId(selectedHotelId)) {
            Object[] rowObject = new Object[colSize];
            int i = 0;
            rowObject[i++] = pension.getPensionType();
            hotelPensionRowList.add(rowObject);
        }
        return hotelPensionRowList;
    }


}
