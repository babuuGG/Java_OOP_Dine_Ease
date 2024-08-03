package DIneEaseModel;
import java.util.Date;

public class Order {
    private int id;
    private int tableNumber;
    private double totalAmount;
    private boolean isPaid;
    private Date orderDate;

    // Constructor
    public Order(int id, int tableNumber, double totalAmount, boolean isPaid, Date orderDate) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.totalAmount = totalAmount;
        this.isPaid = isPaid;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    // toString method
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", tableNumber=" + tableNumber +
                ", totalAmount=" + totalAmount +
                ", isPaid=" + isPaid +
                ", orderDate=" + orderDate +
                '}';
    }
}
