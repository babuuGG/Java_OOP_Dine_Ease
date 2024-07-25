package DineEaseController;

import DIneEaseModel.StaffLoginModel;
import DineEaseVIew.StaffLoginView;


/**
 *
 * @author 
 */
public class StaffLoginController {
    private StaffLoginView view;

    public StaffLoginController(StaffLoginModel staffModel, StaffLoginView view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.show();
    }
}
