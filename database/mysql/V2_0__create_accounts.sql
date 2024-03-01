CREATE TABLE accounts (
    id VARCHAR(255) primary key,
    client_id VARCHAR(255),
    balance DECIMAL(10,2) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    foreign key (client_id) references clients(id)
);