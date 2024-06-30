package business;

import core.Helper;
import dao.ReservationDao;
import entity.Reservation;
import java.util.ArrayList;

public class ReservationManager {
    private final ReservationDao reservationDao;
    public ReservationManager(){
        this.reservationDao = new ReservationDao();
    }
    public ArrayList<Reservation> findAll(){
        return reservationDao.findAll();
    }

    public boolean save(Reservation reservation){
        return this.reservationDao.save(reservation);
    }

    public boolean update(Reservation reservation){
        return this.reservationDao.update(reservation);
    }

    public Reservation getById(int id){
        return this.reservationDao.findById(id);
    }

    public boolean delete(int id){
        if(this.getById(id) == null){
            Helper.showMsg(id + " Reservation ID number could not be found. ");
            return false;
        }
        return this.reservationDao.delete(id);
    }
    public ArrayList<Object[]> getForTable(int colSize, ArrayList<Reservation> reservationList) {
        ArrayList<Object[]> reservationRowList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            Object[] rowObject = new Object[colSize];
            int i = 0;
            rowObject[i++] = reservation.getId();
            rowObject[i++] = reservation.getRoomId();
            rowObject[i++] = reservation.getGuestName();
            rowObject[i++] = reservation.getGuestMail();
            rowObject[i++] = reservation.getGuestPhone();
            rowObject[i++] = reservation.getGuestCitizenId();
            rowObject[i++] = reservation.getCheckInDate();
            rowObject[i++] = reservation.getCheckOutDate();
            rowObject[i++] = reservation.getTotalPrice();
            rowObject[i++] = reservation.getReservationNote();
            reservationRowList.add(rowObject);
        }
        return reservationRowList;
    }
}
