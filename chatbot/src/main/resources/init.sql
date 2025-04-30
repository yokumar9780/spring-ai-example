-- init.sql

-- Insert Customers
INSERT INTO customer (first_name, last_name) VALUES ('John', 'Doe');
INSERT INTO customer (first_name, last_name) VALUES ('Jane', 'Smith');
INSERT INTO customer (first_name, last_name) VALUES ('Michael', 'Johnson');
INSERT INTO customer (first_name, last_name) VALUES ('Sarah', 'Williams');
INSERT INTO customer (first_name, last_name) VALUES ('Robert', 'Taylor');

-- Insert Bookings
INSERT INTO booking (booking_number, date, customer_id, from_airport, to_airport, booking_status, booking_class)
VALUES
    (101, '2025-06-01', 1, 'LAX', 'SFO', 'CONFIRMED', 'ECONOMY'),
    ('102', '2025-06-03', 2, 'JFK', 'LHR', 'CONFIRMED', 'BUSINESS'),
    ('103', '2025-06-05', 3, 'CDG', 'MUC', 'CONFIRMED', 'FIRST_CLASS'),
    ('104', '2025-06-07', 4, 'ARN', 'FRA', 'CONFIRMED', 'ECONOMY'),
    ('105', '2025-06-09', 5, 'HEL', 'TXL', 'CONFIRMED', 'BUSINESS');
