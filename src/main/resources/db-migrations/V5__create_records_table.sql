CREATE TABLE IF NOT EXISTS records (
    id INT,
    name VARCHAR not null,
    amount MONEY not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp,
    subcategory_id INT,
    PRIMARY KEY (id),
    constraint fk_subcategory
        foreign key (subcategory_id)
            references subcategories(id)
);