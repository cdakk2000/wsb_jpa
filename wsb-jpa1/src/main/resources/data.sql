-- Adresy
INSERT INTO address (id, address_line1, address_line2, city, postal_code)
VALUES (1, 'xx', 'yy', 'city', '62-030');

INSERT INTO address (id, address_line1, address_line2, city, postal_code)
VALUES (2, 'aa', 'bb', 'another_city', '00-100');

-- Pacjenci
INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, is_adult)
VALUES (1, 'Basia', 'Nowak', '987654321', 'basia.nowak@wp.com', 'P001', '1998-01-01', 1, True);

INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, is_adult)
VALUES (2, 'Kasia', 'Wola', '987654321', 'kasia.wola@onet.com', 'P124', '1994-05-10', 2, True);

INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, is_adult)
VALUES (3, 'Piotr', 'Kaka', '888555666', 'piotr.kaka@wp.com', 'P003', '2010-12-31', 1, False);


-- Lekarze
INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES (1, 'Jan', 'Kowalski', '123456789', 'jan.kowalski@wp.com', 'D001', 'SURGEON', 1);

INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES (2, 'Robert', 'Gabka', '321654987', 'RobertGabka@onet.com', 'D568', 'SURGEON', 2);

-- Wizyty
INSERT INTO visit (id, description, time, patient_id, doctor_id)
VALUES (1, 'wizyta NFZ', '2028-11-24 10:00:00', 1, 1);

INSERT INTO visit (id, description, time, patient_id, doctor_id)
VALUES (3, 'wizyta kontrolna', '2023-12-15 09:00:00', 1, 1);

INSERT INTO visit (id, description, time, patient_id, doctor_id)
VALUES (4, 'wizyta prywatna', '2024-01-10 11:00:00', 1, 1);

INSERT INTO visit (id, description, time, patient_id, doctor_id)
VALUES (2, 'wizyta prywatna', '2023-11-25 14:30:00', 2, 2);

-- Leczenie
INSERT INTO medical_treatment (id, description, type, visit_id)
VALUES (1, 'test1', 'EKG', 1);

INSERT INTO medical_treatment (id, description, type, visit_id)
VALUES (2, 'test2', 'EKG', 2);

INSERT INTO medical_treatment (id, description, type, visit_id)
VALUES (3, 'usg serca', 'USG', 3);

INSERT INTO medical_treatment (id, description, type, visit_id)
VALUES (4, 'usg nogi', 'RTG', 4);

