/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class AppointmentTreatmentModel {

    private int appointmentTreatmentId;
    private int appointmentNo;
    private int treatmentId;
    private String treatmentName;
    private double amount;

    public int getAppointmentTreatmentId() {
        return appointmentTreatmentId;
    }

    public void setAppointmentTreatmentId(int appointmentTreatmentId) {
        this.appointmentTreatmentId = appointmentTreatmentId;
    }

    public int getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(int appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}