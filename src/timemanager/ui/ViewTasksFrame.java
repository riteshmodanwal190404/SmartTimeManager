package timemanager.ui;

import timemanager.dao.TaskDAO;
import timemanager.model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewTasksFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private int userId;

    public ViewTasksFrame(int userId) {
        this.userId = userId;

        setTitle("View Tasks");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table Model
        model = new DefaultTableModel(new String[]{
                "ID", "Title", "Description", "Due Date", "Priority", "Status"
        }, 0);

        table = new JTable(model);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Buttons: Add + Edit + Delete + Refresh
        JPanel bottom = new JPanel();

        JButton btnAdd = new JButton("Add Task");
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

        // ADD TASK
        btnAdd.addActionListener(e -> new AddTaskFrame(userId));

        // REFRESH
        btnRefresh.addActionListener(e -> loadTasks());

        // EDIT TASK
        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a task to edit!");
                return;
            }

            int id = (int) model.getValueAt(row, 0);
            String title = (String) model.getValueAt(row, 1);
            String desc = (String) model.getValueAt(row, 2);
            String dueDate = (String) model.getValueAt(row, 3);
            String priority = (String) model.getValueAt(row, 4);
            String status = (String) model.getValueAt(row, 5);

            new EditTaskFrame(id, userId, title, desc, dueDate, priority, status);
        });

        // DELETE TASK
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a task to delete!");
                return;
            }

            int id = (int) model.getValueAt(row, 0);

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this task?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                TaskDAO dao = new TaskDAO();
                boolean deleted = dao.deleteTask(id);

                if (deleted) {
                    JOptionPane.showMessageDialog(this, "Task deleted successfully!");
                    loadTasks();
                } else {
                    JOptionPane.showMessageDialog(this, "Error deleting task!");
                }
            }
        });

        loadTasks();
        setVisible(true);
    }

    private void loadTasks() {
        model.setRowCount(0);

        TaskDAO dao = new TaskDAO();
        List<Task> list = dao.getTasksByUser(userId);

        for (Task t : list) {
            model.addRow(new Object[]{
                    t.getId(),
                    t.getTitle(),
                    t.getDescription(),
                    t.getDueDate(),
                    t.getPriority(),
                    t.getStatus()
            });
        }
    }
}
