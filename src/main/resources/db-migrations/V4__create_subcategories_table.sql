CREATE TABLE IF NOT EXISTS subcategories (
    id INT,
    name VARCHAR not null,
    amount MONEY not null,
    category_id INT not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp,
    PRIMARY KEY (id),
    CONSTRAINT fk_category
        FOREIGN KEY (category_id)
            REFERENCES categories(id)
);