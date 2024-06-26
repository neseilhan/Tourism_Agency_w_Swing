package dao;

import business.UserManager;
import core.Db;
import entity.Hotel;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {
    private final Connection con;

    public HotelDao(){
        this.con = Db.getInstance();
    }
    public Hotel getByID(int id) {
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
        String query = "UPDATE public.user SET hotel_name = ?, hotel_city = ?, hotel_region = ?, hotel_address = ?, hotel_mail = ?, hotel_star = ?, hotel_carpark = ?, hotel_spa = ?, hotel_room_service = ?, hotel_pool = ?, hotel_wifi = ?, hotel_fitness = ?, hotel_concierge = ? WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,hotel.getHotel_name());
            pr.setString(2,hotel.getHotel_city());
            pr.setString(3,hotel.getHotel_region());
            pr.setString(4,hotel.getHotel_address());
            pr.setString(5,hotel.getHotel_mail());
            pr.setString(6,hotel.getHotel_star());
            pr.setBoolean(7,hotel.isHotel_carpark());
            pr.setBoolean(8,hotel.isHotel_spa());
            pr.setBoolean(9,hotel.isHotel_room_service());
            pr.setBoolean(10,hotel.isHotel_pool());
            pr.setBoolean(11,hotel.isHotel_wifi());
            pr.setBoolean(12,hotel. isHotel_fitness());
            pr.setBoolean(13,hotel. isHotel_concierge());
            pr.setInt(14,hotel.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public boolean save(Hotel hotel){
        String query = "INSERT INTO public.hotel (hotel_name, hotel_city, hotel_region, hotel_address, hotel_mail, hotel_star, hotel_carpark, hotel_spa, hotel_room_service, hotel_pool, hotel_wifi, hotel_fitness, hotel_concierge ) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,hotel.getHotel_name());
            pr.setString(2,hotel.getHotel_city());
            pr.setString(3,hotel.getHotel_region());
            pr.setString(4,hotel.getHotel_address());
            pr.setString(5,hotel.getHotel_mail());
            pr.setString(6,hotel.getHotel_star());
            pr.setBoolean(7,hotel.isHotel_carpark());
            pr.setBoolean(8,hotel.isHotel_spa());
            pr.setBoolean(9,hotel.isHotel_room_service());
            pr.setBoolean(10,hotel.isHotel_pool());
            pr.setBoolean(11,hotel.isHotel_wifi());
            pr.setBoolean(12,hotel. isHotel_fitness());
            pr.setBoolean(13,hotel. isHotel_concierge());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
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

    public ArrayList<Hotel> query(String query){
        ArrayList<Hotel> user = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while(rs.next()){
                user.add(this.match(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return user;
    }

}
