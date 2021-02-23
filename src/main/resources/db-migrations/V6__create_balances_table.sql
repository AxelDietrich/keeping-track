CREATE TABLE IF NOT EXISTS balances (
    id INT,
    available_amount money,
    savings_amount money,
    debt money,
    account_id int,
    PRIMARY KEY (id),
    CONSTRAINT fk_account
        foreign key (account_id)
            references accounts(id)
);