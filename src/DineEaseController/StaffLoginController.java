/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseController;

import DineEaseVIew.StaffPageView;
import DIneEaseModel.HomepageModel;

import DIneEaseModel.StaffLoginModel;
import DineEaseVIew.HomepageView;

import DineEaseVIew.StaffLoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author roshankhadka
 */
public class StaffLoginController {
    private StaffLoginModel model;
    private StaffLoginView view;

    public StaffLoginController(StaffLoginModel model, StaffLoginView view) {
        this.model = model;
        this.view = view;
        initView();
        initController();
    }

    private void initView() {
        view.frame.setVisible(true);
    }

    private void initController() {
        view.btnLogin.addActionListener((ActionEvent e) -> {
            performLogin();
        });

        view.btnForgotPassword.addActionListener((ActionEvent e) -> {
            view.showResetPasswordDialog(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextField txtResetUsername = (JTextField) ((JPanel) ((JButton) e.getSource()).getParent()).getComponent(2);
                    JTextField txtResetPhoneNumber = (JTextField) ((JPanel) ((JButton) e.getSource()).getParent()).getComponent(4);
                    JPasswordField txtNewPassword = (JPasswordField) ((JPanel) ((JButton) e.getSource()).getParent()).getComponent(6);
                    
                    String username = txtResetUsername.getText();
                    String phoneNumber = txtResetPhoneNumber.getText();
                    String newPassword = new String(txtNewPassword.getPassword());
                    
                    resetPassword(username, phoneNumber, newPassword);
                }
            });
        });

        view.aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(view.frame, "About Button clicked!");
            }
        });

        view.backMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.frame.dispose();
                new HomepageController(new HomepageModel(), new HomepageView());
            }
        });

        view.exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(view.frame, "Are you sure you want to exit?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    private void performLogin() {
        String username = view.txtUsername.getText();
        String password = new String(view.txtPassword.getPassword());

        if (model.validateLogin(username, password)) {
            JOptionPane.showMessageDialog(null, "Login Successful!");
            view.frame.dispose();
            new StaffPageController(new StaffPageView());
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
        }
    }

    private void resetPassword(String username, String phoneNumber, String newPassword) {
        if (model.resetPassword(username, phoneNumber, newPassword)) {
            JOptionPane.showMessageDialog(null, "Password Reset Successful!");
        } else {
            JOptionPane.showMessageDialog(null, "Password Reset Failed. Please check your Username and Phone Number.");
        }
    }

    public static void main(String[] args) {
        StaffLoginModel model = new StaffLoginModel();
        StaffLoginView view = new StaffLoginView();
        new StaffLoginController(model, view);
    }
}
    

