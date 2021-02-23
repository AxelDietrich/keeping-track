CREATE TABLE IF NOT EXISTS keepingtrack.accounts (
    id INT,
    name VARCHAR,
    created_at timestamp not null default current_timestamp,
    PRIMARY KEY (id)
);