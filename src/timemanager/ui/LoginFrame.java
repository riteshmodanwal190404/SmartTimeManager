package timemanager.ui;

import timemanager.dao.UserDAO;
import timemanager.model.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;

    public LoginFrame() {

        setTitle("Smart Time Manager – Login");
        setSize(650, 420);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setUndecorated(true);

        // ==== BACKGROUND ====
        JPanel bg = new JPanel();
        bg.setBackground(new Color(15, 23, 42));  // dark navy
        bg.setLayout(new GridBagLayout());
        add(bg, BorderLayout.CENTER);

        // ==== LOGIN CARD ====
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(420, 360));
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(229, 231, 235), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        bg.add(card);

        // ===== TITLE =====
        JLabel lblTitle = new JLabel("WELCOME BACK", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(new Color(31, 41, 55));
        lblTitle.setBounds(30, 20, 360, 30);
        card.add(lblTitle);

        JLabel lblSub = new JLabel("Login to Smart Time Manager", SwingConstants.CENTER);
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSub.setForeground(new Color(107, 114, 128));
        lblSub.setBounds(30, 55, 360, 25);
        card.add(lblSub);

        // EMAIL LABEL
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblEmail.setBounds(50, 100, 300, 20);
        lblEmail.setForeground(new Color(55, 65, 81));
        card.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(50, 125, 320, 32);
        txtEmail.setBorder(BorderFactory.createLineBorder(new Color(209, 213, 219)));
        txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));
        card.add(txtEmail);

        // PASSWORD LABEL
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblPassword.setBounds(50, 165, 300, 20);
        lblPassword.setForeground(new Color(55, 65, 81));
        card.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(50, 190, 320, 32);
        txtPassword.setBorder(BorderFactory.createLineBorder(new Color(209, 213, 219)));
        txtPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
        card.add(txtPassword);

        // LOGIN BUTTON
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(50, 235, 320, 40);
        btnLogin.setBackground(new Color(37, 99, 235));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.add(btnLogin);

        // CREATE ACCOUNT BUTTON
        JButton btnRegister = new JButton("Create New Account");
        btnRegister.setBounds(50, 285, 320, 35);
        btnRegister.setBackground(new Color(243, 244, 246));
        btnRegister.setForeground(new Color(55, 65, 81));
        btnRegister.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnRegister.setFocusPainted(false);
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegister.setBorder(BorderFactory.createLineBorder(new Color(209, 213, 219)));
        card.add(btnRegister);

        // CLOSE BUTTON
        JButton btnClose = new JButton("×");
        btnClose.setBounds(380, 10, 35, 35);
        btnClose.setBorder(null);
        btnClose.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnClose.setFocusPainted(false);
        btnClose.setForeground(new Color(107, 114, 128));
        btnClose.setBackground(Color.WHITE);
        btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.add(btnClose);

        btnClose.addActionListener(e -> System.exit(0));

        // LOGIN ACTION
        btnLogin.addActionListener(e -> doLogin());

        // REGISTER PAGE ACTION
        btnRegister.addActionListener(e -> {
            dispose();
            new RegisterFrame().setVisible(true);
        });
    }

    private void doLogin() {
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        UserDAO dao = new UserDAO();
        User user = dao.validateLogin(email, password);

        if (user != null) {
            dispose();

            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                new AdminDashboard(user.getName());
            } else {
                new UserDashboard(user.getName(), user.getId());
            }

        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!");
        }
    }
}
