/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseController;



import DIneEaseModel.AdminLoginModel;
import DIneEaseModel.AdminPageModel;
import DineEaseVIew.AdminLoginView;
import DineEaseVIew.AdminPageView;
import DineEaseVIew.StaffPageView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class AdminLoginController {
    private AdminLoginModel model;
    private AdminLoginView view;

    public AdminLoginController(AdminLoginModel model, AdminLoginView view) {
        this.model = model;
        this.view = view;

        this.view.addLoginListener(new LoginListener());
        this.view.addAboutListener(new AboutListener());
        this.view.addBackListener(new BackListener());
        this.view.addExitListener(new ExitListener());
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();

            if (model.authenticate(username, password)) {
                view.showMessage("Login Successful!");
                
                new AdminPageController(new AdminPageModel(), new AdminPageView());
            } else {
                view.showMessage("Invalid Username or Password!");
            }
        }
    }

    class AboutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showMessage("About Button clicked!");
        }
    }

    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            
        }
    }

    class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showConfirmDialog(view, "Are you sure you want to exit?",
                    "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        AdminLoginModel model = new AdminLoginModel();
        AdminLoginView view = new AdminLoginView();
        new AdminLoginController(model, view);
    }
}
