/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.BillModel;

public class BillDAO {


    // Check whether a bill already exists
    public boolean billExists(int appointmentNo) {

        boolean exists = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT bill_id FROM bill "
                    + "WHERE appointment_no = ?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setInt(1, appointmentNo);

            ResultSet rs =
                    pst.executeQuery();

            if (rs.next()) {

                exists = true;
            }

        } catch (Exception e) {

            System.out.println(
                    "Error checking bill: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return exists;
    }


    // Create bill
    public boolean createBill(BillModel bill) {

        boolean result = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO bill "
                    + "("
                    + "appointment_no, "
                    + "consultation_fee, "
                    + "treatment_total, "
                    + "total_amount, "
                    + "payment_status"
                    + ") "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setInt(
                    1,
                    bill.getAppointmentNo()
            );

            pst.setDouble(
                    2,
                    bill.getConsultationFee()
            );

            pst.setDouble(
                    3,
                    bill.getTreatmentTotal()
            );

            pst.setDouble(
                    4,
                    bill.getTotalAmount()
            );

            pst.setString(
                    5,
                    bill.getPaymentStatus()
            );

            result = pst.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Error creating bill: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return result;
    }


    // Get bill by appointment number
    public BillModel getBillByAppointmentNo(
            int appointmentNo) {

        BillModel bill = null;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM bill "
                    + "WHERE appointment_no = ?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setInt(1, appointmentNo);

            ResultSet rs =
                    pst.executeQuery();

            if (rs.next()) {

                bill = new BillModel();

                bill.setBillId(
                        rs.getInt("bill_id")
                );

                bill.setAppointmentNo(
                        rs.getInt("appointment_no")
                );

                bill.setConsultationFee(
                        rs.getDouble("consultation_fee")
                );

                bill.setTreatmentTotal(
                        rs.getDouble("treatment_total")
                );

                bill.setTotalAmount(
                        rs.getDouble("total_amount")
                );

                bill.setPaymentStatus(
                        rs.getString("payment_status")
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error loading bill: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return bill;
    }


    // Mark bill as paid
    public boolean updatePaymentStatus(
            int appointmentNo,
            String paymentStatus) {

        boolean result = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE bill "
                    + "SET payment_status = ? "
                    + "WHERE appointment_no = ?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(1, paymentStatus);

            pst.setInt(2, appointmentNo);

            result = pst.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Error updating payment: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return result;
    }
}
