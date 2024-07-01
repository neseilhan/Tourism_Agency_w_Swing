import business.UserManager;
import core.Db;
import core.Helper;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;

public class App {

    public static void main(String[] args) {

        Helper.setTheme();
//        LoginView login = new LoginView();
        UserManager userManager = new UserManager();
//        AdminView adminView = new AdminView(userManager.findByLogin("admin","asd")); //its temporary usage.
        EmployeeView employeeView = new EmployeeView(userManager.findByLogin("neseilhan","asd"));


    }
}
