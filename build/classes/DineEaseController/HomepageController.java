package DineEaseController;

import DIneEaseModel.HomepageModel;
import DIneEaseModel.OpeningPageModel;
import DIneEaseModel.StaffLoginModel;
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
                new AdminLogin();
                break;
            case "Staff":
            StaffLoginModel staffModel = new StaffLoginModel();
            StaffLoginView staffLoginView = new StaffLoginView();
            new StaffLoginController(staffModel, staffLoginView);
                break;
            case "Customer":
                new CustomerPage();
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
