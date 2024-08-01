package DineEaseController;

import DIneEaseModel.HomepageModel;
import DineEaseDatabase.ItemInfoDAO;
import DIneEaseModel.Item;
import DineEaseVIew.HomepageView;
import DineEaseVIew.ItemInfoView;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class ItemInfoController {
    private ItemInfoView view;
    private ItemInfoDAO dao;
    private DefaultTableModel tableModel;

    public ItemInfoController(ItemInfoView view, ItemInfoDAO dao) {
        this.view = view;
        this.dao = dao;
        initController();
    }

    private void initController() {
        view.getSearchButton().addActionListener(e -> performSearch());
        view.getRefreshButton().addActionListener(e -> refreshTable());
        view.getAboutMenuItem().addActionListener(e -> showAboutDialog());
        view.getBackMenuItem().addActionListener(e -> goBack());
        view.getExitMenuItem().addActionListener(e -> exitApplication());

        try {
            showAllItems();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void performSearch() {
        String searchTerm = view.getSearchField().getText().trim();
        if (!searchTerm.isEmpty()) {
            try {
                List<Item> items = dao.searchItems(searchTerm);
                updateTable(items);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(view.getMainFrame(), "Error performing search!");
            }
        }
    }

    private void refreshTable() {
        try {
            showAllItems();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAllItems() throws SQLException {
        List<Item> items = dao.getAllItems();
        updateTable(items);
    }

    private void updateTable(List<Item> items) {
        String[] columnNames = {"ID", "Food Name", "Price"};
        Object[][] data = new Object[items.size()][3];

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            data[i][0] = item.getId();
            data[i][1] = item.getName();
            data[i][2] = item.getPrice();
        }

        tableModel = new DefaultTableModel(data, columnNames);
        view.getTable().setModel(tableModel);

        view.getTable().getTableHeader().setFont(new Font(null, Font.BOLD, 18));
        view.getTable().setFont(new Font(null, Font.PLAIN, 16));
        view.getTable().setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        view.getTable().setDefaultRenderer(Object.class, centerRenderer);
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
            ItemInfoView view = new ItemInfoView();
            ItemInfoDAO dao = new ItemInfoDAO();
            new ItemInfoController(view, dao);
        });
    }
}
