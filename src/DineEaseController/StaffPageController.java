package DineEaseController;

import DIneEaseModel.HomepageModel;
import DineEaseVIew.HomepageView;
import DineEaseVIew.StaffPageView;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class StaffPageController {
    private StaffPageView view;

    public StaffPageController(StaffPageView view) {
        this.view = view;
        initController();
    }

   

    private void showAboutDialog() {
        JOptionPane.showMessageDialog(view.getMainFrame(), "About Button clicked!");
    }

    private void goBack() {
        view.getMainFrame().dispose();
        new HomepageController(new HomepageModel(), new HomepageView());
    }

    private void exitApplication() {
        int option = JOptionPane.showConfirmDialog(view.getMainFrame(), "Are you sure you want to exit?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StaffPageView view = new StaffPageView();
            new StaffPageController(view);
        });
    }

    private void initController() {
        
    }
}
