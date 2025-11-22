package timemanager.ui;

import timemanager.dao.UserDAO;
import timemanager.model.User;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    public RegisterFrame() {

        setTitle("Create Account – Smart Time Manager");
        setSize(600, 480);
        setLocationRelativeTo(null);
        setUndecorated(true); // cleaner professional look
        setLayout(new BorderLayout());

        // ==== BACKGROUND ====
        JPanel bg = new JPanel(new GridBagLayout());
        bg.setBackground(new Color(15, 23, 42)); // dark navy
        add(bg, BorderLayout.CENTER);

        // ==== CARD PANEL ====
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(430, 400));
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(229, 231, 235), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        bg.add(card);

        // ==== HEADER ====
        JLabel lblTitle = new JLabel("CREATE ACCOUNT", SwingConstants.CENTER);
        lblTitle.setBounds(20, 20, 380, 30);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(new Color(31, 41, 55));
        card.add(lblTitle);

        JLabel lblSub = new JLabel("Sign up to Smart Time Manager", SwingConstants.CENTER);
        lblSub.setBounds(20, 50, 380, 25);
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSub.setForeground(new Color(107, 114, 128));
        card.add(lblSub);

        // ==== NAME FIELD ====
        JLabel lblName = new JLabel("Full Name");
        lblName.setBounds(50, 95, 300, 20);
        lblName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblName.setForeground(new Color(55, 65, 81));
        card.add(lblName);

        JTextField txtName = new JTextField();
        txtName.setBounds(50, 120, 320, 32);
        txtName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtName.setBorder(BorderFactory.createLineBorder(new Color(209, 213, 219)));
        card.add(txtName);

        // ==== EMAIL FIELD ====
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(50, 165, 300, 20);
        lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblEmail.setForeground(new Color(55, 65, 81));
        card.add(lblEmail);

        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(50, 190, 320, 32);
        txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtEmail.setBorder(BorderFactory.createLineBorder(new Color(209, 213, 219)));
        card.add(txtEmail);

        // ==== PASSWORD FIELD ====
        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(50, 235, 300, 20);
        lblPass.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblPass.setForeground(new Color(55, 65, 81));
        card.add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(50, 260, 320, 32);
        txtPass.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtPass.setBorder(BorderFactory.createLineBorder(new Color(209, 213, 219)));
        card.add(txtPass);

        // ==== REGISTER BUTTON ====
        JButton btnRegister = new JButton("Create Account");
        btnRegister.setBounds(50, 315, 320, 40);
        btnRegister.setBackground(new Color(37, 99, 235));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnRegister.setFocusPainted(false);
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.add(btnRegister);

        // ==== CLOSE BUTTON ====
        JButton btnClose = new JButton("×");
        btnClose.setBounds(380, 10, 35, 35);
        btnClose.setBorder(null);
        btnClose.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnClose.setFocusPainted(false);
        btnClose.setForeground(new Color(107, 114, 128));
        btnClose.setBackground(Color.WHITE);
        btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.add(btnClose);

        btnClose.addActionListener(e -> dispose());

        // ==== REGISTER ACTION ====
        btnRegister.addActionListener(e -> {

            String name = txtName.getText().trim();
            String email = txtEmail.getText().trim();
            String password = new String(txtPass.getPassword()).trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            User user = new User(name, email, password, "USER");

            UserDAO dao = new UserDAO();
            boolean saved = dao.registerUser(user);

            if (saved) {
                JOptionPane.showMessageDialog(this,
                        "Account created successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new LoginFrame().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Error creating account!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
