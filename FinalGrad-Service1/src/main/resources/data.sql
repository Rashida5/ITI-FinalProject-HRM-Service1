CREATE TABLE Job (
                     job_id INT AUTO_INCREMENT PRIMARY KEY,
                     job_title VARCHAR(50) NOT NULL,
                     min_salary DECIMAL(10, 2),
                     max_salary DECIMAL(10, 2)
);
CREATE TABLE Department (
                            department_id INT AUTO_INCREMENT PRIMARY KEY,
                            department_name VARCHAR(50) NOT NULL
);
CREATE TABLE Address (
                         address_id INT AUTO_INCREMENT PRIMARY KEY,
                         street VARCHAR(100) NOT NULL,
                         city VARCHAR(50) NOT NULL,
                         state VARCHAR(50) NOT NULL,
                         zip_code VARCHAR(10) NOT NULL,
                         country VARCHAR(50) NOT NULL
);
CREATE TABLE Employee (
                          employee_id INT AUTO_INCREMENT PRIMARY KEY,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE,
                          phone_number VARCHAR(15),
                          job_id INT,
                          department_id INT,
                          manager_id INT,
                          employment_status VARCHAR(20),  -- e.g., 'Active', 'On Leave', 'Terminated'
                          date_of_birth DATE,
                          start_date DATE,
                          end_date DATE,
                          address_id INT,
                          FOREIGN KEY (address_id) REFERENCES Address(address_id),
                          FOREIGN KEY (manager_id) REFERENCES Employee(employee_id),
                          FOREIGN KEY (job_id) REFERENCES Job(job_id),
                          FOREIGN KEY (department_id) REFERENCES Department(department_id)
);

ALTER TABLE Department ADD COLUMN existed BOOLEAN NOT NULL DEFAULT True;

ALTER TABLE Job ADD COLUMN existed BOOLEAN NOT NULL DEFAULT True;

SELECT * FROM gradprojectservice1.department;