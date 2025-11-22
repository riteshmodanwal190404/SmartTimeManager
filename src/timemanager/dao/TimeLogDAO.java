package timemanager.dao;

import timemanager.model.TimeLog;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimeLogDAO {

    // ADD TIME LOG
    public boolean addTimeLog(TimeLog log) {
        String sql = "INSERT INTO timelogs (user_id, log_date, hours, activity, notes) VALUES (?,?,?,?,?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, log.getUserId());
            ps.setString(2, log.getDate());
            ps.setInt(3, log.getHours());
            ps.setString(4, log.getActivity());
            ps.setString(5, log.getNotes());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // GET TIME LOGS BY USER
    public List<TimeLog> getLogsByUser(int userId) {
        List<TimeLog> list = new ArrayList<>();
        String sql = "SELECT * FROM timelogs WHERE user_id=? ORDER BY log_date ASC";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TimeLog log = new TimeLog(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("log_date"),
                        rs.getInt("hours"),
                        rs.getString("activity"),
                        rs.getString("notes")
                );
                list.add(log);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // UPDATE TIME LOG
    public boolean updateTimeLog(TimeLog log) {
        String sql = "UPDATE timelogs SET log_date=?, hours=?, activity=?, notes=? WHERE id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, log.getDate());
            ps.setInt(2, log.getHours());
            ps.setString(3, log.getActivity());
            ps.setString(4, log.getNotes());
            ps.setInt(5, log.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE TIME LOG
    public boolean deleteTimeLog(int id) {
        String sql = "DELETE FROM timelogs WHERE id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
