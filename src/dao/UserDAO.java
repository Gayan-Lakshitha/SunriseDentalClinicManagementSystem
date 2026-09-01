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
import java.util.ArrayList;
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
    
     public ArrayList<UserModel> getAllUsers() {

        ArrayList<UserModel> list =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT user_id, name, email, contact_no, role, password "
                    + "FROM users "
                    + "ORDER BY user_id";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();

            while (rs.next()) {

                UserModel user =
                        new UserModel();

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

                user.setPassword(
                        rs.getString("password")
                );

                list.add(user);
            }

        } catch (Exception e) {

            System.out.println(
                    "Error loading users: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return list;
    }
     
    public boolean updateUser(UserModel user) {

    String sql = "UPDATE users SET "
            + "name = ?, "
            + "email = ?, "
            + "contact_no = ?, "
            + "role = ?, "
            + "password = ? "
            + "WHERE user_id = ?";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement pst =
                con.prepareStatement(sql);

        pst.setString(1, user.getName());
        pst.setString(2, user.getEmail());
        pst.setString(3, user.getContactNo());
        pst.setString(4, user.getRole());
        pst.setString(5, user.getPassword());
        pst.setInt(6, user.getUserId());

        int rows =
                pst.executeUpdate();

        System.out.println(
                "Rows updated: " + rows
        );

        return rows > 0;

    } catch (Exception e) {

        System.out.println(
                "Update User Error: "
                + e.getMessage()
        );

        e.printStackTrace();

        return false;
    }
}
     
      public boolean deleteUser(int userId) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "DELETE FROM users "
                    + "WHERE user_id=?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setInt(
                    1,
                    userId
            );

            return pst.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Error deleting user: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }
    
}