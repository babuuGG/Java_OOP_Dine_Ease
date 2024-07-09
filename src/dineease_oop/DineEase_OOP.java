/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dineease_oop;

import DIneEaseModel.OpeningPageModel;
import DineEaseController.OpeningPageController;
import DineEaseVIew.OpeningPageView;
import javax.swing.SwingUtilities;

/**
 *
 * @author roshankhadka
 */
public class DineEase_OOP {

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Run the GUI construction on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Initialize the MVC components for the OpeningPage
                OpeningPageModel model = new OpeningPageModel();
                OpeningPageView view = new OpeningPageView();
                new OpeningPageController(model, view);
            }
        });
    }
}
