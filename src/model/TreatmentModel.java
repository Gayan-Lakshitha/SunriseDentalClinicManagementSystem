/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class TreatmentModel {

    private int treatmentId;
    private String treatmentName;
    private double treatmentAmount;

    public TreatmentModel() {
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

    public double getTreatmentAmount() {
        return treatmentAmount;
    }

    public void setTreatmentAmount(double treatmentAmount) {
        this.treatmentAmount = treatmentAmount;
    }
}