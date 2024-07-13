package DineEaseView;

import javax.swing.*;
import java.awt.*;

public class HomepageView {
    private JFrame frame;
    private JPanel backgroundPanel;

    public HomepageView() {
        createUI();
    }

    private void createUI() {
        frame = new JFrame("Home Page");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        backgroundPanel = new BackgroundPanel(new ImageIcon("Resources/Homepage.png").getImage());
        frame.add(backgroundPanel, BorderLayout.CENTER);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getBackgroundPanel() {
        return backgroundPanel;
    }

    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image image) {
            this.backgroundImage = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
