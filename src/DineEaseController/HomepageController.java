package DineEaseController;

import DIneEaseModel.AdminLoginModel;
import DIneEaseModel.CustomerPageModel;
import DIneEaseModel.HomepageModel;
import DIneEaseModel.OpeningPageModel;
import DIneEaseModel.StaffLoginModel;
import DineEaseController.AdminLoginController;
import DineEaseController.StaffLoginController;
import DineEaseDatabase.StaffLoginDAO;
import DineEaseVIew.AdminLoginView;
import DineEaseVIew.CustomerPageView;
import DineEaseVIew.HomepageView;
import DineEaseVIew.OpeningPageView;
import DineEaseVIew.StaffLoginView;

import java.awt.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomepageController {
    private HomepageModel model;
    private HomepageView view;

    public HomepageController(HomepageModel model, HomepageView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        for (Component comp : view.getButtonPanel().getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.addActionListener((ActionEvent e) -> {
                    handleButtonClick(button.getText());
                });
            }
        }
    }

    private void handleButtonClick(String buttonText) {
        switch (buttonText) {
            case "Admin":
            AdminLoginModel adminModel = new AdminLoginModel();
            AdminLoginView adminLoginView = new AdminLoginView();
            new AdminLoginController(adminModel, adminLoginView);
                break;
            case "Staff":
            StaffLoginDAO staffDAO = new StaffLoginDAO();
            StaffLoginView staffLoginView = new StaffLoginView();
            new StaffLoginController(staffDAO, staffLoginView);
            
            break;

            case "Customer":
            CustomerPageModel customerModel = new CustomerPageModel();
            CustomerPageView customerView = new CustomerPageView();
            new CustomerPageController(customerView);    
                
                break;
            case "Exit":
                System.exit(0);
                break;
            case "Go Back":
                view.getFrame().dispose();
                new OpeningPageController(new OpeningPageModel(), new OpeningPageView());
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomepageModel model = new HomepageModel();
            HomepageView view = new HomepageView();
            new HomepageController(model, view);
        });
    }
}
