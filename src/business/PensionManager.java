package business;

import core.Helper;
import dao.PensionDao;
import entity.Pension;

import java.util.ArrayList;

public class PensionManager {
    private PensionDao pensionDao;

    public PensionManager() {
        this.pensionDao = new PensionDao();
    }

    public ArrayList<Pension> findAll() {
        return this.pensionDao.findAll();
    }

    public Pension getById(int id) {
        return this.pensionDao.getById(id);
    }

    public ArrayList<Pension> getByListHotelId(int hotelId) {
        return this.pensionDao.getByListHotelId(hotelId);
    }

    public boolean save(Pension pension) {
        if (pension.getPensionId() != 0) {
            Helper.showMsg("pension save error.");
        }
        return this.pensionDao.save(pension);
    }
}