/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DIneEaseModel;



import java.sql.Timestamp;

public class OrderReportModel {
    private Timestamp orderDate;
    private int reportId;
    private double totalAmount;

    public OrderReportModel(Timestamp orderDate, int reportId, double totalAmount) {
        this.orderDate = orderDate;
        this.reportId = reportId;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
