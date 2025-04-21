-- Create table
CREATE TABLE hotels (
                       id BIGINT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       latitude DOUBLE NOT NULL,
                       longitude DOUBLE NOT NULL
);

-- Insert sample data
INSERT INTO hotels (id, name, latitude, longitude) VALUES
                                                  (1, 'The Grand Palace', 28.6139, 77.2090),
                                                  (2, 'Coastal Breeze Resort', 15.2993, 74.1240),
                                                  (3, 'Mountain View Inn', 34.0837, 74.7973),
                                                  (4, 'City Lights Hotel', 19.0760, 72.8777),
                                                  (5, 'Desert Mirage Lodge', 26.9124, 75.7873);
