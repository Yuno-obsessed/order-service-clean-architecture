CREATE TABLE "user"
(
        user_id UUID PRIMARY KEY,
        first_name VARCHAR(50) NOT NULL,
        user_identifier VARCHAR(50) NOT NULL UNIQUE,
        rank INTEGER,
        email VARCHAR(100) NOT NULL,
        email_confirmed BOOLEAN NOT NULL,
        "password" VARCHAR(100) NOT NULL,
        created_at TIMESTAMP NOT NULL,
        updated_at TIMESTAMP NOT NULL
);

CREATE TABLE role
(
        role_id SMALLINT PRIMARY KEY,
        role_type VARCHAR(30)
);

CREATE TABLE user_role
(
        role_id SMALLINT NOT NULL,
        user_id UUID NOT NULL,
        CONSTRAINT pk_user_role PRIMARY KEY (role_id, user_id)
);

CREATE TABLE country
(
        country_id SERIAL PRIMARY KEY,
        description TEXT,
        "name" VARCHAR(100) NOT NULL
);

CREATE TABLE city
(
        city_id SERIAL PRIMARY KEY,
        description TEXT,
        "name" VARCHAR(100),
        country_id INTEGER REFERENCES country (country_id) NOT NULL
);

CREATE TABLE tour (
        tour_id UUID PRIMARY KEY,
        short_description VARCHAR(255) NOT NULL,
        price DOUBLE PRECISION NOT NULL,
--         city_id INTEGER REFERENCES city (city_id) NOT NULL
        city_id INTEGER REFERENCES city (city_id)
);

CREATE TABLE review (
        review_id UUID PRIMARY KEY,
        likes INTEGER,
        dislikes INTEGER,
        user_id UUID REFERENCES "user" (user_id),
        tour_id UUID REFERENCES tour (tour_id),
        city_id INTEGER REFERENCES city (city_id)
);

CREATE TABLE image (
        image_id SERIAL PRIMARY KEY,
        city_id INTEGER REFERENCES city (city_id)
);

CREATE TABLE sight (
        sight_id SERIAL PRIMARY KEY,
        city_id INTEGER REFERENCES city (city_id)
);