package timemanager.ui;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard(String adminName) {

        setTitle("Admin Dashboard - Smart Time Manager");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Frame layout – NO null layout now
        setLayout(new BorderLayout());

        // ==== COLORS ====
        Color sidebarBg = new Color(15, 23, 42);        // dark navy
        Color btnColor = new Color(30, 41, 59);
        Color btnHover = new Color(51, 65, 85);
        Color mainBg = new Color(243, 244, 246);        // gray-100

        // ==== SIDEBAR PANEL ====
        JPanel sidebar = new JPanel();
        sidebar.setBackground(sidebarBg);
        sidebar.setPreferredSize(new Dimension(230, 650));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        JLabel title = new JLabel("ADMIN PANEL");
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("Smart Time Manager");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitle.setForeground(new Color(156, 163, 175));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(title);
        sidebar.add(subtitle);
        sidebar.add(Box.createVerticalStrut(20));

        // Sidebar Buttons with simple icons (emoji-style)
        JButton btnUsers    = createSidebarButton("👤 User Management", btnColor, btnHover);
        JButton btnGoals    = createSidebarButton("🎯 Goals", btnColor, btnHover);
        JButton btnTasks    = createSidebarButton("📋 Tasks", btnColor, btnHover);
        JButton btnLogs     = createSidebarButton("⏱ Time Logs", btnColor, btnHover);
        JButton btnSettings = createSidebarButton("⚙ Settings", btnColor, btnHover);
        JButton btnLogout   = createSidebarButton("🚪 Logout",
                new Color(185, 28, 28),
                new Color(220, 38, 38));

        sidebar.add(btnUsers);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnGoals);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnTasks);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnLogs);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnSettings);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnLogout);

        add(sidebar, BorderLayout.WEST);

        // ==== TOP BAR ====
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.WHITE);
        topBar.setPreferredSize(new Dimension(900, 55));
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(229, 231, 235)));

        JLabel welcome = new JLabel("Welcome, Administrator - " + adminName);
        welcome.setFont(new Font("SansSerif", Font.BOLD, 18));
        welcome.setForeground(new Color(31, 41, 55));
        welcome.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        topBar.add(welcome, BorderLayout.WEST);
        add(topBar, BorderLayout.NORTH);

        // ==== MAIN CONTENT ====
        JPanel contentWrapper = new JPanel();              // wrapper panel center mein
        contentWrapper.setBackground(mainBg);
        contentWrapper.setLayout(new BorderLayout());
        add(contentWrapper, BorderLayout.CENTER);

        // Inner content with FlowLayout so responsive rahe
        JPanel content = new JPanel();
        content.setBackground(mainBg);
        content.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        // Heading
        JPanel headingPanel = new JPanel(new BorderLayout());
        headingPanel.setBackground(mainBg);
        JLabel heading = new JLabel("Admin Dashboard Overview");
        heading.setFont(new Font("SansSerif", Font.BOLD, 22));
        heading.setForeground(new Color(31, 41, 55));
        heading.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        headingPanel.add(heading, BorderLayout.WEST);

        contentWrapper.add(headingPanel, BorderLayout.NORTH);
        contentWrapper.add(content, BorderLayout.CENTER);

        // === CARDS ===
        JPanel usersCard    = createCard("Users", "Manage all users and roles.");
        JPanel goalsCard    = createCard("Goals", "Approve or review user goals.");
        JPanel tasksCard    = createCard("Tasks", "Monitor user tasks and progress.");
        JPanel logsCard     = createCard("Time Logs", "Check user log entries.");
        JPanel settingsCard = createCard("Settings", "Edit system configuration.");

        content.add(usersCard);
        content.add(goalsCard);
        content.add(tasksCard);
        content.add(logsCard);
        content.add(settingsCard);

        // ==== BUTTON ACTIONS (SIDEBAR) ====
        btnUsers.addActionListener(e -> new ViewUsersFrame());
        btnGoals.addActionListener(e -> new ViewGoalsFrame(1));
        btnTasks.addActionListener(e -> new ViewTasksFrame(1));
        btnLogs.addActionListener(e -> new ViewTimeLogsFrame(1));
        btnSettings.addActionListener(e -> new SettingsFrame());
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        // ==== CARD CLICK ACTIONS (same as sidebar) ====
        makeCardClickable(usersCard,    () -> new ViewUsersFrame());
        makeCardClickable(goalsCard,    () -> new ViewGoalsFrame(1));
        makeCardClickable(tasksCard,    () -> new ViewTasksFrame(1));
        makeCardClickable(logsCard,     () -> new ViewTimeLogsFrame(1));
        makeCardClickable(settingsCard, () -> new SettingsFrame());

        setVisible(true);
    }

    // ---- Sidebar Button Creator ----
    private JButton createSidebarButton(String text, Color base, Color hover) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(base);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 15));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 10));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { btn.setBackground(hover); }
            public void mouseExited (java.awt.event.MouseEvent evt) { btn.setBackground(base); }
        });

        return btn;
    }

    // ---- Dashboard Card ----
    private JPanel createCard(String title, String desc) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(250, 120));
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(229, 231, 235)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitle.setForeground(new Color(31, 41, 55));
        lblTitle.setBounds(10, 10, 200, 20);

        JTextArea labelDesc = new JTextArea(desc);
        labelDesc.setLineWrap(true);
        labelDesc.setWrapStyleWord(true);
        labelDesc.setEditable(false);
        labelDesc.setFocusable(false);
        labelDesc.setForeground(new Color(107, 114, 128));
        labelDesc.setBackground(Color.WHITE);
        labelDesc.setFont(new Font("SansSerif", Font.PLAIN, 13));
        labelDesc.setBounds(10, 40, 220, 60);

        card.add(lblTitle);
        card.add(labelDesc);

        return card;
    }

    // ---- Make Card Clickable (hover + click) ----
    private void makeCardClickable(JPanel card, Runnable action) {
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                action.run();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(248, 250, 252));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(Color.WHITE);
            }
        });
    }
}
