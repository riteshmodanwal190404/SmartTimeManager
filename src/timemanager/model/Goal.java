package timemanager.model;

public class Goal {
    private int id;
    private int userId;
    private String title;
    private String description;
    private String targetDate;
    private String status;

    public Goal() {
    }

    // Used when creating a new goal (without id, DB auto-generates id)
    public Goal(int userId, String title, String description, String targetDate, String status) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.status = status;
    }

    // Used when updating an existing goal (id already known)
    public Goal(int id, int userId, String title, String description, String targetDate, String status) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.status = status;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
