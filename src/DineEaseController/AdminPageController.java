package DineEaseController;



import DIneEaseModel.AdminLoginModel;
import DIneEaseModel.AdminPageModel;
import DineEaseDatabase.ItemInfoDAO;
import DineEaseDatabase.ItemModifyDAO;
import DineEaseDatabase.OrderReportDAO;
import DineEaseDatabase.StaffInfoDAO;
import DineEaseVIew.AdminLoginView;
import DineEaseVIew.AdminPageView;
import DineEaseVIew.ItemInfoView;
import DineEaseVIew.ItemModifyView;
import DineEaseVIew.OrderReportView;
import DineEaseVIew.StaffInfoView;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;





public class AdminPageController {
    private AdminPageView view;
    private AdminPageModel model;

    public AdminPageController(AdminPageModel adminPageModel, AdminPageView view) {
        this.view = view;
        this.model = new AdminPageModel(); // Initialize the model

        // Add action listeners to buttons
        view.showButtonDemo(e -> {
            try {
                handleButtonClick(e.getActionCommand());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Add menu listeners
        view.addMenuListener(new MenuListener());
    }

    private void handleButtonClick(String command) throws ClassNotFoundException, SQLException {
        switch (command) {
            case "Items Modify":
                view.getMainFrame().dispose();
                ItemModifyView modifyView = new ItemModifyView();
                ItemModifyDAO modifyDAO = new ItemModifyDAO();
                new ItemModifyController(modifyView, modifyDAO);
                break;
            case "Staffs Info":
                view.getMainFrame().dispose();
                StaffInfoView staffView = new StaffInfoView();
                StaffInfoDAO staffDAO = new StaffInfoDAO();
                new StaffInfoController(staffView, staffDAO);
                break;
            case "Items Info":
                view.getMainFrame().dispose();
                ItemInfoView itemView = new ItemInfoView();
                ItemInfoDAO itemDAO = new ItemInfoDAO();
                new ItemInfoController(itemView, itemDAO);
                break;
            case "Sales Report":
    try {
        view.getMainFrame().dispose();  // Dispose of the current view
        OrderReportView orderView = new OrderReportView();
        OrderReportDAO orderDao = new OrderReportDAO();
        new OrderReportController(orderView, orderDao);
        orderView.setVisible(true);  // Make the new view visible
    } catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(null, "Error loading Sales Report: " + e.getMessage());
        e.printStackTrace();  // Log the exception for debugging
    }
    break;



            case "Go Back":
                view.getMainFrame().dispose();
                new AdminLoginController(new AdminLoginModel(), new AdminLoginView());
                break;
        }
    }

    private class MenuListener implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "About":
                    view.showMessage("About Button clicked!");
                    break;
                case "Back":
                    view.getMainFrame().dispose();
                    new AdminLoginController(new AdminLoginModel(), new AdminLoginView());
                    break;
                case "Exit":
                    int option = javax.swing.JOptionPane.showConfirmDialog(view.getMainFrame(), "Are you sure you want to exit?",
                            "Exit Confirmation", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);
                    if (option == javax.swing.JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                    break;
            }
        }
    }
    
}
