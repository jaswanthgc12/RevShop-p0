USE `hospitalmanagement`;
-- Create Patient table
CREATE TABLE Appointment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patientId BIGINT,
    doctorId BIGINT,
    date DATE,
    time TIME,
    duration VARCHAR(255),
    status VARCHAR(255),
    FOREIGN KEY (patientId) REFERENCES Patient(id),
    FOREIGN KEY (doctorId) REFERENCES Staff(id)
);

CREATE TABLE Patient (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    age BIGINT,
    gender VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(255),
    email VARCHAR(255),
    medicalHistory TEXT,
    treatmentPlan TEXT
);

-- Create Staff table
CREATE TABLE Staff (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    age BIGINT,
    gender VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(255),
    email VARCHAR(255),
    jobTitle VARCHAR(255),
    salary BIGINT,
    benefits TEXT
);

-- Create MedicalRecord table
CREATE TABLE MedicalRecord (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    patient_id BIGINT,
    doctor_id BIGINT,
    date DATE,
    diagnosis VARCHAR(255),
    prescription TEXT,
    notes TEXT,
    FOREIGN KEY (patient_id) REFERENCES Patient(id),
    FOREIGN KEY (doctor_id) REFERENCES Staff(id)
);

-- Create Billing table
CREATE TABLE Billing (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    amount BIGINT,
    treatment_description TEXT,
    patient_id BIGINT,
    FOREIGN KEY (patient_id) REFERENCES Patient(id)
);

-- Create Inventory table
CREATE TABLE Inventory (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    quantity BIGINT,
    category VARCHAR(255),
    price BIGINT,
    supplier VARCHAR(255)
);

-- Create Pharmacy table
CREATE TABLE Pharmacy (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    patient_id BIGINT,
    medication_name VARCHAR(255),
    dosage VARCHAR(255),
    refill_date DATE,
    prescription_number BIGINT,
    FOREIGN KEY (patient_id) REFERENCES Patient(id)
);
