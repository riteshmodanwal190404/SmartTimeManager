package timemanager.ui;

import timemanager.dao.GoalDAO;
import timemanager.model.Goal;

import javax.swing.*;
import java.awt.*;

public class AddGoalFrame extends JFrame {

    public AddGoalFrame(int userId) {

        setTitle("Add New Goal");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitle = new JLabel("Goal Title:");
        lblTitle.setBounds(40, 40, 120, 25);
        add(lblTitle);

        JTextField txtTitle = new JTextField();
        txtTitle.setBounds(160, 40, 220, 25);
        add(txtTitle);

        JLabel lblDesc = new JLabel("Description:");
        lblDesc.setBounds(40, 90, 120, 25);
        add(lblDesc);

        JTextArea txtDesc = new JTextArea();
        txtDesc.setBounds(160, 90, 220, 80);
        txtDesc.setLineWrap(true);
        add(txtDesc);

        JLabel lblDate = new JLabel("Target Date (YYYY-MM-DD):");
        lblDate.setBounds(40, 190, 200, 25);
        add(lblDate);

        JTextField txtDate = new JTextField();
        txtDate.setBounds(240, 190, 140, 25);
        add(txtDate);

        JButton btnSave = new JButton("Save Goal");
        btnSave.setBounds(160, 250, 120, 35);
        btnSave.setBackground(new Color(33, 150, 243));
        btnSave.setForeground(Color.WHITE);
        add(btnSave);

        btnSave.addActionListener(e -> {
            String title = txtTitle.getText().trim();
            String desc = txtDesc.getText().trim();
            String date = txtDate.getText().trim();

            if (title.isEmpty() || desc.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            Goal g = new Goal(userId, title, desc, date, "PENDING");

            GoalDAO dao = new GoalDAO();
            boolean success = dao.addGoal(g);

            if (success) {
                JOptionPane.showMessageDialog(this, "Goal added successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error saving goal.");
            }
        });

        setVisible(true);
    }
}
