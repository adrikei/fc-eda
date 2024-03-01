CREATE TABLE transactions (
    id VARCHAR(255) primary key,
    account_id_from VARCHAR(255), 
    account_id_to VARCHAR(255), 
    amount DECIMAL(10,2) NOT NULL,
    created_at DATETIME NOT NULL,
    foreign key (account_id_from) references accounts(id),
    foreign key (account_id_to) references accounts(id)
);