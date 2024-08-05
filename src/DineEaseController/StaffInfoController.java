/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseController;

import DIneEaseModel.Staff;
import DineEaseVIew.StaffInfoView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class StaffInfoController {
    private StaffInfoView view;
   

    public StaffInfoController(StaffInfoView view) {
        this.view = view;
    
        this.view.addInsertButtonListener(new InsertButtonListener());
        this.view.addUpdateButtonListener(new UpdateButtonListener());
        this.view.addDeleteButtonListener(new DeleteButtonListener());
        this.view.addSearchButtonListener(new SearchButtonListener());
        this.view.addRefreshButtonListener(new RefreshButtonListener());
        refreshTable();
    }

    private void refreshTable() {
        try {
            List<Staff> staffList = dao.getAllStaff();
            DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID", "Username", "Password", "Address", "Contact", "Email"}, 0);
            for (Staff staff : staffList) {
                tableModel.addRow(new Object[]{
                        staff.getId(),
                        staff.getUsername(),
                        staff.getPassword(),
                        staff.getAddress(),
                        staff.getContact(),
                        staff.getEmail()
                });
            }
            view.setTableModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
            view.showMessage("Error loading staff data.");
        }
    }

    private class InsertButtonListener implements ActionListener {
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
                    dao.insertStaff(staff);
                    refreshTable();
                    view.showMessage("Staff inserted successfully.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    view.showMessage("Error inserting staff.");
                }
            }
        }
    }

    private class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getSelectedRow();
            if (selectedRow >= 0) {
                Staff staff = new Staff();
                staff.setId((int) view.getValueAt(selectedRow, 0));
                staff.setUsername((String) view.getValueAt(selectedRow, 1));
                staff.setPassword((String) view.getValueAt(selectedRow, 2));
                staff.setAddress((String) view.getValueAt(selectedRow, 3));
                staff.setContact((long) view.getValueAt(selectedRow, 4));
                staff.setEmail((String) view.getValueAt(selectedRow, 5));

                StaffDialog dialog = new StaffDialog(view.mainFrame, "Update Staff");
              
                dialog.setVisible(true);

                if (dialog.isConfirmed()) {
                    staff.setUsername(dialog.getUsername());
                    staff.setPassword(dialog.getPassword());
                    staff.setAddress(dialog.getAddress());
                    staff.setContact(dialog.getContact());
                    staff.setEmail(dialog.getEmail());

                    try {
                        dao.updateStaff(staff);
                        refreshTable();
                        view.showMessage("Staff updated successfully.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        view.showMessage("Error updating staff.");
                    }
                }
            } else {
                view.showMessage("No staff selected.");
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) view.getValueAt(selectedRow, 0);

                int option = JOptionPane.showConfirmDialog(view.mainFrame, "Are you sure you want to delete this staff?",
                        "Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (option == JOptionPane.YES_OPTION) {
                    try {
                        dao.deleteStaff(id);
                        refreshTable();
                        view.showMessage("Staff deleted successfully.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        view.showMessage("Error deleting staff.");
                    }
                }
            } else {
                view.showMessage("No staff selected.");
            }
        }
    }

    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchTerm = view.getSearchTerm();
            if (!searchTerm.isEmpty()) {
                try {
                    List<Staff> staffList = dao.searchStaff(searchTerm);
                    DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID", "Username", "Password", "Address", "Contact", "Email"}, 0);
                    for (Staff staff : staffList) {
                        tableModel.addRow(new Object[]{
                                staff.getId(),
                                staff.getUsername(),
                                staff.getPassword(),
                                staff.getAddress(),
                                staff.getContact(),
                                staff.getEmail()
                        });
                    }
                    view.setTableModel(tableModel);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    view.showMessage("Error searching staff.");
                }
            } else {
                view.showMessage("Please enter a search term.");
            }
        }
    }

    private class RefreshButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTable();
        }
    }
}

