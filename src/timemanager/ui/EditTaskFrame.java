package timemanager.ui;

import timemanager.dao.TaskDAO;
import timemanager.model.Task;

import javax.swing.*;
import java.awt.*;

public class EditTaskFrame extends JFrame {

    public EditTaskFrame(int id, int userId, String oldTitle, String oldDesc,
                         String oldDue, String oldPriority, String oldStatus) {

        setTitle("Edit Task");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(40, 40, 120, 25);
        add(lblTitle);

        JTextField txtTitle = new JTextField(oldTitle);
        txtTitle.setBounds(160, 40, 180, 25);
        add(txtTitle);

        JLabel lblDesc = new JLabel("Description:");
        lblDesc.setBounds(40, 90, 120, 25);
        add(lblDesc);

        JTextField txtDesc = new JTextField(oldDesc);
        txtDesc.setBounds(160, 90, 180, 25);
        add(txtDesc);

        JLabel lblDue = new JLabel("Due Date:");
        lblDue.setBounds(40, 140, 120, 25);
        add(lblDue);

        JTextField txtDue = new JTextField(oldDue);
        txtDue.setBounds(160, 140, 180, 25);
        add(txtDue);

        JLabel lblPri = new JLabel("Priority:");
        lblPri.setBounds(40, 190, 120, 25);
        add(lblPri);

        JComboBox<String> cmbPriority = new JComboBox<>(new String[]{
                "LOW", "MEDIUM", "HIGH"
        });
        cmbPriority.setSelectedItem(oldPriority);
        cmbPriority.setBounds(160, 190, 180, 25);
        add(cmbPriority);

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(40, 240, 120, 25);
        add(lblStatus);

        JComboBox<String> cmbStatus = new JComboBox<>(new String[]{
                "PENDING", "IN PROGRESS", "COMPLETED"
        });
        cmbStatus.setSelectedItem(oldStatus);
        cmbStatus.setBounds(160, 240, 180, 25);
        add(cmbStatus);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(130, 300, 140, 35);
        btnUpdate.setBackground(new Color(255, 152, 0));
        btnUpdate.setForeground(Color.WHITE);
        add(btnUpdate);

        btnUpdate.addActionListener(e -> {
            String title = txtTitle.getText().trim();
            String desc = txtDesc.getText().trim();
            String dueDate = txtDue.getText().trim();
            String priority = cmbPriority.getSelectedItem().toString();
            String status = cmbStatus.getSelectedItem().toString();

            Task t = new Task(id, userId, title, desc, dueDate, priority, status);
            TaskDAO dao = new TaskDAO();

            if (dao.updateTask(t)) {
                JOptionPane.showMessageDialog(this, "Task updated successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error updating task!");
            }
        });

        setVisible(true);
    }
}
