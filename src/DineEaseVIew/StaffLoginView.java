package DineEaseVIew;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author roshankhadka
 */
public class StaffLoginView {
    public JFrame frame;
    public JPanel panel;
    public JLabel lblHeader;
    public JMenuItem aboutMenuItem, backMenuItem, exitMenuItem;

    public StaffLoginView() {
        createUI();
        setLayout();
    }

    private void createUI() {
        frame = new JFrame("Dine-Ease: Your Virtual Manager");
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(g);
            }

            private void setBackground(Graphics g) {
                ImageIcon backgroundImage = new ImageIcon("Resources/StaffLogin.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        lblHeader = new JLabel("STAFF LOGIN PAGE");

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

    private void setLayout() {
        panel.setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        lblHeader.setBounds((screenSize.width - 300) / 2, 50, 300, 50);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 28));
        lblHeader.setForeground(new Color(44, 62, 80));

        panel.add(lblHeader);
        frame.add(panel);
    }

    public void show() {
        frame.setVisible(true);
    }
}
