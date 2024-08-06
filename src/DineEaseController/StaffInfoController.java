package DineEaseController;

import DIneEaseModel.Staff;
import DineEaseDatabase.StaffInfoDAO;
import DineEaseVIew.StaffInfoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class StaffInfoController {
    private StaffInfoView view;
    private StaffInfoDAO model;

    public StaffInfoController(StaffInfoView view, StaffInfoDAO model) {
        this.view = view;
        this.model = model;

        this.view.addInsertButtonListener(new InsertButtonListener());
        this.view.addUpdateButtonListener(new UpdateButtonListener());
        this.view.addDeleteButtonListener(new DeleteButtonListener());
        this.view.addSearchButtonListener(new SearchButtonListener());
        this.view.addRefreshButtonListener(new RefreshButtonListener());

        refreshTable();
    }

    class InsertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StaffDialog dialog = new StaffDialog(view.mainFrame, "Insert Staff");
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                Staff staff = new Staff();
                staff.setUsername(dialog.getUsername());
                staff.setPassword(dialog.getPassword());
                staff.setAddress(dialog.getAddress());
                staff.setContact(dialog.getContact());
                staff.setEmail(dialog.getEmail());

                try {
                    model.insertStaff(staff);
                    view.showMessage("Staff inserted successfully!");
                    refreshTable();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    view.showMessage("Error inserting staff.");
                }
            }
        }
    }

    class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) view.getValueAt(selectedRow, 0);
                StaffDialog dialog = new StaffDialog(view.mainFrame, "Update Staff");
                dialog.setID(id);
                dialog.setVisible(true);

                if (dialog.isConfirmed()) {
                    Staff staff = new Staff();
                    staff.setId(id);
                    staff.setUsername(dialog.getUsername());
                    staff.setPassword(dialog.getPassword());
                    staff.setAddress(dialog.getAddress());
                    staff.setContact(dialog.getContact());
                    staff.setEmail(dialog.getEmail());

                    try {
                        model.updateStaff(staff);
                        view.showMessage("Staff updated successfully!");
                        refreshTable();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        view.showMessage("Error updating staff.");
                    }
                }
            }
        }
    }

    class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) view.getValueAt(selectedRow, 0);
                try {
                    model.deleteStaff(id);
                    view.showMessage("Staff deleted successfully!");
                    refreshTable();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    view.showMessage("Error deleting staff.");
                }
            }
        }
    }

    class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchTerm = view.getSearchTerm();
            if (!searchTerm.isEmpty()) {
                try {
                    List<Staff> staffList = model.searchStaff(searchTerm);
                    view.updateTable(staffList);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    view.showMessage("Error searching staff.");
                }
            }
        }
    }

    class RefreshButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTable();
        }
    }

    private void refreshTable() {
        try {
            List<Staff> staffList = model.getAllStaff();
            view.updateTable(staffList);
        } catch (SQLException ex) {
            ex.printStackTrace();
            view.showMessage("Error loading staff data.");
        }
    }
}
