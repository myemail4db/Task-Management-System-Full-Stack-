# Instructions to load the MySQL Database with the flat files

## 1\) Prep

### 1. Download each CSV to a folder on your machine (note the full path).

Example paths:
     Windows: C:\data\users.csv

### 2. Start MySQL and open a terminal client:

```bash
mysql -u root -p --local-infile=1
```

If you see an error about local infile later, we’ll enable it in step 4.

---

## 2\) Create DB + Tables (copy/paste)

```sql
CREATE DATABASE IF NOT EXISTS taskdb CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE taskdb;

CREATE TABLE users (
  user_id INT PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  email VARCHAR(120) UNIQUE,
  role ENUM('DEVELOPER','QA','DEVOPS','PRODUCT','MANAGER','ANALYST','SUPPORT','DESIGNER'),
  team VARCHAR(60),
  active TINYINT(1) NOT NULL DEFAULT 1,
  created_at DATETIME NOT NULL
);

CREATE TABLE projects (
  project_id INT PRIMARY KEY,
  code VARCHAR(20) UNIQUE,
  name VARCHAR(120),
  type ENUM('FEATURE','PLATFORM','RESEARCH','MAINTENANCE','SECURITY'),
  status ENUM('PLANNING','ACTIVE','ON_HOLD','DONE','CANCELLED'),
  owner_user_id INT,
  created_at DATETIME NOT NULL,
  target_due DATETIME NULL,
  CONSTRAINT fk_projects_owner FOREIGN KEY (owner_user_id) REFERENCES users(user_id)
);

CREATE TABLE tasks (
  task_id INT PRIMARY KEY,
  project_id INT NOT NULL,
  title VARCHAR(255),
  description TEXT,
  priority ENUM('LOW','MEDIUM','HIGH','CRITICAL'),
  status ENUM('OPEN','IN_PROGRESS','BLOCKED','REVIEW','DONE','CANCELLED'),
  estimate_points INT,
  creator_user_id INT,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  due_date DATETIME NULL,
  CONSTRAINT fk_tasks_project FOREIGN KEY (project_id) REFERENCES projects(project_id),
  CONSTRAINT fk_tasks_creator FOREIGN KEY (creator_user_id) REFERENCES users(user_id)
);

CREATE TABLE task_assignments (
  task_id INT NOT NULL,
  user_id INT NOT NULL,
  assigned_at DATETIME NOT NULL,
  PRIMARY KEY (task_id, user_id),
  CONSTRAINT fk_ta_task FOREIGN KEY (task_id) REFERENCES tasks(task_id),
  CONSTRAINT fk_ta_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE comments (
  comment_id INT PRIMARY KEY,
  task_id INT NOT NULL,
  user_id INT NOT NULL,
  body TEXT,
  created_at DATETIME NOT NULL,
  CONSTRAINT fk_comments_task FOREIGN KEY (task_id) REFERENCES tasks(task_id),
  CONSTRAINT fk_comments_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE labels (
  label_id INT PRIMARY KEY,
  name VARCHAR(120) UNIQUE,
  category ENUM('domain','priority','component','release','area')
);

CREATE TABLE task_labels (
  task_id INT NOT NULL,
  label_id INT NOT NULL,
  PRIMARY KEY (task_id, label_id),
  CONSTRAINT fk_tl_task FOREIGN KEY (task_id) REFERENCES tasks(task_id),
  CONSTRAINT fk_tl_label FOREIGN KEY (label_id) REFERENCES labels(label_id)
);

-- Helpful indexes
CREATE INDEX idx_tasks_project_status ON tasks(project_id, status);
CREATE INDEX idx_tasks_due ON tasks(due_date);
CREATE INDEX idx_tasks_created ON tasks(created_at);
CREATE INDEX idx_comments_task ON comments(task_id, created_at);
CREATE INDEX idx_assign_user ON task_assignments(user_id, task_id);

```

---

## 3\) Import CSVs (copy/paste; change file paths)

Replace the file paths with wherever you saved the downloads.

```sql
-- Enable local infile in this session (safe)
SET SESSION local_infile = 1;

-- USERS
LOAD DATA LOCAL INFILE '/path/to/users.csv'
INTO TABLE users
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(user_id,first_name,last_name,email,role,team,active,created_at);

-- PROJECTS
LOAD DATA LOCAL INFILE '/path/to/projects.csv'
INTO TABLE projects
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(project_id,code,name,type,status,owner_user_id,created_at,target_due);

-- TASKS
LOAD DATA LOCAL INFILE '/path/to/tasks.csv'
INTO TABLE tasks
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(task_id,project_id,title,description,priority,status,estimate_points,creator_user_id,created_at,updated_at,due_date);

-- TASK_ASSIGNMENTS
LOAD DATA LOCAL INFILE '/path/to/task_assignments.csv'
INTO TABLE task_assignments
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(task_id,user_id,assigned_at);

-- COMMENTS
LOAD DATA LOCAL INFILE '/path/to/comments.csv'
INTO TABLE comments
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(comment_id,task_id,user_id,body,created_at);

-- LABELS
LOAD DATA LOCAL INFILE '/path/to/labels.csv'
INTO TABLE labels
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(label_id,name,category);

-- TASK_LABELS
LOAD DATA LOCAL INFILE '/path/to/task_labels.csv'
INTO TABLE task_labels
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(task_id,label_id);

```

Sanity checks

```sql
SELECT COUNT(*) FROM users;          -- ~300 (or 600 if using the first dataset)
SELECT COUNT(*) FROM projects;       -- ~100 (or 180)
SELECT COUNT(*) FROM tasks;          -- ~5,000 (or 12,000)
SELECT COUNT(*) FROM comments;       -- ~15,000 (or 35,000)
```

---

## 4\) If LOCAL INFILE is disabled
