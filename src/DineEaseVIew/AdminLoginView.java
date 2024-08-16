/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseVIew;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminLoginView extends JFrame {
    private JPanel panel, loginPanel;
    private JLabel lblUsername, lblPassword, lblHeader;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public AdminLoginView() {
        createUI();
        addComponents();
        setLayout();
    }

    public void createUI() {
        setTitle("Dine-Ease: Your Virtual Manager");
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Resources/AdminLogin.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        loginPanel = new JPanel();
        lblUsername = new JLabel("Username: ");
        txtUsername = new JTextField(15);
        lblPassword = new JLabel("Password: ");
        lblHeader = new JLabel("ADMIN LOGIN ");
        txtPassword = new JPasswordField(15);
        btnLogin = new JButton("Login");
    }

    public void setLayout() {
        panel.setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        loginPanel.setLayout(null);
        loginPanel.setBounds(900, (screenSize.height - 450) / 2, 400, 450);
        loginPanel.setBackground(Color.white);

        lblHeader.setBounds((loginPanel.getWidth() - 300) / 2, 20, 300, 50);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 28));
        lblHeader.setForeground(new Color(44, 62, 80));

        int x = 50, y = 80, width = 300, height = 30, spacing = 40;
        lblUsername.setBounds(x, y, width, height);
        txtUsername.setBounds(x, y + spacing, width, height);
        lblPassword.setBounds(x, y + 2 * spacing, width, height);
        txtPassword.setBounds(x, y + 3 * spacing, width, height);
        btnLogin.setBounds(150, y + 5 * spacing, 100, 30);

        lblUsername.setFont(new Font("Arial", Font.PLAIN, 18));
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 18));
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 16));
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        btnLogin.setFont(new Font("Arial", Font.BOLD, 18));
        btnLogin.setBackground(new Color(52, 152, 219));
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setBorderPainted(false);
        btnLogin.setOpaque(true);

        loginPanel.add(lblHeader);
        loginPanel.add(lblUsername);
        loginPanel.add(txtUsername);
        loginPanel.add(lblPassword);
        loginPanel.add(txtPassword);
        loginPanel.add(btnLogin);

        panel.add(loginPanel);
        add(panel);

        JLabel headerLabel = new JLabel("ADMIN LOGIN PAGE", JLabel.CENTER);
        headerLabel.setFont(new Font(null, Font.BOLD, 30));
        headerLabel.setForeground(Color.white);
        getContentPane().setBackground(Color.GRAY);
        add(headerLabel, BorderLayout.NORTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void addComponents() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu aboutMenu = new JMenu("About");
        menuBar.add(aboutMenu);

        JMenu backMenu = new JMenu("Back");
        menuBar.add(backMenu);

        JMenu exitMenu = new JMenu("Exit");
        menuBar.add(exitMenu);

        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenu.add(aboutMenuItem);

        JMenuItem backMenuItem = new JMenuItem("Back");
        backMenu.add(backMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenu.add(exitMenuItem);
    }

    public String getUsername() {
        return txtUsername.getText();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }

    public void addAboutListener(ActionListener listener) {
        JMenuItem aboutMenuItem = ((JMenu) getJMenuBar().getMenu(0)).getItem(0);
        aboutMenuItem.addActionListener(listener);
    }

    public void addBackListener(ActionListener listener) {
        JMenuItem backMenuItem = ((JMenu) getJMenuBar().getMenu(1)).getItem(0);
        backMenuItem.addActionListener(listener);
    }

    public void addExitListener(ActionListener listener) {
        JMenuItem exitMenuItem = ((JMenu) getJMenuBar().getMenu(2)).getItem(0);
        exitMenuItem.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}

