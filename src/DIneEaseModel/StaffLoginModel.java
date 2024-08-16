package DIneEaseModel;

import DineEaseDatabase.StaffLoginDAO;

public class StaffLoginModel {
    private StaffLoginDAO staffLoginDAO;

    public StaffLoginModel() {
        this.staffLoginDAO = new StaffLoginDAO();
    }

    public boolean validateLogin(String username, String password) {
        return staffLoginDAO.validateLogin(username, password);
    }

    public boolean resetPassword(String username, String phoneNumber, String newPassword) {
        return staffLoginDAO.resetPassword(username, phoneNumber, newPassword);
    }
}
