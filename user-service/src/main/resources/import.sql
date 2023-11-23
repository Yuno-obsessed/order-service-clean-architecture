INSERT INTO roles(id, role_type, created_at)
VALUES (1, 'ROLE_ADMIN', now());
INSERT INTO users(id, first_name, last_name, username, rank, email, email_confirmed, password, created_at, updated_at,
                  deleted_at, deleted)
VALUES ('2de076d2-b617-48e6-91e5-7d5b84fe0764', 'Ivan', 'Grozniy', 'Ivan.Grozniy', 1, 'example@gmail.com', false,
        '$2a$12$aE/BZ5ecw6dCJUIcPN83Kuiwoav8jWxBy353Vi8a.PiJ5AyLc9e9u', now(), now(), now(), false);
INSERT INTO user_role(user_id, role_id)
VALUES ('2de076d2-b617-48e6-91e5-7d5b84fe0764', 1);