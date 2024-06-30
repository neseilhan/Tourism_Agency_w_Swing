package dao;

import core.Db;
import entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {
    private final Connection con;

    public ReservationDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Reservation> findAll(){
        ArrayList<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM public.reservation";
        try{
            Statement st = this.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                reservations.add(this.match(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return reservations;
    }
    public Reservation findById(int id){
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public boolean save(Reservation reservation){
        String query = "INSERT INTO public.reservation " +
                "(reservation_room_id," +
                "reservation_name," +
                "reservation_mail," +
                "reservation_pn, " +
                "reservation_idnum, " +
                "reservation_in_date, " +
                "reservation_out_date," +
                "reservation_prc," +
                "reservation_note) " +

                " values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            setReservationValues(reservation, pr);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Reservation reservation){
        String query = "UPDATE public.reservation SET reservation_name = ? , reservation_idnum = ? , reservation_mail = ? ," +
                " reservation_pn = ? WHERE reservation_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, reservation.getGuestName());
            pr.setString(2,reservation.getGuestCitizenId());
            pr.setString(3, reservation.getGuestMail());
            pr.setString(4,reservation.getGuestPhone());
            pr.setInt(5,reservation.getId());

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE from public.reservation WHERE reservation_id = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }
    private void setReservationValues(Reservation reservation, PreparedStatement pr) throws SQLException {
        pr.setInt(1,reservation.getRoomId());
        pr.setString(2, reservation.getGuestName());
        pr.setString(3, reservation.getGuestMail());
        pr.setString(4, reservation.getGuestPhone());
        pr.setString(5, reservation.getGuestCitizenId());
        pr.setDate(6, Date.valueOf(reservation.getCheckInDate()));
        pr.setDate(7, Date.valueOf(reservation.getCheckOutDate()));
        pr.setString(8, reservation.getTotalPrice());
        pr.setString(9, reservation.getReservationNote());
    }

    public Reservation match(ResultSet rs) throws SQLException{
        Reservation obj = new Reservation();
        obj.setId(rs.getInt("reservation_id"));
        obj.setRoomId(rs.getInt("reservation_room_id"));
        obj.setGuestName(rs.getString("reservation_name"));
        obj.setGuestMail(rs.getString("reservation_mail"));
        obj.setGuestPhone(rs.getString("reservation_pn"));
        obj.setGuestCitizenId(rs.getString("reservation_idnum"));
        obj.setCheckInDate(LocalDate.parse(rs.getString("reservation_in_date")));
        obj.setCheckOutDate(LocalDate.parse(rs.getString("reservation_out_date")));
        obj.setTotalPrice(rs.getString("reservation_prc"));
        obj.setGuestCitizenId(rs.getString("reservation_note"));

        return obj;
    }
}
