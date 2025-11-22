package timemanager.ui;

import timemanager.dao.TimeLogDAO;
import timemanager.model.TimeLog;

import javax.swing.*;
import java.awt.*;

public class AddTimeLogFrame extends JFrame {

    public AddTimeLogFrame(int userId) {

        setTitle("Add Time Log");
        setSize(480, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Task Name
        JLabel lblTask = new JLabel("Task Name:");
        lblTask.setBounds(40, 40, 150, 25);
        add(lblTask);

        JTextField txtTask = new JTextField();
        txtTask.setBounds(180, 40, 230, 25);
        add(txtTask);

        // Hours
        JLabel lblHours = new JLabel("Hours:");
        lblHours.setBounds(40, 90, 150, 25);
        add(lblHours);

        JTextField txtHours = new JTextField();
        txtHours.setBounds(180, 90, 230, 25);
        add(txtHours);

        // Date
        JLabel lblDate = new JLabel("Date (YYYY-MM-DD):");
        lblDate.setBounds(40, 140, 180, 25);
        add(lblDate);

        JTextField txtDate = new JTextField();
        txtDate.setBounds(220, 140, 190, 25);
        add(txtDate);

        // Notes
        JLabel lblNotes = new JLabel("Notes:");
        lblNotes.setBounds(40, 190, 150, 25);
        add(lblNotes);

        JTextField txtNotes = new JTextField();
        txtNotes.setBounds(180, 190, 230, 25);
        add(txtNotes);

        // Add Button
        JButton btnAdd = new JButton("Add Log");
        btnAdd.setBounds(180, 250, 150, 40);
        btnAdd.setBackground(new Color(0, 150, 136));
        btnAdd.setForeground(Color.WHITE);
        add(btnAdd);

        // ADD ACTION
        btnAdd.addActionListener(e -> {
            String task = txtTask.getText().trim();
            String date = txtDate.getText().trim();
            String notes = txtNotes.getText().trim();
            String hoursStr = txtHours.getText().trim();

            if (task.isEmpty() || date.isEmpty() || hoursStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Task, Date, and Hours are required!");
                return;
            }

            int hours;
            try {
                hours = Integer.parseInt(hoursStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Hours must be a number!");
                return;
            }

            // ✔ Correct constructor with 5 arguments
            TimeLog log = new TimeLog(userId, task, hours, date, notes);

            TimeLogDAO dao = new TimeLogDAO();
            if (dao.addTimeLog(log)) {
                JOptionPane.showMessageDialog(this, "Time log added successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error adding time log!");
            }
        });

        setVisible(true);
    }
}
