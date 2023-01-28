

INSERT INTO jwtapp_db.files (id, name, status)
VALUES
    (6, '1.2.xlsx', 'ACTIVE'),
    (7, '3.1.xlsx', 'NOT_ACTIVE'),
    (8, '1.1.xlsx', 'ACTIVE');



INSERT INTO jwtapp_db.users (id, username, email, firstname, lastname, password, created, updated, status, role)
VALUES
    (1, 'Tim', 'askd@gmail.com', 'Timur', 'Bro', '$2b$10$FyRqaApxAzt1b1MG4jbUyedRLMaZTiphXUsXzTbqxd2z74dLOX6xa', '2023-01-28 13:34:17', '2023-01-28 13:34:20', 'ACTIVE', 'ADMIN'),
    (2, 'Ann', 'test@gmail.com', 'Anna', 'Phillips', '$2b$10$h5Uuf04ozvhHWDNdj9WGLu4xgNKShic6Qz.WSWQvrEfpi0BbakNAa', '2023-01-28 14:32:21', '2023-01-28 14:37:23', 'ACTIVE', 'MODERATOR'),
    (3, 'Boris', 'boris@gmail.com', 'Borya', 'Borievych', '$2b$10$Gz4Yja1SbYNCeHyI5TnM/Oc5gWKp1KmljQVYpWzBSSNZwGOwlTWq.', '2023-01-28 14:26:25', '2023-01-28 14:42:28', 'ACTIVE', 'USER'),
    (4, 'Olga1111111', 'olga@gmail.com44444444444', 'Olya222222222', 'Olgovna3333333333', '$2b$10$bX6Utks9/.cW2Iglf/nFeedR8dzkRnW1MCcoQaAMyghBP/G99vSQa', '2023-01-28 11:34:30', '2023-01-28 17:34:32', 'NOT_ACTIVE', 'USER');


INSERT INTO jwtapp_db.events (id, user_id, file_id, created, updated, status)
VALUES
    (2, 1, 6, '2023-01-28 14:32:18', '2023-01-28 14:40:20', 'ACTIVE'),
    (3, 3, 7, '2023-01-28 14:32:22', '2023-01-28 14:40:24', 'ACTIVE'),
    (4, 3, 8, '2023-01-28 14:32:26', '2023-01-28 14:40:27', 'ACTIVE');

