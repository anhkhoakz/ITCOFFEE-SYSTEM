/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import com.mysql.cj.xdevapi.Result;
import javax.swing.JOptionPane;
import model.User;
import java.sql.*;

/**
 *
 * @author Win
 */
public class UserDao {
    public static void save(User user){
        String query = "Insert into user(name, email,mobileNumber,address, password, securityQuestion, answer, status) values('"+ user.getName() +"','"+ user.getEmail()+"','"+ user.getMobileNumber()+"','"+ user.getAddress()+"','"+ user.getPassword()+"','"+ user.getSecurityQuestion()+"','"+ user.getAnswer()+"','false' )";
        
        DbOperations.setDataOrDelete(query, "Registered Successfully! Wait for Admin Approval!");
    }
    
    public static User Login(String email, String password){
        User user = null;
        try {
            String query ="Select * from user where email='"+ email +"' and password ='"+ password+ "'";
            ResultSet rs = DbOperations.getData(query);
            
            while(rs.next()){
                user = new User();
                user.setStatus(rs.getString("status"));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }
    
    public static User getSecurityQuestion(String email){
        
        User user = null;
        try {
//            String query = "Update user Set password='" + user.getPassword() + "' where email='"+user.getEmail() +"' and securityQuestion='"+user.getSecurityQuestion()+"' and answer='"+user.getAnswer()+"'";
            String query ="Select * from user where email='"+ email + "'";
            ResultSet rs = DbOperations.getData(query);
            while(rs.next()){
                user = new User();
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setAnswer(rs.getString("answer"));
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
        
        return user;
    }
    
    public static void update(String email, String newPassword){
        String query = "Update user set password='" + newPassword + "' where email='"+email+"'";
        DbOperations.setDataOrDelete(query, "Password Change Successfully");
    }
}