package timemanager.model;

public class TimeLog {

    private int id;
    private int userId;
    private String date;      // YYYY-MM-DD
    private int hours;
    private String activity;
    private String notes;

    public TimeLog() {}

    // Constructor for INSERT (AddTimeLogFrame)
    public TimeLog(int userId, String date, int hours, String activity, String notes) {
        this.userId = userId;
        this.date = date;
        this.hours = hours;
        this.activity = activity;
        this.notes = notes;
    }

    // Constructor for UPDATE (EditTimeLogFrame)
    public TimeLog(int id, int userId, String date, int hours, String activity, String notes) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
        this.activity = activity;
        this.notes = notes;
    }

    // ======= GETTERS & SETTERS =======

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public int getHours() { return hours; }

    public void setHours(int hours) { this.hours = hours; }

    public String getActivity() { return activity; }

    public void setActivity(String activity) { this.activity = activity; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }
}
