package DineEaseController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffDialog extends JDialog {
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField addressField;
    private JTextField contactField;
    private JTextField emailField;
    private JButton confirmButton;
    private JButton cancelButton;
    private boolean confirmed;

    public StaffDialog(Frame parent, String title) {
        super(parent, title, true);
        prepareGUI();
    }

    private void prepareGUI() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField(20);
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JTextField(20);
        panel.add(passwordField);

        panel.add(new JLabel("Address:"));
        addressField = new JTextField(20);
        panel.add(addressField);

        panel.add(new JLabel("Contact:"));
        contactField = new JTextField(20);
        panel.add(contactField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        panel.add(emailField);

        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(getParent());
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return passwordField.getText().trim();
    }

    public String getAddress() {
        return addressField.getText().trim();
    }

    public long getContact() {
        try {
            return Long.parseLong(contactField.getText().trim());
        } catch (NumberFormatException e) {
            return 0; 
        }
    }

    public String getEmail() {
        return emailField.getText().trim();
    }

    public void setID(int id) {
        
    }
}
