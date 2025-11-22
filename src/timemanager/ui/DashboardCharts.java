package timemanager.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;

public class DashboardCharts {

    // ===============================
    //    PIE CHART – GOALS STATUS
    // ===============================
    public static JPanel createGoalsStatusPieChart(int pending, int inProgress, int completed) {

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Pending", pending);
        dataset.setValue("In Progress", inProgress);
        dataset.setValue("Completed", completed);

        JFreeChart chart = ChartFactory.createPieChart(
                "Goal Status Overview",
                dataset,
                true,
                true,
                false
        );

        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new java.awt.Dimension(350, 250));
        return panel;
    }

    // ===============================
    //  BAR CHART – WEEKLY WORK HOURS
    // ===============================
    public static JPanel createTimeLogBarChart(int mon, int tue, int wed, int thu, int fri, int sat, int sun) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(mon, "Hours", "Mon");
        dataset.addValue(tue, "Hours", "Tue");
        dataset.addValue(wed, "Hours", "Wed");
        dataset.addValue(thu, "Hours", "Thu");
        dataset.addValue(fri, "Hours", "Fri");
        dataset.addValue(sat, "Hours", "Sat");
        dataset.addValue(sun, "Hours", "Sun");

        JFreeChart barChart = ChartFactory.createBarChart(
                "Weekly Time Log Summary",
                "Day",
                "Hours Spent",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        ChartPanel panel = new ChartPanel(barChart);
        panel.setPreferredSize(new java.awt.Dimension(450, 250));

        return panel;
    }

    // ===============================
    //  LINE CHART (OPTIONAL FUTURE)
    // ===============================
    public static JPanel createLineChartDemo() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(2, "Productivity", "Mon");
        dataset.addValue(4, "Productivity", "Tue");
        dataset.addValue(3, "Productivity", "Wed");
        dataset.addValue(6, "Productivity", "Thu");
        dataset.addValue(5, "Productivity", "Fri");

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Productivity Trend",
                "Day",
                "Score",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel panel = new ChartPanel(lineChart);
        panel.setPreferredSize(new java.awt.Dimension(450, 250));
        return panel;
    }
}
