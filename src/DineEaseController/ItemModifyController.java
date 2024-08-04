
package DineEaseController;

import DIneEaseModel.Item;
import DineEaseDatabase.ItemInfoDAO;
import DineEaseDatabase.ItemModifyDAO;
import DineEaseVIew.ItemInfoView;
import DineEaseVIew.ItemModifyView;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;
import javax.swing.SwingUtilities;

public class ItemModifyController {
    private ItemModifyView view;
    private ItemModifyDAO model;

    public ItemModifyController(ItemModifyView view, ItemModifyDAO model) {
        this.view = view;
        this.model = model;

        view.getSearchButton().addActionListener(e -> performSearch());
        view.getRefreshButton().addActionListener(e -> refreshTable());
        view.getInsertButton().addActionListener(e -> insertItem());
        view.getUpdateButton().addActionListener(e -> updateItem());
        view.getDeleteButton().addActionListener(e -> deleteItem());
    }

    private void performSearch() {
        String searchTerm = view.getSearchField().getText().trim();
        if (!searchTerm.isEmpty()) {
            try {
                List<Item> items = model.searchItems(searchTerm);
                updateTable(items);
            } catch (SQLException ex) {
                view.showMessage("Error searching items.");
            }
        }
    }

    private void refreshTable() {
        try {
            List<Item> items = model.getAllItems();
            updateTable(items);
        } catch (SQLException ex) {
            view.showMessage("Error refreshing table.");
        }
    }

    private void insertItem() {
        ItemDialog dialog = new ItemDialog(view.getMainFrame(), "Insert Item");
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            Item item = new Item(dialog.getID(), dialog.getName(), dialog.getPrice());
            try {
                model.insertItem(item);
                refreshTable();
            } catch (SQLException ex) {
                view.showMessage("Error inserting item.");
            }
        }
    }

    private void updateItem() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) view.getTable().getValueAt(selectedRow, 0);
            ItemDialog dialog = new ItemDialog(view.getMainFrame(), "Update Item");
            dialog.setID(id);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                Item item = new Item(id, dialog.getName(), dialog.getPrice());
                try {
                    model.updateItem(item);
                    refreshTable();
                } catch (SQLException ex) {
                    view.showMessage("Error updating item.");
                }
            }
        }
    }

    private void deleteItem() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) view.getTable().getValueAt(selectedRow, 0);
            try {
                model.deleteItem(id);
                refreshTable();
            } catch (SQLException ex) {
                view.showMessage("Error deleting item.");
            }
        }
    }

    private void updateTable(List<Item> items) {
        String[] columnNames = {"ID", "Food Name", "Price"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (Item item : items) {
            tableModel.addRow(new Object[]{item.getId(), item.getName(), item.getPrice()});
        }
        view.setTableModel(tableModel);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ItemModifyView view = new ItemModifyView();
            ItemModifyDAO dao = new ItemModifyDAO();
            new ItemModifyController(view, dao);
        });
    }
}
