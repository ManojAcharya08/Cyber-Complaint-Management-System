-- Drop and create the database
DROP DATABASE IF EXISTS cybercrime_db;
CREATE DATABASE cybercrime_db;
USE cybercrime_db;

-- 1. Create admin table
CREATE TABLE admin (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL -- Store hashed passwords in production!
);

-- 2. Insert initial admin (use hashed password in production!)
INSERT INTO admin (username, password) VALUES ('admin', 'admin123');

-- 3. View admins
SELECT * FROM admin;

-- 4. Create complaint table
CREATE TABLE complaint (
    case_id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    date_reported DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    location VARCHAR(100) NOT NULL,
    area VARCHAR(100) NOT NULL,
    evidence VARCHAR(255), -- File path or URL to evidence
    status ENUM('New', 'Under Investigation', 'Solved', 'Closed') DEFAULT 'New'
);

-- 5. Insert sample complaints
INSERT INTO complaint (type, description, location, area, evidence, status)
VALUES
('Phishing', 'Received suspicious email asking for bank details.', 'Mumbai', 'Andheri', NULL, 'New'),
('Hacking', 'Unauthorized access to company server.', 'Delhi', 'Connaught Place', 'evidence1.pdf', 'Under Investigation');

-- 6. View all complaints
SELECT * FROM complaint;

-- 7. Update status values to uppercase (if needed for enum mapping)
SET SQL_SAFE_UPDATES = 0;
UPDATE complaint SET status = 'New' WHERE status IS NULL OR status = '';
UPDATE complaint SET status = 'New' WHERE status = 'NEW';
UPDATE complaint SET status = 'Under Investigation' WHERE status = 'UNDER_INVESTIGATION';
UPDATE complaint SET status = 'Solved' WHERE status = 'SOLVED';
UPDATE complaint SET status = 'Closed' WHERE status = 'CLOSED';

-- 8. Show all unique status values
SELECT DISTINCT status FROM complaint;

-- 9. Show complaints with status not matching allowed values (should be empty if above updates worked)
SELECT * FROM complaint WHERE status NOT IN ('New', 'Under Investigation', 'Solved', 'Closed');

-- 10. Show the most recent complaint
SELECT * FROM complaint ORDER BY case_id DESC LIMIT 1;

-- 11. Show complaint table structure
SHOW COLUMNS FROM complaint;
DESCRIBE complaint;

-- 12. (Optional) Change column name to match Java entity (if needed)
ALTER TABLE complaint CHANGE case_id caseId BIGINT AUTO_INCREMENT;

-- 13. (Optional) Drop an old column if it exists
-- ALTER TABLE complaint DROP COLUMN old_id_column;

-- 14. View all complaints again
SELECT * FROM complaint;
