/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseController;

import DIneEaseModel.AdminLoginModel;
import DIneEaseModel.AdminPageModel;

import DineEaseDatabase.ItemInfoDAO;
import DineEaseVIew.AdminLoginView;

import DineEaseVIew.AdminPageView;

import DineEaseVIew.ItemInfoView;





public class AdminPageController {
    private AdminPageView view;
    private AdminPageModel model;

    public AdminPageController(AdminPageView view) {
        this.view = view;
        this.model = model;

        // Add action listeners to buttons
        view.showButtonDemo(e -> handleButtonClick(e.getActionCommand()));

        // Add menu listeners
        view.addMenuListener(new MenuListener());
    }

    private void handleButtonClick(String command) {
        switch (command) {
            case "Items Modify":
                view.getMainFrame().dispose();
                
                break;
            case "Staffs Info":
                view.showMessage("Staff Info button clicked!");
                break;
            case "Items Info":
                view.getMainFrame().dispose();
                ItemInfoView itemView = new ItemInfoView();
                ItemInfoDAO itemDAO = new ItemInfoDAO();
                new ItemInfoController(itemView, itemDAO);
                break;
            case "Sales Report":
                view.showMessage("Sales Report button clicked!");
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
