package timemanager.ui;

import timemanager.dao.TimeLogDAO;
import timemanager.model.TimeLog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewTimeLogsFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private int userId;

    public ViewTimeLogsFrame(int userId) {
        this.userId = userId;

        setTitle("My Time Logs");
        setSize(850, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table Model
        model = new DefaultTableModel(new String[]{
                "ID", "Task Name", "Hours Spent", "Log Date", "Notes"
        }, 0);

        table = new JTable(model);
        table.setRowHeight(25);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom Panel Buttons: Add + Edit + Delete + Refresh
        JPanel bottom = new JPanel();

        JButton btnAdd = new JButton("Add Log");
        btnAdd.setBackground(new Color(0, 150, 136));
        btnAdd.setForeground(Color.WHITE);

        JButton btnEdit = new JButton("Edit");
        btnEdit.setBackground(new Color(255, 152, 0));
        btnEdit.setForeground(Color.WHITE);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBackground(new Color(244, 67, 54));
        btnDelete.setForeground(Color.WHITE);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBackground(new Color(33, 150, 243));
        btnRefresh.setForeground(Color.WHITE);

        bottom.add(btnAdd);
        bottom.add(btnEdit);
        bottom.add(btnDelete);
        bottom.add(btnRefresh);

        add(bottom, BorderLayout.SOUTH);

        // Add Time Log
        btnAdd.addActionListener(e -> new AddTimeLogFrame(userId));

        // Refresh
        btnRefresh.addActionListener(e -> loadLogs());

        // Edit Log
        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a log to edit!");
                return;
            }

            int id = (int) model.getValueAt(row, 0);
            String task = (String) model.getValueAt(row, 1);
            int hours = Integer.parseInt(model.getValueAt(row, 2).toString());
            String date = (String) model.getValueAt(row, 3);
            String notes = (String) model.getValueAt(row, 4);

            new EditTimeLogFrame(id, userId, task, hours, date, notes);
        });

        // Delete Log
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a time log to delete!");
                return;
            }

            int id = (int) model.getValueAt(row, 0);

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete this log?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                TimeLogDAO dao = new TimeLogDAO();
                boolean deleted = dao.deleteTimeLog(id);

                if (deleted) {
                    JOptionPane.showMessageDialog(this, "Time log deleted!");
                    loadLogs();
                } else {
                    JOptionPane.showMessageDialog(this, "Error deleting log!");
                }
            }
        });

        loadLogs();
        setVisible(true);
    }

    private void loadLogs() {
        model.setRowCount(0);

        TimeLogDAO dao = new TimeLogDAO();
        List<TimeLog> logs = dao.getLogsByUser(userId);

        for (TimeLog t : logs) {
            model.addRow(new Object[]{
                    t.getId(),
                    t.getActivity(),
                    t.getHours(),
                    t.getDate(),
                    t.getNotes()
            });

        }
    }
}
