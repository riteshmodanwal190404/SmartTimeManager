package timemanager.ui;

import timemanager.dao.TaskDAO;
import timemanager.model.Task;

import javax.swing.*;
import java.awt.*;

public class AddTaskFrame extends JFrame {

    public AddTaskFrame(int userId) {

        setTitle("Add New Task");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(40, 40, 120, 25);
        add(lblTitle);

        JTextField txtTitle = new JTextField();
        txtTitle.setBounds(160, 40, 180, 25);
        add(txtTitle);

        JLabel lblDesc = new JLabel("Description:");
        lblDesc.setBounds(40, 90, 120, 25);
        add(lblDesc);

        JTextField txtDesc = new JTextField();
        txtDesc.setBounds(160, 90, 180, 25);
        add(txtDesc);

        JLabel lblDue = new JLabel("Due Date:");
        lblDue.setBounds(40, 140, 120, 25);
        add(lblDue);

        JTextField txtDue = new JTextField();
        txtDue.setBounds(160, 140, 180, 25);
        add(txtDue);

        JLabel lblPriority = new JLabel("Priority:");
        lblPriority.setBounds(40, 190, 120, 25);
        add(lblPriority);

        JComboBox<String> cmbPriority = new JComboBox<>(new String[]{
                "LOW", "MEDIUM", "HIGH"
        });
        cmbPriority.setBounds(160, 190, 180, 25);
        add(cmbPriority);

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(40, 240, 120, 25);
        add(lblStatus);

        JComboBox<String> cmbStatus = new JComboBox<>(new String[]{
                "PENDING", "IN PROGRESS", "COMPLETED"
        });
        cmbStatus.setBounds(160, 240, 180, 25);
        add(cmbStatus);

        JButton btnSave = new JButton("Save Task");
        btnSave.setBounds(130, 300, 140, 35);
        btnSave.setBackground(new Color(33, 150, 243));
        btnSave.setForeground(Color.WHITE);
        add(btnSave);

        btnSave.addActionListener(e -> {
            String title = txtTitle.getText().trim();
            String desc = txtDesc.getText().trim();
            String dueDate = txtDue.getText().trim();
            String priority = cmbPriority.getSelectedItem().toString();
            String status = cmbStatus.getSelectedItem().toString();

            Task t = new Task(userId, title, desc, dueDate, priority, status);
            TaskDAO dao = new TaskDAO();

            if (dao.addTask(t)) {
                JOptionPane.showMessageDialog(this, "Task added successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error adding task!");
            }
        });

        setVisible(true);
    }
}
