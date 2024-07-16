package DineEaseController;

import DIneEaseModel.HomepageModel;
import DIneEaseModel.OpeningPageModel;
import DineEaseVIew.HomepageView;

import DineEaseVIew.OpeningPageView;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OpeningPageController {
    private OpeningPageModel model;
    private OpeningPageView view;

    public OpeningPageController(OpeningPageModel model, OpeningPageView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnGetStarted().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getBtnGetStarted()) {
                    // Dispose the OpeningPage frame
                    view.getFrame().dispose();
                    // Initialize the Homepage MVC components
                    HomepageModel homeModel = new HomepageModel();
                    HomepageView homeView = new HomepageView();
                    new HomepageController(homeModel, homeView);
                }
            }
        });

        view.getBtnGetStarted().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                view.getBtnGetStarted().setBackground(Color.YELLOW);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                view.getBtnGetStarted().setBackground(Color.GREEN);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OpeningPageModel model = new OpeningPageModel();
            OpeningPageView view = new OpeningPageView();
            new OpeningPageController(model, view);
        });
    }
}
