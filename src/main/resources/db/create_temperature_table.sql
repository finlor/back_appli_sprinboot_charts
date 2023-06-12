CREATE TABLE temperature(
                            temperature_id SERIAL PRIMARY KEY NOT NULL,
                            city_id integer REFERENCES city(city_id) ON DELETE CASCADE,
                            date    timestamp NOT NULL,
                            temperature NUMERIC(28,16) NOT NULL
);
