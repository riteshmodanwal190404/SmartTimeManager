# Smart Time Manager – Java Desktop Application

Smart Time Manager is a Java Swing–based desktop productivity application that helps users manage their **Goals**, **Tasks**, and **Time Logs**, along with an **Admin Dashboard**, **Settings**, and **Dark Theme UI**.

This project is built as a fully functional desktop software using:
- Java Swing
- MySQL Database
- JDBC (MySQL Connector)
- MVC Architecture (DAO + Model + UI)

---

## 🚀 Features

### 👤 User Features
- User registration and login
- Add / Edit / Delete goals
- Add / Edit / Delete tasks
- Add / Edit / Delete time logs
- View personal dashboard
- Time-spent analysis using logs
- Change basic settings (like app title)

### 👨‍💼 Admin Features
- Login as Admin
- View all users
- Delete user accounts
- View all goals
- View all tasks
- View all time logs
- Modify app-level settings

### 🎨 UI Features
- Dark theme dashboard
- Sidebar-based navigation
- Simple and clean forms
- Splash screen on startup

---

## 🛠️ Technology Stack

| Component  | Technology        |
|-----------|-------------------|
| Language  | Java (Core Java)  |
| GUI       | Java Swing        |
| Database  | MySQL             |
| Driver    | MySQL Connector/J |
| Pattern   | DAO + MVC         |
| IDE       | IntelliJ IDEA     |

---

## 🗄️ Database Design (MySQL)

### `users` table
- id (INT, PK, AUTO_INCREMENT)  
- name (VARCHAR)  
- email (VARCHAR, UNIQUE)  
- password (VARCHAR)  
- role (VARCHAR) – e.g. "ADMIN" / "USER"  

### `goals` table
- id (INT, PK, AUTO_INCREMENT)  
- user_id (INT, FK → users.id)  
- title (VARCHAR)  
- description (TEXT / VARCHAR)  
- target_date (VARCHAR / DATE)  
- status (VARCHAR)  

### `tasks` table
- id (INT, PK, AUTO_INCREMENT)  
- user_id (INT, FK → users.id)  
- title (VARCHAR)  
- description (TEXT / VARCHAR)  
- due_date (VARCHAR / DATE)  
- priority (VARCHAR)  
- status (VARCHAR)  

### `timelogs` table
- id (INT, PK, AUTO_INCREMENT)  
- user_id (INT, FK → users.id)  
- log_date (VARCHAR / DATE)  
- hours (INT)  
- activity (VARCHAR)  
- notes (VARCHAR / TEXT)  

### `settings` table
- id (INT, PK, AUTO_INCREMENT)  
- app_title (VARCHAR)  
- theme (VARCHAR)  

---

## 🧩 Project Structure (Suggested)

```text
SmartTimeManager/
│
├── src/
│   └── timemanager/
│       ├── Main.java
│       ├── ui/          # All Swing Frames
│       │   ├── SplashScreen.java
│       │   ├── LoginFrame.java
│       │   ├── RegisterFrame.java
│       │   ├── AdminDashboard.java
│       │   ├── UserDashboard.java
│       │   ├── AddGoalFrame.java
│       │   ├── ViewGoalsFrame.java
│       │   ├── EditGoalFrame.java
│       │   ├── AddTaskFrame.java
│       │   ├── ViewTasksFrame.java
│       │   ├── EditTaskFrame.java
│       │   ├── AddTimeLogFrame.java
│       │   ├── ViewTimeLogsFrame.java
│       │   ├── EditTimeLogFrame.java
│       │   └── SettingsFrame.java
│       │
│       ├── dao/         # Database access classes
│       │   ├── DBUtil.java
│       │   ├── UserDAO.java
│       │   ├── GoalDAO.java
│       │   ├── TaskDAO.java
│       │   ├── TimeLogDAO.java
│       │   └── SettingsDAO.java
│       │
│       ├── model/       # POJO classes
│       │   ├── User.java
│       │   ├── Goal.java
│       │   ├── Task.java
│       │   ├── TimeLog.java
│       │   └── Settings.java
│       │
│       └── util/
│           └── ThemeColors.java
│
└── README.md
 for Admin Dashboard = admin@tm.com/admin123
