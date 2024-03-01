INSERT INTO clients (id, name, email, created_at, updated_at)
VALUES
    ('d19c23f9-d33f-11ee-9fc6-0242ac120003', 'John Doe', 'john@doe.com', now(), now()),
    ('d19cd235-d33f-11ee-9fc6-0242ac120003', 'Jane Doe', 'jane@doe.com', now(), now());

INSERT INTO accounts (id, client_id, balance, created_at, updated_at)
VALUES
    ('90f5c758-8330-4a27-aae8-c28631a9a359', 'd19c23f9-d33f-11ee-9fc6-0242ac120003', 1000, now(), now()),
    ('faa198e3-99a0-4114-8bc4-82c0ded58ec9', 'd19cd235-d33f-11ee-9fc6-0242ac120003', 500, now(), now());