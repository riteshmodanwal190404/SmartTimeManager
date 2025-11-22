package timemanager.ui;

import timemanager.dao.TimeLogDAO;
import timemanager.model.TimeLog;

import javax.swing.*;
import java.awt.*;

public class EditTimeLogFrame extends JFrame {

    public EditTimeLogFrame(int id, int userId, String oldTask, int oldHours, String oldDate, String oldNotes) {

        setTitle("Edit Time Log");
        setSize(480, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Task Name
        JLabel lblTask = new JLabel("Task Name:");
        lblTask.setBounds(40, 40, 150, 25);
        add(lblTask);

        JTextField txtTask = new JTextField(oldTask);
        txtTask.setBounds(180, 40, 230, 25);
        add(txtTask);

        // Hours
        JLabel lblHours = new JLabel("Hours Spent:");
        lblHours.setBounds(40, 90, 150, 25);
        add(lblHours);

        JTextField txtHours = new JTextField(String.valueOf(oldHours));
        txtHours.setBounds(180, 90, 230, 25);
        add(txtHours);

        // Date
        JLabel lblDate = new JLabel("Log Date (YYYY-MM-DD):");
        lblDate.setBounds(40, 140, 180, 25);
        add(lblDate);

        JTextField txtDate = new JTextField(oldDate);
        txtDate.setBounds(220, 140, 190, 25);
        add(txtDate);

        // Notes
        JLabel lblNotes = new JLabel("Notes:");
        lblNotes.setBounds(40, 190, 150, 25);
        add(lblNotes);

        JTextField txtNotes = new JTextField(oldNotes);
        txtNotes.setBounds(180, 190, 230, 25);
        add(txtNotes);

        // Button
        JButton btnUpdate = new JButton("Update Log");
        btnUpdate.setBounds(180, 250, 150, 40);
        btnUpdate.setBackground(new Color(255, 152, 0));
        btnUpdate.setForeground(Color.WHITE);
        add(btnUpdate);

        // Update Action
        btnUpdate.addActionListener(e -> {
            String task = txtTask.getText().trim();
            String date = txtDate.getText().trim();
            String notes = txtNotes.getText().trim();
            String hoursStr = txtHours.getText().trim();

            if (task.isEmpty() || date.isEmpty() || notes.isEmpty() || hoursStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            int hours;
            try {
                hours = Integer.parseInt(hoursStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Hours must be a number!");
                return;
            }

            TimeLog log = new TimeLog(id, userId, task, hours, date, notes);
            TimeLogDAO dao = new TimeLogDAO();

            if (dao.updateTimeLog(log)) {
                JOptionPane.showMessageDialog(this, "Time log updated!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error updating time log!");
            }
        });

        setVisible(true);
    }
}
