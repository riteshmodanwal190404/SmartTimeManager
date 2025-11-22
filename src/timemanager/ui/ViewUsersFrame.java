package timemanager.ui;

import timemanager.dao.UserDAO;
import timemanager.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewUsersFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ViewUsersFrame() {

        setTitle("User Management");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{
                "ID", "Name", "Email", "Role"
        }, 0);

        table = new JTable(model);
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnDelete = new JButton("Delete User");
        btnDelete.setBackground(new Color(244, 67, 54));
        btnDelete.setForeground(Color.WHITE);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBackground(new Color(33, 150, 243));
        btnRefresh.setForeground(Color.WHITE);

        JPanel bottom = new JPanel();
        bottom.add(btnDelete);
        bottom.add(btnRefresh);

        add(bottom, BorderLayout.SOUTH);

        loadUsers();

        btnRefresh.addActionListener(e -> loadUsers());

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a user!");
                return;
            }

            int id = (int) model.getValueAt(row, 0);

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete this user?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                UserDAO dao = new UserDAO();
                boolean deleted = dao.deleteUser(id);

                if (deleted) {
                    JOptionPane.showMessageDialog(this, "User deleted!");
                    loadUsers();
                } else {
                    JOptionPane.showMessageDialog(this, "Error deleting user!");
                }
            }
        });

        setVisible(true);
    }

    private void loadUsers() {
        model.setRowCount(0);

        UserDAO dao = new UserDAO();
        List<User> list = dao.getAllUsers();

        for (User u : list) {
            model.addRow(new Object[]{
                    u.getId(),
                    u.getName(),
                    u.getEmail(),
                    u.getRole()
            });
        }
    }
}
