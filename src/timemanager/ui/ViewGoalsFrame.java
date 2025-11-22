package timemanager.ui;

import timemanager.dao.GoalDAO;
import timemanager.model.Goal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewGoalsFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private int userId;

    public ViewGoalsFrame(int userId) {
        this.userId = userId;

        setTitle("View Goals");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // TABLE MODEL
        model = new DefaultTableModel(new String[]{
                "ID", "Title", "Description", "Target Date", "Status"
        }, 0);

        table = new JTable(model);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // BUTTON PANEL (Add + Edit + Delete + Refresh)
        JPanel bottomPanel = new JPanel();

        JButton btnAdd = new JButton("Add Goal");
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

        // ADD ALL BUTTONS
        bottomPanel.add(btnAdd);
        bottomPanel.add(btnEdit);
        bottomPanel.add(btnDelete);
        bottomPanel.add(btnRefresh);

        add(bottomPanel, BorderLayout.SOUTH);

        // BUTTON ACTIONS
        btnAdd.addActionListener(e -> new AddGoalFrame(userId));

        btnRefresh.addActionListener(e -> loadGoals());

        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a goal to edit!");
                return;
            }

            int id = (int) model.getValueAt(row, 0);
            String title = (String) model.getValueAt(row, 1);
            String desc = (String) model.getValueAt(row, 2);
            String target = (String) model.getValueAt(row, 3);
            String status = (String) model.getValueAt(row, 4);

            new EditGoalFrame(id, userId, title, desc, target, status);
        });

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a goal to delete!");
                return;
            }

            int id = (int) model.getValueAt(row, 0);

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this goal?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                GoalDAO dao = new GoalDAO();
                boolean deleted = dao.deleteGoal(id);

                if (deleted) {
                    JOptionPane.showMessageDialog(this, "Goal deleted successfully!");
                    loadGoals();
                } else {
                    JOptionPane.showMessageDialog(this, "Error deleting goal");
                }
            }
        });

        // LOAD GOALS INITIALLY
        loadGoals();

        setVisible(true);
    }

    private void loadGoals() {
        model.setRowCount(0);

        GoalDAO dao = new GoalDAO();
        List<Goal> list = dao.getGoalsByUser(userId);

        for (Goal g : list) {
            model.addRow(new Object[]{
                    g.getId(),
                    g.getTitle(),
                    g.getDescription(),
                    g.getTargetDate(),
                    g.getStatus()
            });
        }
    }
}
