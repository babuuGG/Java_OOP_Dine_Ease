/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DIneEaseModel;

public class BillItem {
    private String foodName;
    private int quantity;
    private double totalPrice;
    public BillItem(String foodName, int quantity, double totalPrice) {
        this.foodName = foodName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getUnitPrice() {
        return totalPrice / quantity;
    }
}
