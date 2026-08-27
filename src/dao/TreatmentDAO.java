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
import model.TreatmentModel;

public class TreatmentDAO {

    public ArrayList<TreatmentModel> getAllTreatments() {

        ArrayList<TreatmentModel> treatmentList =
                new ArrayList<>();

        String sql = "SELECT * FROM treatments "
                + "ORDER BY treatment_name";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                TreatmentModel treatment =
                        new TreatmentModel();

                treatment.setTreatmentId(
                        rs.getInt("treatment_id")
                );

                treatment.setTreatmentName(
                        rs.getString("treatment_name")
                );

                treatment.setAmount(
                        rs.getDouble("amount")
                );

                treatmentList.add(treatment);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("Error loading treatments: "
                    + e.getMessage());
        }

        return treatmentList;
    }
}
