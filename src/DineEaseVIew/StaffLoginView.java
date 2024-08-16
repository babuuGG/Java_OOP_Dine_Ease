/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseVIew;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class StaffLoginView {
   public JFrame frame;
   public JPanel panel, loginPanel;
   public JLabel lblUsername, lblPassword, lblHeader;
   public JTextField txtUsername;
   public JPasswordField txtPassword;
   public JButton btnLogin, btnForgotPassword;
   public JMenuItem aboutMenuItem, backMenuItem, exitMenuItem;

    public StaffLoginView() {
        createUI();
        setLayout();
    }

    public void createUI() {
        frame = new JFrame("Dine-Ease: Your Virtual Manager");
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Resources/StaffLogin.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        loginPanel = new JPanel();
        lblUsername = new JLabel("Username: ");
        txtUsername = new JTextField(15);
        lblPassword = new JLabel("Password: ");
        lblHeader = new JLabel("STAFF LOGIN PAGE");
        txtPassword = new JPasswordField(15);
        btnLogin = new JButton("Login");
        btnForgotPassword = new JButton("Forgot Password?");

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu aboutMenu = new JMenu("About");
        menuBar.add(aboutMenu);
        JMenu backMenu = new JMenu("Back");
        menuBar.add(backMenu);
        JMenu exitMenu = new JMenu("Exit");
        menuBar.add(exitMenu);

        aboutMenuItem = new JMenuItem("About");
        aboutMenu.add(aboutMenuItem);
        backMenuItem = new JMenuItem("Back");
        backMenu.add(backMenuItem);
        exitMenuItem = new JMenuItem("Exit");
        exitMenu.add(exitMenuItem);
    }

    public void setLayout() {
    panel.setLayout(null);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setSize(screenSize.width, screenSize.height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);

    int panelWidth = 400;
    int panelHeight = 450;
    loginPanel.setLayout(null);
    loginPanel.setBounds(100, (screenSize.height - panelHeight) / 2, panelWidth, panelHeight);
    loginPanel.setBackground(Color.lightGray);

    lblHeader.setBounds((frame.getWidth() - 300) / 2, 20, 300, 50); // Adjust bounds for the frame
    lblHeader.setFont(new Font("Arial", Font.BOLD, 28));
    lblHeader.setForeground(new Color(44, 62, 80));
    frame.getContentPane().add(lblHeader); // Add lblHeader to the frame

    int x = 50, y = 80, width = 300, height = 30, spacing = 40;
    lblUsername.setBounds(x, y, width, height);
    txtUsername.setBounds(x, y + spacing, width, height);
    lblPassword.setBounds(x, y + 2 * spacing, width, height);
    txtPassword.setBounds(x, y + 3 * spacing, width, height);
    btnLogin.setBounds(150, y + 5 * spacing, 100, 30);
    btnForgotPassword.setBounds(130, y + 6 * spacing, 150, 30);

    lblUsername.setFont(new Font("Arial", Font.PLAIN, 18));
    lblPassword.setFont(new Font("Arial", Font.PLAIN, 18));
    txtUsername.setFont(new Font("Arial", Font.PLAIN, 16));
    txtPassword.setFont(new Font("Arial", Font.PLAIN, 16));
    btnLogin.setFont(new Font("Arial", Font.BOLD, 18));
    btnLogin.setBackground(new Color(52, 152, 219));
    btnLogin.setForeground(new Color(0, 0, 182, 155));
    btnLogin.setBorderPainted(false);
    btnLogin.setOpaque(true);

    loginPanel.add(lblUsername);
    loginPanel.add(txtUsername);
    loginPanel.add(lblPassword);
    loginPanel.add(txtPassword);
    loginPanel.add(btnLogin);
    loginPanel.add(btnForgotPassword);

    panel.add(loginPanel);
    frame.add(panel);
    frame.setVisible(true);
}


    public void showResetPasswordDialog(ActionListener actionListener) {
        JFrame resetFrame = new JFrame("Reset Password");
        JPanel resetPanel = new JPanel();
        resetPanel.setLayout(null);

        JLabel lblResetHeader = new JLabel("Reset Password");
        lblResetHeader.setBounds(125, 20, 150, 30);
        lblResetHeader.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblResetUsername = new JLabel("Username: ");
        lblResetUsername.setBounds(50, 70, 100, 20);
        JTextField txtResetUsername = new JTextField(15);
        txtResetUsername.setBounds(160, 70, 150, 20);

        JLabel lblResetPhoneNumber = new JLabel("Phone Number: ");
        lblResetPhoneNumber.setBounds(50, 100, 100, 20);
        JTextField txtResetPhoneNumber = new JTextField(15);
        txtResetPhoneNumber.setBounds(160, 100, 150, 20);

        JLabel lblNewPassword = new JLabel("New Password: ");
        lblNewPassword.setBounds(50, 130, 100, 20);
        JPasswordField txtNewPassword = new JPasswordField(15);
        txtNewPassword.setBounds(160, 130, 150, 20);

        JButton btnResetPassword = new JButton("Reset Password");
        btnResetPassword.setBounds(130, 170, 150, 30);
        btnResetPassword.addActionListener(actionListener);

        resetPanel.add(lblResetHeader);
        resetPanel.add(lblResetUsername);
        resetPanel.add(txtResetUsername);
        resetPanel.add(lblResetPhoneNumber);
        resetPanel.add(txtResetPhoneNumber);
        resetPanel.add(lblNewPassword);
        resetPanel.add(txtNewPassword);
        resetPanel.add(btnResetPassword);

        resetFrame.add(resetPanel);
        resetFrame.setSize(400, 300);
        resetFrame.setResizable(false);
        resetFrame.setLocationRelativeTo(null);
        resetFrame.setVisible(true);
    }
}
        
    

