CREATE TABLE IF NOT EXISTS categories (
    id INT,
    name VARCHAR not null,
    account_id INT not null,
    PRIMARY KEY (id),
    CONSTRAINT fk_account
        FOREIGN KEY(account_id)
            REFERENCES accounts(id)
);