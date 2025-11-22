package timemanager.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import timemanager.dao.GoalDAO;
import timemanager.model.Goal;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserChartPanel extends JPanel {

    public UserChartPanel(int userId) {
        setLayout(new BorderLayout());

        GoalDAO dao = new GoalDAO();
        List<Goal> goals = dao.getGoalsByUser(userId);

        int completed = 0;
        int pending = 0;

        for (Goal g : goals) {
            if ("COMPLETED".equalsIgnoreCase(g.getStatus())) {
                completed++;
            } else {
                pending++;
            }
        }

        // Dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Completed Goals", completed);
        dataset.setValue("Pending Goals", pending);

        // Chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Goals Summary",
                dataset,
                true,
                true,
                false
        );

        // Panel
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }
}
