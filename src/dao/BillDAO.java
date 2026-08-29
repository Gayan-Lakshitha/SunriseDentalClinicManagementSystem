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


   public boolean billExists(int appointmentNo) {

        boolean exists = false;

        String sql = "SELECT bill_id FROM bill "
                + "WHERE appointment_no = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setInt(1, appointmentNo);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                exists = true;
            }

            rs.close();
            pst.close();
            con.close();

        } catch (Exception e) {

            System.out.println(
                    "Error checking bill: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return exists;
    }


     public boolean createBill(BillModel bill) {

        boolean result = false;

        String sql = "INSERT INTO bill "
                + "(appointment_no, patient_id, "
                + "treatment_total, consultation_fee, "
                + "grand_total, payment, balance, "
                + "payment_status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setInt(
                    1,
                    bill.getAppointmentNo()
            );

            pst.setInt(
                    2,
                    bill.getPatientId()
            );

            pst.setDouble(
                    3,
                    bill.getTreatmentTotal()
            );

            pst.setDouble(
                    4,
                    bill.getConsultationFee()
            );

            pst.setDouble(
                    5,
                    bill.getGrandTotal()
            );

            pst.setDouble(
                    6,
                    bill.getPayment()
            );

            pst.setDouble(
                    7,
                    bill.getBalance()
            );

            pst.setString(
                    8,
                    bill.getPaymentStatus()
            );

            result = pst.executeUpdate() > 0;

            pst.close();
            con.close();

        } catch (Exception e) {

            System.out.println(
                    "Error creating bill: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return result;
    }


     public BillModel getBillByAppointmentNo(
            int appointmentNo) {

        BillModel bill = null;

        String sql = "SELECT * FROM bill "
                + "WHERE appointment_no = ? "
                + "ORDER BY bill_id DESC LIMIT 1";

        try {

            Connection con =
                    DBConnection.getConnection();

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

                bill.setPatientId(
                        rs.getInt("patient_id")
                );

                bill.setTreatmentTotal(
                        rs.getDouble("treatment_total")
                );

                bill.setConsultationFee(
                        rs.getDouble("consultation_fee")
                );

                bill.setGrandTotal(
                        rs.getDouble("grand_total")
                );

                bill.setPayment(
                        rs.getDouble("payment")
                );

                bill.setBalance(
                        rs.getDouble("balance")
                );

                bill.setPaymentStatus(
                        rs.getString("payment_status")
                );

                bill.setBillDate(
                        rs.getString("bill_date")
                );
            }

            rs.close();
            pst.close();
            con.close();

        } catch (Exception e) {

            System.out.println(
                    "Error loading bill: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return bill;
    }



     public boolean updatePayment(
            int appointmentNo,
            double payment,
            double balance,
            String paymentStatus) {

        boolean result = false;

        String sql = "UPDATE bill "
                + "SET payment = ?, "
                + "balance = ?, "
                + "payment_status = ? "
                + "WHERE appointment_no = ?";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setDouble(1, payment);

            pst.setDouble(2, balance);

            pst.setString(3, paymentStatus);

            pst.setInt(4, appointmentNo);

            result = pst.executeUpdate() > 0;

            pst.close();
            con.close();

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
