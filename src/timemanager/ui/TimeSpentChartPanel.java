package timemanager.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import timemanager.dao.TimeLogDAO;
import timemanager.model.TimeLog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TimeSpentChartPanel extends JPanel {

    public TimeSpentChartPanel(int userId) {

        setLayout(new BorderLayout());

        TimeLogDAO dao = new TimeLogDAO();
        List<TimeLog> logs = dao.getLogsByUser(userId);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (TimeLog log : logs) {
            dataset.addValue(log.getHours(), "Hours", log.getDate());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Daily Time Spent",
                "Date",
                "Hours",
                dataset
        );

        ChartPanel panel = new ChartPanel(barChart);
        add(panel, BorderLayout.CENTER);
    }
}
