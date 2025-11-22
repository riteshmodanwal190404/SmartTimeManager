package timemanager.ui;

import javax.swing.*;
import java.awt.*;

public class UserChartWindow extends JFrame {

    public UserChartWindow(int userId) {
        setTitle("Goal Progress Chart");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new UserChartPanel(userId), BorderLayout.CENTER);

        setVisible(true);
    }
}
