package timemanager.ui;

import javax.swing.*;
import java.awt.*;

public class TimeSpentChartWindow extends JFrame {

    public TimeSpentChartWindow(int userId) {

        setTitle("Time Usage Chart");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new TimeSpentChartPanel(userId), BorderLayout.CENTER);

        setVisible(true);
    }
}
