package dao;

import core.Db;
import entity.Pension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionDao {
    private final Connection connection;

    public PensionDao() {
        this.connection = Db.getInstance();
    }

    public Pension getById(int id) {
        Pension pension = null;
        String query = "SELECT * FROM public.pension_type WHERE pension_id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                pension = this.match(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pension;
    }

    public ArrayList<Pension> findAll() {
        return this.selectByQuery("SELECT * FROM public.pension_type ORDER BY pension_id ASC");
    }

    public ArrayList<Pension> getByListHotelId(int hotelId) { //get pension list by hotelId.
        return this.selectByQuery("SELECT * FROM public.pension_type WHERE pension_hotel_id = " + hotelId);
    }

    public boolean save(Pension pension) {
        String query = "INSERT INTO public.pension_type " +
                "(" +
                "pension_type, " +
                "pension_hotel_id" +
                ")" +
                " VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, pension.getPensionType());
            preparedStatement.setInt(2, pension.getHotelId());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private Pension match(ResultSet resultSet) throws SQLException {
        Pension pension = new Pension();
        pension.setPensionId(resultSet.getInt("pension_id"));
        pension.setPensionType(resultSet.getString("pension_type"));
        pension.setHotelId(resultSet.getInt("pension_hotel_id"));
        return pension;
    }

    private ArrayList<Pension> selectByQuery(String query) {
        ArrayList<Pension> pensionList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                pensionList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionList;
    }
    public ArrayList<Pension> getByHotelId(int hotelId) {
        ArrayList<Pension> pensions = new ArrayList<>();
        String query = "SELECT * FROM public.pension_type WHERE pension_hotel_id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, hotelId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Pension pension = new Pension();
                pension.setPensionId(resultSet.getInt("pension_id"));
                pension.setPensionType(resultSet.getString("pension_type"));
                pension.setHotelId(resultSet.getInt("pension_hotel_id"));
                pensions.add(pension);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensions;
    }

//    public void deleteByHotelId(int hotelId) {
//        String sql = "DELETE FROM public.pension_type WHERE pension_hotel_id = ?";
//        try (Connection conn = Db.getInstance();
//             PreparedStatement pr = conn.prepareStatement(sql)) {
//            pr.setInt(1, hotelId);
//            pr.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void saveAll(ArrayList<Pension> pensions) {
//        String sql = "INSERT INTO public.pension_type (pension_hotel_id, pension_type) VALUES (?, ?)";
//        try (Connection conn = Db.getInstance();
//             PreparedStatement pr = conn.prepareStatement(sql)) {
//            for (Pension pension : pensions) {
//                pr.setInt(1, pension.getHotelId());
//                pr.setString(2, pension.getPensionType());
//                pr.addBatch();
//            }
//            pr.executeBatch();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}