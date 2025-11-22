package timemanager.ui;

import timemanager.dao.GoalDAO;
import timemanager.model.Goal;

import javax.swing.*;
import java.awt.*;

public class EditGoalFrame extends JFrame {

    public EditGoalFrame(int goalId, int userId, String oldTitle, String oldDesc, String oldDate, String oldStatus) {

        setTitle("Edit Goal");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lbl1 = new JLabel("Title:");
        lbl1.setBounds(40, 40, 120, 25);
        add(lbl1);

        JTextField txtTitle = new JTextField(oldTitle);
        txtTitle.setBounds(160, 40, 200, 25);
        add(txtTitle);

        JLabel lbl2 = new JLabel("Description:");
        lbl2.setBounds(40, 90, 120, 25);
        add(lbl2);

        JTextField txtDesc = new JTextField(oldDesc);
        txtDesc.setBounds(160, 90, 200, 25);
        add(txtDesc);

        JLabel lbl3 = new JLabel("Target Date:");
        lbl3.setBounds(40, 140, 120, 25);
        add(lbl3);

        JTextField txtDate = new JTextField(oldDate);
        txtDate.setBounds(160, 140, 200, 25);
        add(txtDate);

        JLabel lbl4 = new JLabel("Status:");
        lbl4.setBounds(40, 190, 120, 25);
        add(lbl4);

        JTextField txtStatus = new JTextField(oldStatus);
        txtStatus.setBounds(160, 190, 200, 25);
        add(txtStatus);

        JButton btnUpdate = new JButton("Update Goal");
        btnUpdate.setBounds(160, 240, 150, 35);
        btnUpdate.setBackground(new Color(33, 150, 243));
        btnUpdate.setForeground(Color.WHITE);
        add(btnUpdate);

        // UPDATE BUTTON ACTION
        btnUpdate.addActionListener(e -> {

            String newTitle = txtTitle.getText().trim();
            String newDesc = txtDesc.getText().trim();
            String newDate = txtDate.getText().trim();
            String newStatus = txtStatus.getText().trim();

            Goal g = new Goal(goalId, userId, newTitle, newDesc, newDate, newStatus);

            GoalDAO dao = new GoalDAO();
            boolean updated = dao.updateGoal(g);

            if (updated) {
                JOptionPane.showMessageDialog(this, "Goal updated successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error updating goal!");
            }
        });

        setVisible(true);
    }
}
