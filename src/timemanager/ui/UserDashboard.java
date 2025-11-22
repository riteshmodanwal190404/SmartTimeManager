package timemanager.ui;

import javax.swing.*;
import java.awt.*;

public class UserDashboard extends JFrame {

    private final int userId;

    public UserDashboard(String userName, int userId) {
        this.userId = userId;

        setTitle("User Dashboard - Smart Time Manager");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // COLORS
        Color sidebarBg = new Color(15, 23, 42);
        Color btnColor = new Color(30, 64, 175);
        Color btnHover = new Color(37, 99, 235);
        Color mainBg = new Color(243, 244, 246);

        // SIDEBAR
        JPanel sidebar = new JPanel();
        sidebar.setBackground(sidebarBg);
        sidebar.setPreferredSize(new Dimension(230, 650));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        JLabel title = new JLabel("USER PANEL");
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

        JButton btnMyGoals   = createSidebarButton("🎯 My Goals", btnColor, btnHover);
        JButton btnMyTasks   = createSidebarButton("📋 My Tasks", btnColor, btnHover);
        JButton btnMyLogs    = createSidebarButton("⏱ My Time Logs", btnColor, btnHover);
        JButton btnSettings  = createSidebarButton("⚙ Settings", btnColor, btnHover);
        JButton btnLogout    = createSidebarButton("🚪 Logout", new Color(185, 28, 28), new Color(220, 38, 38));

        sidebar.add(btnMyGoals);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnMyTasks);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnMyLogs);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnSettings);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnLogout);

        add(sidebar, BorderLayout.WEST);

        // TOP BAR
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.WHITE);
        topBar.setPreferredSize(new Dimension(900, 55));
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(229, 231, 235)));

        JLabel welcome = new JLabel("Welcome, " + userName);
        welcome.setFont(new Font("SansSerif", Font.BOLD, 18));
        welcome.setForeground(new Color(31, 41, 55));
        welcome.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        topBar.add(welcome, BorderLayout.WEST);

        add(topBar, BorderLayout.NORTH);

        // MAIN CONTENT
        JPanel contentWrapper = new JPanel(new BorderLayout());
        contentWrapper.setBackground(mainBg);
        add(contentWrapper, BorderLayout.CENTER);

        JPanel content = new JPanel();
        content.setBackground(mainBg);
        content.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        JLabel heading = new JLabel("Your Productivity Dashboard");
        heading.setFont(new Font("SansSerif", Font.BOLD, 22));
        heading.setForeground(new Color(31, 41, 55));
        heading.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JPanel headingPanel = new JPanel(new BorderLayout());
        headingPanel.setBackground(mainBg);
        headingPanel.add(heading, BorderLayout.WEST);

        contentWrapper.add(headingPanel, BorderLayout.NORTH);
        contentWrapper.add(content, BorderLayout.CENTER);

        // CARDS
        JPanel goalsCard     = createCard("My Goals", "Create, update and track your goals.");
        JPanel tasksCard     = createCard("My Tasks", "Plan your daily tasks with priorities.");
        JPanel logsCard      = createCard("My Time Logs", "Log how much time you spend each day.");
        JPanel tipsCard      = createCard("Tips & Balance", "Keep a healthy balance of study and rest.");
        JPanel timeChartCard = createCard("Time Spent Chart", "Visualize how you spend your time.");

        content.add(goalsCard);
        content.add(tasksCard);
        content.add(logsCard);
        content.add(tipsCard);
        content.add(timeChartCard);

        // SIDEBAR BUTTON ACTIONS
        btnMyGoals.addActionListener(e -> new ViewGoalsFrame(userId));
        btnMyTasks.addActionListener(e -> new ViewTasksFrame(userId));
        btnMyLogs.addActionListener(e -> new ViewTimeLogsFrame(userId));
        btnSettings.addActionListener(e -> new SettingsFrame());
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        // CARD CLICK ACTIONS
        makeCardClickable(goalsCard,     () -> new ViewGoalsFrame(userId));
        makeCardClickable(tasksCard,     () -> new ViewTasksFrame(userId));
        makeCardClickable(logsCard,      () -> new ViewTimeLogsFrame(userId));
        // Agar tumne UserChartWindow aur TimeSpentChartWindow banaya hai to:
        // makeCardClickable(tipsCard,      () -> new UserChartWindow(userId));
        // makeCardClickable(timeChartCard, () -> new TimeSpentChartWindow(userId));
        // Abhi filhaal simple message:
        makeCardClickable(tipsCard, () ->
                JOptionPane.showMessageDialog(this,
                        "Tips screen feature can be added here.",
                        "Info", JOptionPane.INFORMATION_MESSAGE));

        makeCardClickable(timeChartCard, () ->
                JOptionPane.showMessageDialog(this,
                        "Time Spent Chart screen can open here.",
                        "Info", JOptionPane.INFORMATION_MESSAGE));

        setVisible(true);
    }

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
        lblTitle.setBounds(10, 10, 220, 20);

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
