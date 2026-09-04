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
import model.AppointmentModel;
import model.UserModel;

public class AppointmentDAO {

    
    public boolean addAppointment(AppointmentModel appointment) {

        String sql = "INSERT INTO appointments "
                + "(patient_id, address, dentist_id, appointment_date, status) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            if (con == null) {
                return false;
            }

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, appointment.getPatientId());
            ps.setString(2, appointment.getAddress());
            ps.setInt(3, appointment.getDentistId());
            ps.setString(4, appointment.getAppointmentDate());
            ps.setString(5, "Pending");

            int result = ps.executeUpdate();

            ps.close();
            con.close();

            return result > 0;

        } catch (SQLException e) {

            System.out.println("Error adding appointment: "
                    + e.getMessage());

            return false;
        }
    }


   
    public ArrayList<UserModel> getDoctors() {

        ArrayList<UserModel> doctorList =
                new ArrayList<>();

        String sql = "SELECT user_id, name "
                + "FROM users "
                + "WHERE role = 'Doctor' "
                + "ORDER BY name";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                UserModel doctor = new UserModel();

                doctor.setUserId(
                        rs.getInt("user_id")
                );

                doctor.setName(
                        rs.getString("name")
                );

                doctorList.add(doctor);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("Error loading doctors: "
                    + e.getMessage());
        }

        return doctorList;
    }


   
    public ArrayList<AppointmentModel> getPatientAppointments(
            int patientId) {

        ArrayList<AppointmentModel> appointmentList =
                new ArrayList<>();

        String sql = "SELECT a.appointment_no, "
                + "p.name AS patient_name, "
                + "p.contact_no AS contact_no, "
                + "a.address, "
                + "d.name AS dentist_name, "
                + "a.appointment_date, "
                + "a.appointment_time, "
                + "a.status "
                + "FROM appointments a "
                + "JOIN users p ON a.patient_id = p.user_id "
                + "JOIN users d ON a.dentist_id = d.user_id "
                + "WHERE a.patient_id = ? "
                + "ORDER BY a.appointment_no DESC";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, patientId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                AppointmentModel appointment =
                        new AppointmentModel();

                appointment.setAppointmentNo(
                        rs.getInt("appointment_no")
                );

                appointment.setPatientName(
                        rs.getString("patient_name")
                );

                appointment.setContactNo(
                        rs.getString("contact_no")
                );

                appointment.setAddress(
                        rs.getString("address")
                );

                appointment.setDentistName(
                        rs.getString("dentist_name")
                );

                appointment.setAppointmentDate(
                        rs.getString("appointment_date")
                );

                appointment.setAppointmentTime(
                        rs.getString("appointment_time")
                );

                appointment.setStatus(
                        rs.getString("status")
                );

                appointmentList.add(appointment);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("Error loading appointments: "
                    + e.getMessage());
        }

        return appointmentList;
    }
    
    public ArrayList<AppointmentModel> getAllAppointments() {

    ArrayList<AppointmentModel> appointmentList =
            new ArrayList<>();

    String sql = "SELECT a.appointment_no, "
            + "p.user_id AS patient_id, "
            + "p.name AS patient_name, "
            + "p.contact_no AS contact_no, "
            + "a.address, "
            + "d.user_id AS dentist_id, "
            + "d.name AS dentist_name, "
            + "a.appointment_date, "
            + "a.appointment_time, "
            + "a.status "
            + "FROM appointments a "
            + "JOIN users p ON a.patient_id = p.user_id "
            + "JOIN users d ON a.dentist_id = d.user_id "
            + "ORDER BY a.appointment_no DESC";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            AppointmentModel appointment =
                    new AppointmentModel();

            appointment.setAppointmentNo(
                    rs.getInt("appointment_no")
            );

            appointment.setPatientId(
                    rs.getInt("patient_id")
            );

            appointment.setPatientName(
                    rs.getString("patient_name")
            );

            appointment.setContactNo(
                    rs.getString("contact_no")
            );

            appointment.setAddress(
                    rs.getString("address")
            );

            appointment.setDentistId(
                    rs.getInt("dentist_id")
            );

            appointment.setDentistName(
                    rs.getString("dentist_name")
            );

            appointment.setAppointmentDate(
                    rs.getString("appointment_date")
            );

            appointment.setAppointmentTime(
                    rs.getString("appointment_time")
            );

            appointment.setStatus(
                    rs.getString("status")
            );

            appointmentList.add(appointment);
        }

        rs.close();
        ps.close();
        con.close();

    } catch (SQLException e) {

        System.out.println("Error loading appointments: "
                + e.getMessage());
    }

    return appointmentList;
}

    public AppointmentModel searchAppointment(int appointmentNo) {

    AppointmentModel appointment = null;

    String sql = "SELECT a.appointment_no, "
            + "p.user_id AS patient_id, "
            + "p.name AS patient_name, "
            + "p.contact_no AS contact_no, "
            + "a.address, "
            + "d.user_id AS dentist_id, "
            + "d.name AS dentist_name, "
            + "a.appointment_date, "
            + "a.appointment_time, "
            + "a.status "
            + "FROM appointments a "
            + "JOIN users p ON a.patient_id = p.user_id "
            + "JOIN users d ON a.dentist_id = d.user_id "
            + "WHERE a.appointment_no = ?";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(1, appointmentNo);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            appointment = new AppointmentModel();

            appointment.setAppointmentNo(
                    rs.getInt("appointment_no")
            );

            appointment.setPatientId(
                    rs.getInt("patient_id")
            );

            appointment.setPatientName(
                    rs.getString("patient_name")
            );

            appointment.setContactNo(
                    rs.getString("contact_no")
            );

            appointment.setAddress(
                    rs.getString("address")
            );

            appointment.setDentistId(
                    rs.getInt("dentist_id")
            );

            appointment.setDentistName(
                    rs.getString("dentist_name")
            );

            appointment.setAppointmentDate(
                    rs.getString("appointment_date")
            );

            appointment.setAppointmentTime(
                    rs.getString("appointment_time")
            );

            appointment.setStatus(
                    rs.getString("status")
            );
        }

        rs.close();
        ps.close();
        con.close();

    } catch (SQLException e) {

        System.out.println("Search error: "
                + e.getMessage());
    }

    return appointment;
}
    
    public boolean updateAppointment(AppointmentModel appointment) {

    String sql = "UPDATE appointments SET "
            + "address = ?, "
            + "dentist_id = ?, "
            + "appointment_date = ?, "
            + "appointment_time = ?, "
            + "status = ? "
            + "WHERE appointment_no = ?";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(1, appointment.getAddress());

        ps.setInt(2, appointment.getDentistId());

        ps.setString(3, appointment.getAppointmentDate());

        ps.setString(4, appointment.getAppointmentTime());

        ps.setString(5, appointment.getStatus());

        ps.setInt(6, appointment.getAppointmentNo());

        int result = ps.executeUpdate();

        ps.close();
        con.close();

        return result > 0;

    } catch (SQLException e) {

        System.out.println("Update error: "
                + e.getMessage());

        return false;
    }
}
 
    public boolean deleteAppointment(int appointmentNo) {

    String sql =
            "DELETE FROM appointments WHERE appointment_no = ?";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(1, appointmentNo);

        int result = ps.executeUpdate();

        ps.close();
        con.close();

        return result > 0;

    } catch (SQLException e) {

        System.out.println("Delete error: "
                + e.getMessage());

        return false;
    }
}
    
    
    public ArrayList<AppointmentModel> getDoctorAppointments(
        int dentistId) {

    ArrayList<AppointmentModel> appointmentList =
            new ArrayList<>();

    String sql = "SELECT a.appointment_no, "
            + "p.user_id AS patient_id, "
            + "p.name AS patient_name, "
            + "p.contact_no AS contact_no, "
            + "a.address, "
            + "d.user_id AS dentist_id, "
            + "d.name AS dentist_name, "
            + "a.appointment_date, "
            + "a.appointment_time, "
            + "a.status "
            + "FROM appointments a "
            + "JOIN users p ON a.patient_id = p.user_id "
            + "JOIN users d ON a.dentist_id = d.user_id "
            + "WHERE a.dentist_id = ? "
            + "ORDER BY a.appointment_date ASC";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(1, dentistId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            AppointmentModel appointment =
                    new AppointmentModel();
            appointment.setAppointmentNo(
                    rs.getInt("appointment_no")
            );
            appointment.setPatientId(
                    rs.getInt("patient_id")
            );
            appointment.setPatientName(
                    rs.getString("patient_name")
            );
            appointment.setContactNo(
                    rs.getString("contact_no")
            );
            appointment.setAddress(
                    rs.getString("address")
            );
            appointment.setDentistId(
                    rs.getInt("dentist_id")
            );
            appointment.setDentistName(
                    rs.getString("dentist_name")
            );
            appointment.setAppointmentDate(
                    rs.getString("appointment_date")
            );
            appointment.setAppointmentTime(
                    rs.getString("appointment_time")
            );
            appointment.setStatus(
                    rs.getString("status")
            );
            appointmentList.add(appointment);
        }

        rs.close();
        ps.close();
        con.close();

    } catch (SQLException e) {

        System.out.println(
                "Error loading doctor appointments: "
                + e.getMessage()
        );
    }

    return appointmentList;
}
      
    public AppointmentModel getAppointmentByNo(int appointmentNo) {

    AppointmentModel appointment = null;

    try {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM appointments "
                + "WHERE appointment_no = ?";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, appointmentNo);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            appointment = new AppointmentModel();

            appointment.setAppointmentNo(
                    rs.getInt("appointment_no")
            );

            appointment.setPatientId(
                    rs.getInt("patient_id")
            );

            appointment.setAddress(
                    rs.getString("address")
            );

            appointment.setDentistId(
                    rs.getInt("dentist_id")
            );
            appointment.setAppointmentDate(
                    rs.getString("appointment_date")
            );

            appointment.setAppointmentTime(
                    rs.getString("appointment_time")
            );

            appointment.setStatus(
                    rs.getString("status")
            );
        }

    } catch (Exception e) {

        System.out.println(
                "Error loading appointment: "
                + e.getMessage()
        );

        e.printStackTrace();
    }

    return appointment;
}
}
