package Security;
import Database.DatabaseManager;    
import Utils.Tools;
import Models.*;

import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.SQLException;

public class Identity {
    public static boolean login(Scanner sc, DatabaseManager db, User user) throws SQLException{ 
        
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        ResultSet rs = db.executeQuery("SELECT * FROM account WHERE username = '" + username + "' AND password = '" + password + "'");
        if (rs.next()) {
            user.setUserName(rs.getString("username"));
            user.setPassWord(rs.getString("password"));
            user.setRole(rs.getString("role"));
            user.setIdUser(rs.getInt("id_user"));
            return true;
        } else {
            System.out.println("\nInvalid username or password, please try again.");
            return false;
        }
    }
    public static void showProfile(Employee e){
        Tools.cout(e.toString(), "\n\n");
    }
    public static boolean isAdmin(int id, DatabaseManager db) throws SQLException{
        String sql = "SELECT role FROM `account` WHERE id_user='" + id +"' AND role='manager'";
        ResultSet rs = db.executeQuery(sql);
        if(rs.next()){
            rs.close();
            return true;
        }
        return false;
    }
}
