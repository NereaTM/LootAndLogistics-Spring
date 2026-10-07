CREATE TABLE delivery_quests (
    id UUID PRIMARY KEY ,

    title VARCHAR(255) NOT NULL ,

    origin_name VARCHAR(255) ,
    origin_street VARCHAR(255) NOT NULL ,
    origin_region VARCHAR(255) NOT NULL ,

    destination_name VARCHAR(255) ,
    destination_street VARCHAR(255) NOT NULL ,
    destination_region VARCHAR(255) NOT NULL ,

    cargo_name VARCHAR(255) NOT NULL ,
    cargo_quantity INTEGER NOT NULL ,

    reward_amount INTEGER NOT NULL ,
    reward_currency VARCHAR(255) NOT NULL ,

    danger_level VARCHAR(20) NOT NULL ,
    status VARCHAR(20) NOT NULL
);