package business;

import core.Helper;
import dao.PensionDao;
import dao.RoomDao;

import java.sql.Array;
import java.util.ArrayList;

import dao.SeasonDao;
import entity.Room;

public class RoomManager {
    private RoomDao roomDao;
    private SeasonDao seasonDao;
    private PensionDao pensionDao;

    public RoomManager() {
        this.roomDao = new RoomDao();
    }

    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    public ArrayList<Room> getByListHotelId(int hotelId) {
        return this.roomDao.getByListHotelId(hotelId);
    }

    public boolean save(Room room) {
        if (room.getRoom_id() != 0) {
            Helper.showMsg("room could not be saved.");
        }
        return this.roomDao.save(room);
    }

    public Room getById(int id) { return this.roomDao.getById(id); }

    public ArrayList<Room> searchForRooms(String filterStartDate, String filterEndDate, String filterCity, String filterHotel,  int filterBed) {
        return this.roomDao.searchForRooms(filterStartDate, filterEndDate, filterCity, filterHotel, filterBed);
    }


    public ArrayList<Object[]> getForTable(int colSize, ArrayList<Room> roomList) {
        ArrayList<Object[]> roomRowList = new ArrayList<>();
        for (Room room : roomList) {
            Object[] rowObject = new Object[colSize];
            int i = 0;
            rowObject[i++] = room.getRoom_id();
            rowObject[i++] = room.getHotel().getHotel_name();
            rowObject[i++] = room.getPension().getPensionType();
            rowObject[i++] = room.getRoomType();
            rowObject[i++] = room.getRoom_stock();
            rowObject[i++] = room.getSeason().getSeasonName();
            rowObject[i++] = room.getRoomPriceAdult();
            rowObject[i++] = room.getRoomPriceChild();
            rowObject[i++] = room.getRoom_bed();
            rowObject[i++] = room.getRoom_size();
            rowObject[i++] = room.isRoom_tv();
            rowObject[i++] = room.isRoom_minibar();
            rowObject[i++] = room.isRoom_game_console();
            rowObject[i++] = room.isRoom_safebox();
            rowObject[i++] = room.isRoom_projection();

            roomRowList.add(rowObject);
        }
        return roomRowList;
    }

}
