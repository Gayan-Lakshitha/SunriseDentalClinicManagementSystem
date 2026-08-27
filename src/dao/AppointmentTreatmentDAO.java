/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.AppointmentTreatmentModel;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AppointmentTreatmentDAO {

    public boolean addAppointmentTreatment(
            AppointmentTreatmentModel treatment) {

        String sql = "INSERT INTO appointment_treatments "
                + "(appointment_no, treatment_id, amount) "
                + "VALUES (?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1,
                    treatment.getAppointmentNo());

            ps.setInt(2,
                    treatment.getTreatmentId());

            ps.setDouble(3,
                    treatment.getAmount());

            int result = ps.executeUpdate();

            ps.close();
            con.close();

            return result > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error saving treatment: "
                    + e.getMessage()
            );

            return false;
        }
    }


    public boolean treatmentAlreadyAdded(
        int appointmentNo,
        int treatmentId) {

    String sql = "SELECT * FROM appointment_treatments "
            + "WHERE appointment_no = ? "
            + "AND treatment_id = ?";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(1, appointmentNo);
        ps.setInt(2, treatmentId);

        ResultSet rs = ps.executeQuery();

        boolean exists = rs.next();

        rs.close();
        ps.close();
        con.close();

        return exists;

    } catch (SQLException e) {

        System.out.println(
                "Error checking treatment: "
                + e.getMessage()
        );

        return false;
    }
}

    public ArrayList<AppointmentTreatmentModel>
        getAppointmentTreatments(int appointmentNo) {

    ArrayList<AppointmentTreatmentModel> list =
            new ArrayList<>();

    String sql = "SELECT at.appointment_treatment_id, "
            + "at.appointment_no, "
            + "t.treatment_id, "
            + "t.treatment_name, "
            + "at.amount "
            + "FROM appointment_treatments at "
            + "JOIN treatments t "
            + "ON at.treatment_id = t.treatment_id "
            + "WHERE at.appointment_no = ?";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(1, appointmentNo);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            AppointmentTreatmentModel treatment =
                    new AppointmentTreatmentModel();

            treatment.setAppointmentTreatmentId(
                    rs.getInt(
                            "appointment_treatment_id"
                    )
            );

            treatment.setAppointmentNo(
                    rs.getInt("appointment_no")
            );

            treatment.setTreatmentId(
                    rs.getInt("treatment_id")
            );

            treatment.setTreatmentName(
                    rs.getString("treatment_name")
            );

            treatment.setAmount(
                    rs.getDouble("amount")
            );

            list.add(treatment);
        }

        rs.close();
        ps.close();
        con.close();

    } catch (SQLException e) {

        System.out.println(
                "Error loading appointment treatments: "
                + e.getMessage()
        );
    }

    return list;
}
        
        public double getTreatmentTotal(int appointmentNo) {

    double total = 0;

    try {

        Connection con = DBConnection.getConnection();

        String sql =
                "SELECT SUM(amount) AS total "
                + "FROM appointment_treatments "
                + "WHERE appointment_no = ?";

        PreparedStatement pst =
                con.prepareStatement(sql);

        pst.setInt(1, appointmentNo);

        ResultSet rs =
                pst.executeQuery();

        if (rs.next()) {

            total = rs.getDouble("total");
        }

    } catch (Exception e) {

        System.out.println(
                "Error calculating treatment total: "
                + e.getMessage()
        );

        e.printStackTrace();
    }

    return total;
}

}