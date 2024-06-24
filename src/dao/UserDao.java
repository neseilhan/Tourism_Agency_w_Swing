package dao;

import core.Db;
import entity.User;
import entity.UserRole;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    private final Connection con;
    public UserDao(){
        this.con = Db.getInstance();
    }
    public ArrayList<User> findAll() { //Finds all users without filter.
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM public.user";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while(rs.next()){
                userList.add(this.match(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return userList;
    }
    public User findByLogin(String username, String password){ //Checks if the user has an account.
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name=? AND user_password = ?"; //'public.user' is for psql.

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public ArrayList<User> findAllUsersByRole(String role) {
        ArrayList<User> userArrayList = new ArrayList<>();
        String query = "SELECT * FROM public.user WHERE user_role = ?";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userArrayList.add(this.match(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userArrayList;
    }

    // Specific method to find all admins
    public ArrayList<User> findAllAdmins() {
        return findAllUsersByRole("ADMIN");
    }

    // Specific method to find all employees
    public ArrayList<User> findAllEmployees() {
        return findAllUsersByRole("EMPLOYEE");
    }

    public User findById(int userId){
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,userId);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }
    public boolean update(User user){
        String query = "UPDATE public.user SET user_name = ? , user_password = ? , user_role = ? WHERE user_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2,user.getPassword());
            pr.setString(3, user.getRole().toString());
            pr.setInt(4,user.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public boolean save(User user){
        String query = "INSERT INTO public.user (user_name, user_password, user_role) values (?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,user.getUsername());
            pr.setString(2,user.getPassword());
            pr.setString(3,user.getRole().toString());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int id){
        String query = "DELETE from public.user WHERE user_id = ?";

        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public ArrayList<User> query(String query){
        ArrayList<User> user = new ArrayList<>();
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

    public User match(ResultSet rs) throws SQLException{
        User obj = new User();

        obj.setId(rs.getInt("user_id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setPassword(rs.getString("user_password"));
        obj.setRole(UserRole.valueOf(rs.getString("user_role")));

        return obj;

    }
}
