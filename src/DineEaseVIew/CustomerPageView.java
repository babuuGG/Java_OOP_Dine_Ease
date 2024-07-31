/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseVIew;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class CustomerPageView {
    private JFrame mainFrame;
    private JPanel buttonPanel;
    private JLabel headerLabel;
    private JMenuItem aboutMenuItem;
    private JMenuItem backMenuItem;
    private JMenuItem exitMenuItem;

    public CustomerPageView() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Customer Page");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(screenSize.width, screenSize.height);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a custom panel with a background image
        ImagePanel backgroundPanel = new ImagePanel(new ImageIcon("Resources/CustomerPage.jpg").getImage());
        backgroundPanel.setLayout(new BorderLayout());
        mainFrame.setContentPane(backgroundPanel);

        headerLabel = new JLabel("CUSTOMER PAGE", JLabel.CENTER);
        headerLabel.setFont(new Font(null, Font.BOLD, 30));
        headerLabel.setForeground(Color.black);
    
        mainFrame.add(headerLabel, BorderLayout.NORTH);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        buttonPanel.setOpaque(false); // Make the button panel transparent

        mainFrame.add(buttonPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

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

        mainFrame.setLocationRelativeTo(null);
    }

    public void addButton(JButton button, GridBagConstraints constraints) {
        buttonPanel.add(button, constraints);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JMenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }

    public JMenuItem getBackMenuItem() {
        return backMenuItem;
    }

    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    // Custom JPanel to paint a background image
    class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static class RoundedBorder extends AbstractBorder {
        private final Color borderColor;
        private final int borderRadius;

        public RoundedBorder(Color borderColor, int borderRadius) {
            this.borderColor = borderColor;
            this.borderRadius = borderRadius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(borderColor);
            g2.drawRoundRect(x, y, width - 1, height - 1, borderRadius, borderRadius);

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(borderRadius, borderRadius, borderRadius, borderRadius);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(borderRadius, borderRadius, borderRadius, borderRadius);
            return insets;
        }
    }
}
