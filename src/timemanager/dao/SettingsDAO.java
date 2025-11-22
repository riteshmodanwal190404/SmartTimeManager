package timemanager.dao;

import timemanager.model.Settings;
import java.sql.*;

public class SettingsDAO {

    public Settings loadSettings() {
        Settings s = new Settings();
        String sql = "SELECT * FROM settings WHERE id=1";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                s.setAppTitle(rs.getString("app_title"));
                s.setTheme(rs.getString("theme"));
                s.setAdminPassword(rs.getString("admin_password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public boolean saveSettings(Settings s) {
        String sql = "UPDATE settings SET app_title=?, theme=?, admin_password=? WHERE id=1";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getAppTitle());
            ps.setString(2, s.getTheme());
            ps.setString(3, s.getAdminPassword());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
