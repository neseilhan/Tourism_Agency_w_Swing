package business;

import core.Helper;
import dao.UserDao;
import entity.User;
import entity.UserRole;

import java.util.ArrayList;

public class UserManager {

    private final UserDao userDao;

    public UserManager(){
        this.userDao = new UserDao();
    }

    public User getById(int id){
        return this.userDao.findById(id);
    }

    public boolean save(User user) {
        if (this.getById(user.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.userDao.save(user);
    }

    public boolean update(User user){
        if(getById(user.getId()) == null){
            Helper.showMsg("User with id number " + user.getId() + " could not be found.");
            return false;
        }
        return this.userDao.update(user);
    }
    public boolean delete(int id){
        if(this.getById(id) == null){
            Helper.showMsg( "User with id number " + id + " could not be found.");
            return false;
        }
        return this.userDao.delete(id);
    }
    public User findByLogin(String username, String password){
        return this.userDao.findByLogin(username,password);
    }
    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    public ArrayList<User> findAllUsersByRole(String role) {
        return userDao.findAllUsersByRole(role);
    }
    public ArrayList<User> findAllAdmins(){
        return this.userDao.findAllAdmins();
    }

    public ArrayList<User> findAllEmployees(){
        return this.userDao.findAllEmployees();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> modelList){ //To create a query in the table and assign a new value.
        ArrayList<Object[]> modelObjList = new ArrayList<>();
        for (User obj : modelList) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getUsername();
            rowObject[i++] = obj.getPassword();
            rowObject[i++] = obj.getRole();
            modelObjList.add(rowObject);
        }
        return modelObjList;
    }
    public ArrayList<User> filter_role(UserRole role){   //We can add name field to the panel and search by name.
        String query = "SELECT * FROM public.user";
        ArrayList<String> whereList = new ArrayList<>();
//        if(!name.isEmpty()){
//            whereList.add("username LIKE '" + name + "%'");
//        }

        if(role != null){
            whereList.add(" user_role = '" + role + "'");

        }

        if(!whereList.isEmpty()){
            String whereQuery = String.join("AND", whereList);
            query += " WHERE " + whereQuery;
        }
        return this.userDao.query(query);
    }
}
