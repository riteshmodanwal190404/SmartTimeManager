package timemanager.util;

import javax.swing.*;
import java.awt.*;

public class ThemeManager {

    public static void applyTheme(String theme) {
        try {
            if (theme.equalsIgnoreCase("DARK")) {

                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

                // DARK COLOR TWEAKS
                UIManager.put("control", new Color(40,40,40));
                UIManager.put("info", new Color(55,55,55));
                UIManager.put("nimbusBase", new Color(30,30,30));
                UIManager.put("nimbusBlueGrey", new Color(80,80,80));
                UIManager.put("text", Color.WHITE);

            } else {
                // LIGHT MODE (System Default)
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
