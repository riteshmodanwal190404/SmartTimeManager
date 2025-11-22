package timemanager.ui;

import timemanager.dao.SettingsDAO;
import timemanager.model.Settings;

import javax.swing.*;
import java.awt.*;

public class SettingsFrame extends JFrame {

    public SettingsFrame() {

        setTitle("Application Settings");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitle = new JLabel("Application Title:");
        lblTitle.setBounds(50, 50, 150, 25);
        add(lblTitle);

        JTextField txtTitle = new JTextField();
        txtTitle.setBounds(200, 50, 220, 25);
        add(txtTitle);

        JLabel lblTheme = new JLabel("Theme:");
        lblTheme.setBounds(50, 110, 150, 25);
        add(lblTheme);

        String[] themes = {"LIGHT", "DARK"};
        JComboBox<String> cmbTheme = new JComboBox<>(themes);
        cmbTheme.setBounds(200, 110, 220, 25);
        add(cmbTheme);

        JLabel lblPass = new JLabel("Admin Password:");
        lblPass.setBounds(50, 170, 150, 25);
        add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(200, 170, 220, 25);
        add(txtPass);

        JButton btnSave = new JButton("Save Settings");
        btnSave.setBounds(160, 250, 150, 40);
        btnSave.setBackground(new Color(33, 150, 243));
        btnSave.setForeground(Color.WHITE);
        add(btnSave);

        // Load Existing Settings
        SettingsDAO dao = new SettingsDAO();
        Settings s = dao.loadSettings();

        txtTitle.setText(s.getAppTitle());
        cmbTheme.setSelectedItem(s.getTheme());
        txtPass.setText(s.getAdminPassword());

        // SAVE BUTTON
        btnSave.addActionListener(e -> {

            s.setAppTitle(txtTitle.getText());
            s.setTheme(cmbTheme.getSelectedItem().toString());
            s.setAdminPassword(new String(txtPass.getPassword()));

            if (dao.saveSettings(s)) {
                JOptionPane.showMessageDialog(this, "Settings Saved Successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save settings!");
            }
        });

        setVisible(true);
    }
}
