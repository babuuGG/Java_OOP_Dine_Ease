package DineEaseController;

import DIneEaseModel.HomepageModel;
import DineEaseView.HomepageView;

import javax.swing.SwingUtilities;

public class HomepageController {
    private HomepageModel model;
    private HomepageView view;

    public HomepageController(HomepageModel model, HomepageView view) {
        this.model = model;
        this.view = view;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomepageModel model = new HomepageModel();
            HomepageView view = new HomepageView();
            new HomepageController(model, view);
        });
    }
}
