INSERT INTO roles(role_type, created_at)
VALUES ('ROLE_ADMIN', now());
INSERT INTO users(id, first_name, last_name, username, email, email_confirmed, password, created_at, updated_at,
                  deleted_at, deleted)
VALUES ('2de076d2-b617-48e6-91e5-7d5b84fe0764', 'Ivan', 'Grozniy', 'Ivan.Grozniy', 'example@gmail.com', false,
        'XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=', now(), now(), now(), false);
INSERT INTO user_role(user_id, role_type)
VALUES ('2de076d2-b617-48e6-91e5-7d5b84fe0764', 'ROLE_ADMIN');