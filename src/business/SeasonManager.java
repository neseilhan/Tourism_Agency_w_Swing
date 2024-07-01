package business;

import core.Helper;
import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private SeasonDao seasonDao;

    public SeasonManager() {
        this.seasonDao = new SeasonDao();
    }

    public ArrayList<Season> findAll() { return this.seasonDao.findAll(); }

    public ArrayList<Season> getByListHotelId(int hotelId) { return this.seasonDao.getByListHotelId(hotelId); }

    public Season getById(int id) {
        return this.seasonDao.getByID(id);
    }

    public boolean save(Season season) {
        if (season.getSeasonId() != 0) {
            Helper.showMsg("Season couldn't save.");
        }
        return this.seasonDao.save(season);
    }
    public ArrayList<Season> getByHotelId(int hotelId) {
        return seasonDao.getByHotelId(hotelId);
    }



}
