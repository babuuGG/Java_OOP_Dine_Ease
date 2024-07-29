/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseVIew;





import javax.swing.*;
import java.awt.*;

public class AdminLoginView extends JFrame {
    private JPanel panel;
    private JLabel lblHeader;

    public AdminLoginView() {
        createUI();
     
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

     
        lblHeader = new JLabel("ADMIN LOGIN PAGE", JLabel.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 28));
        lblHeader.setForeground(Color.WHITE);

     
        panel.setLayout(new BorderLayout());
        panel.add(lblHeader, BorderLayout.NORTH);

       
        add(panel);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
