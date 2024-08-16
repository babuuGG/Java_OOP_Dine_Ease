/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseVIew;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author roshankhadka
 */
public class OpeningPageView {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton btnGetStarted;
    private JLabel backgroundLabel;

    public OpeningPageView() {
        createUI();
        setLayout();
    }

    public void createUI() {
        frame = new JFrame("Welcome to Dine-Ease");
        mainPanel = new JPanel();
        btnGetStarted = new JButton("Get Started");
        
        // Set button properties
        btnGetStarted.setBounds(320, 35, 150, 40);
        btnGetStarted.setFont(new Font("Arial", Font.BOLD, 20));
        btnGetStarted.setFocusPainted(false);
        btnGetStarted.setBackground(Color.BLUE);
        btnGetStarted.setForeground(Color.BLACK);
        btnGetStarted.setBorderPainted(false);
        btnGetStarted.setOpaque(true);
        

        // Load and set background image
        ImageIcon backgroundImg = new ImageIcon("Resources/ooo.png");
        backgroundLabel = new JLabel(backgroundImg);
        backgroundLabel.setBounds(0, 0, 800, 470);

        // Add components to panel
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.black);
        mainPanel.add(btnGetStarted);
        mainPanel.add(backgroundLabel);
    }

    public void setLayout() {
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    public JButton getBtnGetStarted() {
        return btnGetStarted;
    }

    public JFrame getFrame() {
        return frame;
    }
}
    

