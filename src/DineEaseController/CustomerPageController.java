/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseController;

import DIneEaseModel.CustomerPageModel;
import DIneEaseModel.HomepageModel;
import DineEaseDatabase.ItemInfoDAO;
import DineEaseVIew.CustomerPageView;
import DineEaseVIew.HomepageView;
import DineEaseVIew.ItemInfoView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomerPageController {
    private CustomerPageView view;

    public CustomerPageController(CustomerPageView view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getAboutMenuItem().addActionListener(e -> showMessage("About Button clicked!"));
        view.getBackMenuItem().addActionListener(e -> goBack());
        view.getExitMenuItem().addActionListener(e -> exitApp());

        createButton("Menu Info", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            ItemInfoView itemView = new ItemInfoView();
            ItemInfoDAO itemDAO = new ItemInfoDAO();
            new ItemInfoController(itemView, itemDAO);
            }
        });

        createButton("Exit", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMessage("Do you relly want to exit?");
                System.exit(0);
            }
        });

        createButton("Go Back", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMessage("Go Back Button clicked!");
                view.getMainFrame().dispose();
                 new HomepageController(new HomepageModel(), new HomepageView());
            }
        });
    }

    private void createButton(String buttonText, ActionListener actionListener) {
        JButton button = new JButton(buttonText);
        button.setFont(new Font(null, Font.BOLD, 24));
        button.setForeground(Color.white);
        button.setBackground(new Color(0, 102, 204));
        button.setFocusPainted(false);
        button.setBorder(new CustomerPageView.RoundedBorder(Color.white, 30));
        button.setPreferredSize(new Dimension(220, 70));
        button.setBorderPainted(false);
        button.setOpaque(true);

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
        view.addButton(button, constraints);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(view.getMainFrame(), message);
    }

    private void goBack() {
        view.getMainFrame().dispose();
         new HomepageController(new HomepageModel(), new HomepageView());
    }

    private void exitApp() {
        int option = JOptionPane.showConfirmDialog(view.getMainFrame(), "Are you sure you want to exit?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerPageView view = new CustomerPageView();
            new CustomerPageController(view);
        });
    }
}
