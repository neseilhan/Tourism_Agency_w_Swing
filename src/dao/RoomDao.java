package dao;

import core.Db;
import entity.Hotel;
import entity.Pension;
import entity.Room;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomDao {
    private final Connection con;
    private final HotelDao hotelDao = new HotelDao();
    private final PensionDao pensionDao = new PensionDao();
    private final SeasonDao seasonDao = new SeasonDao();

    private Hotel hotel;
    private Pension pension;

    public RoomDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Room> findAll() {
        return this.selectByQuery("SELECT * FROM public.room ORDER BY room_id ASC");
    }


    public ArrayList<Room> getByListHotelId(int hotelId) {
        return this.selectByQuery("SELECT * FROM public.room WHERE room_hotel_id = " + hotelId);
    }

    public Room getById(int id) {
        Room room = null;
        String query = "SELECT * FROM public.room WHERE room_id = ?";
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                room = this.match(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }


    public Array arrayConversion(ArrayList<String> roomFeaturesList) {
        try {
            return this.con.createArrayOf("text", roomFeaturesList.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean save(Room room) {
        String query = "INSERT INTO public.room " +
                "(" +
                "room_type, " +
                "room_bed, " +
                "room_size, " +
                "room_stock, " +
                "room_adult_prc, " +
                "room_child_prc, " +
                "room_hotel_id, " +
                "room_pension_id, " +
                "room_season_id," +
                "room_tv," +
                "room_projection," +
                "room_safebox," +
                "room_game_console," +
                "room_minibar" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);
            setRoomValues(room, preparedStatement);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public boolean updateRoomStock(int roomStock, Room room) {
        String query = "UPDATE public.room SET room_stock = ? WHERE room_id = ?";
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, roomStock);
            preparedStatement.setInt(2, room.getRoom_id());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public ArrayList<Room> searchForRooms(String filterStartDate, String filterEndDate, String filterCity, String filterHotel, int bedCount) {
        String query = "SELECT * FROM public.room AS R INNER JOIN public.hotel AS H ON R.room_hotel_id = H.hotel_id INNER JOIN public.season AS S ON R.room_season_id = S.season_id";
        ArrayList<String> where = new ArrayList<>();
        String whereStr = null;

        where.add("R.room_stock > 0");

        if (filterCity != null && !filterCity.isEmpty()) {
            where.add("H.hotel_city = '" + filterCity + "'");
        }

        if (filterHotel != null && !filterHotel.isEmpty()) {
            where.add("H.hotel_name = '" + filterHotel + "'");
        }

        if (bedCount > 0) {
            where.add("R.room_bed >= '" + bedCount + "'");
        }

        if (filterStartDate != null && !filterStartDate.isEmpty()) {
            LocalDate startDate = LocalDate.parse(filterStartDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            where.add("S.season_start_date <= '" + startDate + "'");
            where.add("S.season_end_date >= '" + startDate + "'");
        }

        if(filterEndDate != null && !filterEndDate.isEmpty()) {
            LocalDate endDate = LocalDate.parse(filterEndDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            where.add("S.season_end_date >= '" + endDate + "'");
            where.add("S.season_start_date <= '" + endDate + "'");
        }

        if (!where.isEmpty()) {
            whereStr = String.join(" AND ", where);
        }

        if (whereStr != null) {
            query += " WHERE " + whereStr;
        }

        System.out.println(query);
        ArrayList<Room> searchedRoomList = this.selectByQuery(query);

        return searchedRoomList;
    }


    private ArrayList<Room> selectByQuery(String query) {
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet resultSet = this.con.createStatement().executeQuery(query);
            while (resultSet.next()) {
                roomList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    private void setRoomValues(Room room, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, room.getRoomType().toString());
        preparedStatement.setInt(2, room.getRoom_bed());
        preparedStatement.setInt(3, room.getRoom_size());
        preparedStatement.setInt(4, room.getRoom_stock());
        preparedStatement.setString(5, room.getRoomPriceAdult());
        preparedStatement.setString(6, room.getRoomPriceChild());
        preparedStatement.setInt(7, room.getRoom_hotelId());
        preparedStatement.setInt(8, room.getRoom_pensionId());
        preparedStatement.setInt(9, room.getRoom_seasonId());
        preparedStatement.setBoolean(10, room.isRoom_tv());
        preparedStatement.setBoolean(11, room.isRoom_minibar());
        preparedStatement.setBoolean(12, room.isRoom_game_console());
        preparedStatement.setBoolean(13, room.isRoom_safebox());
        preparedStatement.setBoolean(14, room.isRoom_projection());

    }

    private Room match(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setRoom_id(resultSet.getInt("room_id"));
        room.setRoom_bed(resultSet.getInt("room_bed"));
        room.setRoom_size(resultSet.getInt("room_size"));
        room.setRoom_stock(resultSet.getInt("room_stock"));

        room.setHotelId(resultSet.getInt("room_hotel_id"));
        room.setHotel(this.hotelDao.getById(resultSet.getInt("room_hotel_id")));

        room.setRoom_pensionId(resultSet.getInt("room_pension_id"));
        room.setPension(this.pensionDao.getById(resultSet.getInt("room_pension_id")));

        room.setSeasonId(resultSet.getInt("room_season_id"));
        room.setSeason(this.seasonDao.getByID(resultSet.getInt("room_season_id")));

        room.setRoomType(Room.RoomType.valueOf(resultSet.getString("room_type")));
        room.setRoomPriceAdult(resultSet.getString("room_adult_prc"));
        room.setRoomPriceChild(resultSet.getString("room_child_prc"));

        room.setRoom_tv((resultSet.getBoolean("room_tv")));
        room.setRoom_minibar((resultSet.getBoolean("room_minibar")));
        room.setRoom_game_console((resultSet.getBoolean("room_game_console")));
        room.setRoom_safebox((resultSet.getBoolean("room_safebox")));
        room.setRoom_projection((resultSet.getBoolean("room_projection")));

        return room;
    }
}
