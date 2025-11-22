package timemanager.dao;

import timemanager.model.Goal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoalDAO {

    // ADD NEW GOAL
    public boolean addGoal(Goal g) {
        String sql = "INSERT INTO goals (user_id, title, description, target_date, status) VALUES (?,?,?,?,?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, g.getUserId());
            ps.setString(2, g.getTitle());
            ps.setString(3, g.getDescription());
            ps.setString(4, g.getTargetDate());
            ps.setString(5, g.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // GET GOALS BY USER
    public List<Goal> getGoalsByUser(int userId) {

        List<Goal> list = new ArrayList<>();

        String sql = "SELECT * FROM goals WHERE user_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Goal g = new Goal();
                g.setId(rs.getInt("id"));
                g.setUserId(rs.getInt("user_id"));
                g.setTitle(rs.getString("title"));
                g.setDescription(rs.getString("description"));
                g.setTargetDate(rs.getString("target_date"));
                g.setStatus(rs.getString("status"));

                list.add(g);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // DELETE GOAL
    public boolean deleteGoal(int id) {
        String sql = "DELETE FROM goals WHERE id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE GOAL
    public boolean updateGoal(Goal g) {
        String sql = "UPDATE goals SET title=?, description=?, target_date=?, status=? WHERE id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, g.getTitle());
            ps.setString(2, g.getDescription());
            ps.setString(3, g.getTargetDate());
            ps.setString(4, g.getStatus());
            ps.setInt(5, g.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ⭐⭐⭐ GET SINGLE GOAL BY ID — Added Now ⭐⭐⭐
    public Goal getGoalById(int id) {
        String sql = "SELECT * FROM goals WHERE id = ? LIMIT 1";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Goal g = new Goal();
                g.setId(rs.getInt("id"));
                g.setUserId(rs.getInt("user_id"));
                g.setTitle(rs.getString("title"));
                g.setDescription(rs.getString("description"));
                g.setTargetDate(rs.getString("target_date"));
                g.setStatus(rs.getString("status"));
                return g;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
