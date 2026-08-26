/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.UserModel;

public class UserDAO {

    public boolean registerUser(UserModel user) {

    String sql = "INSERT INTO users "
            + "(name, email, contact_no, role, password) "
            + "VALUES (?, ?, ?, ?, ?)";

    try {

        Connection con = DBConnection.getConnection();

        if (con == null) {
            return false;
        }

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getContactNo());
        ps.setString(4, user.getRole());
        ps.setString(5, user.getPassword());

        int result = ps.executeUpdate();

        ps.close();
        con.close();

        return result > 0;

    } catch (SQLException e) {

        System.out.println("Registration error: "
                + e.getMessage());

        return false;
    }
}

    public UserModel login(String email, String password) {

        UserModel user = null;

        String sql = "SELECT * FROM users "
                + "WHERE email = ? AND password = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                user = new UserModel();

                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setContactNo(rs.getString("contact_no"));
                user.setRole(rs.getString("role"));
                user.setPassword(rs.getString("password"));
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("Login error: "
                    + e.getMessage());
        }

        return user;
    }
    
    public UserModel getUserById(int userId) {

    UserModel user = null;

    String sql = "SELECT * FROM users WHERE user_id = ?";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            user = new UserModel();

            user.setUserId(
                    rs.getInt("user_id")
            );

            user.setName(
                    rs.getString("name")
            );

            user.setEmail(
                    rs.getString("email")
            );

            user.setContactNo(
                    rs.getString("contact_no")
            );

            user.setRole(
                    rs.getString("role")
            );
        }

        rs.close();
        ps.close();
        con.close();

    } catch (SQLException e) {

        System.out.println("Error getting user: "
                + e.getMessage());
    }

    return user;
}
    
}