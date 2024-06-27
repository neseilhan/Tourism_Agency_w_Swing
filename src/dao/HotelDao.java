package dao;

import core.Db;
import entity.Hotel;

import java.sql.*;
import java.util.ArrayList;

public class HotelDao {
    private final Connection con;

    public HotelDao(){
        this.con = Db.getInstance();
    }
    public Hotel getById(int id) {
        Hotel hotel = null;
        String query = "SELECT * FROM public.hotel WHERE hotel_id = ?";
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                hotel = this.match(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotel;
    }

    public ArrayList<Hotel> findAll() { //Finds all users without filter.
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while(rs.next()){
                hotelList.add(this.match(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return hotelList;
    }
    public Hotel match(ResultSet rs) throws SQLException{

        Hotel obj = new Hotel();

        obj.setId(rs.getInt("hotel_id"));
        obj.setHotel_name(rs.getString("hotel_name"));
        obj.setHotel_city(rs.getString("hotel_city"));
        obj.setHotel_region((rs.getString("hotel_region")));
        obj.setHotel_address((rs.getString("hotel_address")));
        obj.setHotel_mail((rs.getString("hotel_mail")));
        obj.setHotel_star((rs.getString("hotel_star")));
        obj.setHotel_carpark((rs.getBoolean("hotel_carpark")));
        obj.setHotel_spa((rs.getBoolean("hotel_spa")));
        obj.setHotel_room_service((rs.getBoolean("hotel_room_service")));
        obj.setHotel_pool((rs.getBoolean("hotel_pool")));
        obj.setHotel_wifi((rs.getBoolean("hotel_wifi")));
        obj.setHotel_fitness((rs.getBoolean("hotel_fitness")));
        obj.setHotel_concierge((rs.getBoolean("hotel_concierge")));

        return obj;

    }
    public boolean update(Hotel hotel){
        String query = "UPDATE public.user SET hotel_name = ?," +
                " hotel_city = ?," +
                " hotel_region = ?," +
                " hotel_address = ?," +
                " hotel_mail = ?," +
                " hotel_star = ?, " +
                "hotel_carpark = ?," +
                " hotel_spa = ?," +
                " hotel_room_service = ?, " +
                "hotel_pool = ?," +
                " hotel_wifi = ?," +
                " hotel_fitness = ?," +
                " hotel_concierge = ?" +
                " WHERE hotel_id = ?";
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);
            setHotelValues(hotel, preparedStatement);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean save(Hotel hotel) {
        String query = "INSERT INTO public.hotel " +
                "(" +
                "hotel_name, " +
                "hotel_city, " +
                "hotel_region, " +
                "hotel_address, " +
                "hotel_mail, " +
                "hotel_star, " +
                "hotel_carpark, " +
                "hotel_spa, " +
                "hotel_room_service, " +
                "hotel_pool, " +
                "hotel_wifi, " +
                "hotel_fitness, " +
                "hotel_concierge" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);
            setHotelValues(hotel, preparedStatement);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public int saveAndGetHotelId(Hotel hotel) {
        String query = "INSERT INTO public.hotel " +
                "(" +
                "hotel_name, " +
                "hotel_city, " +
                "hotel_region, " +
                "hotel_address, " +
                "hotel_mail, " +
                "hotel_star, " +
                "hotel_carpark, " +
                "hotel_spa, " +
                "hotel_room_service, " +
                "hotel_pool, " +
                "hotel_wifi, " +
                "hotel_fitness, " +
                "hotel_concierge" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            setHotelValues(hotel, preparedStatement);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean delete(int id){
        String query = "DELETE from public.hotel WHERE hotel_id = ?";

        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    private void setHotelValues(Hotel hotel, PreparedStatement preparedStatement) throws SQLException {

        //This method was written to avoid writing the same data.
        preparedStatement.setString(1,hotel.getHotel_name());
        preparedStatement.setString(2,hotel.getHotel_city());
        preparedStatement.setString(3,hotel.getHotel_region());
        preparedStatement.setString(4,hotel.getHotel_address());
        preparedStatement.setString(5,hotel.getHotel_mail());
        preparedStatement.setString(6,hotel.getHotel_star());
        preparedStatement.setBoolean(7,hotel.isHotel_carpark());
        preparedStatement.setBoolean(8,hotel.isHotel_spa());
        preparedStatement.setBoolean(9,hotel.isHotel_room_service());
        preparedStatement.setBoolean(10,hotel.isHotel_pool());
        preparedStatement.setBoolean(11,hotel.isHotel_wifi());
        preparedStatement.setBoolean(12,hotel. isHotel_fitness());
        preparedStatement.setBoolean(13,hotel. isHotel_concierge());
    }

//    public ArrayList<Hotel> query(String query){
//        ArrayList<Hotel> user = new ArrayList<>();
//        try {
//            ResultSet rs = this.con.createStatement().executeQuery(query);
//            while(rs.next()){
//                user.add(this.match(rs));
//            }
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
//        return user;
//    }

}
