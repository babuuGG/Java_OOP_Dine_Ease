/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseController;

import DIneEaseModel.OpeningPageModel;
import DineEaseVIew.OpeningPageView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

/**
 *
 * @author roshankhadka
 */
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
                
                    new Homepage(); 
                }
            }
        });

        
        view.getBtnGetStarted().addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                view.getBtnGetStarted().setBackground(Color.YELLOW);
            }

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

