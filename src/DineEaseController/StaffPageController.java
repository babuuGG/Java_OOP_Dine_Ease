package DineEaseController;



import DIneEaseModel.HomepageModel;
import DIneEaseModel.StaffLoginModel;
import DineEaseDatabase.ItemInfoDAO;
import DineEaseDatabase.StaffLoginDAO;



import DineEaseVIew.HomepageView;
import DineEaseVIew.ItemInfoView;
import DineEaseVIew.StaffLoginView;

import DineEaseVIew.StaffPageView;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

public class StaffPageController {
    private StaffPageView view;

    public StaffPageController(StaffPageView view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getAboutMenuItem().addActionListener(e -> showAboutDialog());
        view.getBackMenuItem().addActionListener(e -> goBack());
        view.getExitMenuItem().addActionListener(e -> exitApplication());

        showButtonDemo();
    }

    private void showAboutDialog() {
        JOptionPane.showMessageDialog(view.getMainFrame(), "About Button clicked!");
    }

    private void goBack() {
        view.getMainFrame().dispose();
        new StaffLoginController(new StaffLoginDAO(), new StaffLoginView());
    }

    private void exitApplication() {
        int option = JOptionPane.showConfirmDialog(view.getMainFrame(), "Are you sure you want to exit?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void showButtonDemo() {
        createButton("Menu Info", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            ItemInfoView itemView = new ItemInfoView();
            ItemInfoDAO itemDAO = new ItemInfoDAO();
            new ItemInfoController(itemView, itemDAO);
            }
        });

        createButton("Order Placement", e -> {
            view.getMainFrame().dispose();
            OrderPlacement orderp= new OrderPlacement();
            
        });

        createButton("Go Back", e -> {
            view.getMainFrame().dispose();
            new StaffLoginController(new StaffLoginDAO(), new StaffLoginView());

        });
        view.getMainFrame().setLocationRelativeTo(null);
    }

    private void createButton(String buttonText, ActionListener actionListener) {
        JButton button = new JButton(buttonText);
        button.setFont(new Font(null, Font.BOLD, 24));
        button.setForeground(Color.white);
        button.setBackground(new Color(0, 102, 204));
        button.setFocusPainted(false);
        button.setBorder((Border) new RoundedBorder(Color.white, 30));
        button.setPreferredSize(new Dimension(220, 70));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0, 153, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 102, 204));
            }
        });

        button.addActionListener(actionListener);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(15, 0, 15, 0);
        view.getButtonPanel().add(button, constraints);
    }

    private static class RoundedBorder extends AbstractBorder {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StaffPageView view = new StaffPageView();
            new StaffPageController(view);
        });
    }
}