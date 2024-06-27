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

    public Pension getByID(int id) {
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
}