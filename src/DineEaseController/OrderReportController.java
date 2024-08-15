/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseController;





import DIneEaseModel.OrderReportModel;
import DineEaseVIew.OrderReportView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class OrderReportController {
    private OrderReportView view;
   

    public OrderReportController(OrderReportView view) {
        this.view = view;
       

        this.view.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Date searchDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(view.dateTextField.getText()).getTime());
                    performSearch(searchDate);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please use yyyy-MM-dd.");
                }
            }
        });

        fetchAllOrders();
    }

    

    
}

