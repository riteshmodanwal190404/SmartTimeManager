package timemanager.ui;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {

    JProgressBar progress;

    public SplashScreen() {

        setUndecorated(true);
        setSize(600, 350);
        setLocationRelativeTo(null);

        // ===== MAIN BACKGROUND PANEL =====
        JPanel bg = new JPanel();
        bg.setBackground(new Color(15, 23, 42)); // Dark navy
        bg.setLayout(new GridBagLayout());
        add(bg);

        // ===== CENTER WHITE CARD =====
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(450, 240));
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(229, 231, 235), 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        bg.add(card);  // Add card to center

        // ===== APP TITLE =====
        JLabel title = new JLabel("SMART TIME MANAGER", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(31, 41, 55));
        title.setBounds(30, 30, 390, 35);
        card.add(title);

        // ===== SUBTEXT =====
        JLabel loadingTxt = new JLabel("Initializing modules...", SwingConstants.CENTER);
        loadingTxt.setFont(new Font("SansSerif", Font.PLAIN, 14));
        loadingTxt.setForeground(new Color(100, 116, 139));
        loadingTxt.setBounds(30, 80, 390, 25);
        card.add(loadingTxt);

        // ===== PROGRESS BAR =====
        progress = new JProgressBar();
        progress.setBounds(50, 140, 350, 25);
        progress.setForeground(new Color(37, 99, 235));  // Blue
        progress.setBackground(new Color(229, 231, 235)); // Light gray
        progress.setBorder(BorderFactory.createLineBorder(new Color(209, 213, 219)));
        progress.setStringPainted(true);
        card.add(progress);

        setVisible(true);

        // ===== SMOOTH LOADING THREAD =====
        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    progress.setValue(i);

                    if (i < 40) loadingTxt.setText("Loading UI Components...");
                    else if (i < 75) loadingTxt.setText("Connecting to Database...");
                    else loadingTxt.setText("Starting Application...");

                    Thread.sleep(35); // Control speed of loading
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            dispose();
            new LoginFrame().setVisible(true);

        }).start();
    }
}
