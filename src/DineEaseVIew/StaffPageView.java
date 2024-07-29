package DineEaseVIew;

import javax.swing.*;
import java.awt.*;

public class StaffPageView {
    private JFrame mainFrame;
    private JPanel buttonPanel;
    private JLabel headerLabel;
    private Image backgroundImage;

    public StaffPageView() {
        loadImage();
        prepareGUI();
    }

    private void loadImage() {
        try {
            backgroundImage = new ImageIcon("Resources/Staffpage.jpg").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Staff Page");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(screenSize.width, screenSize.height);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        headerLabel = new JLabel("STAFF PAGE", JLabel.CENTER);
        headerLabel.setFont(new Font(null, Font.BOLD, 30));
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setBackground(Color.BLUE);
        mainFrame.add(headerLabel, BorderLayout.NORTH);

        buttonPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        buttonPanel.setOpaque(false); // Ensure the panel is transparent

        mainFrame.add(buttonPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public JLabel getHeaderLabel() {
        return headerLabel;
    }

   
}
