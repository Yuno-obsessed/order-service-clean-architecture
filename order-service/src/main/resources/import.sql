INSERT INTO order_service.product_types (type_name, prefix) VALUES ('Electronics', 'ELC');
INSERT INTO order_service.product_subtypes (subtype_id, subtype_name, subtype_prefix, type_name) VALUES (1, 'Computers', 'CMP', 'Electronics');
INSERT INTO order_service.discounts(id, percent, started_at, expired_at, active) VALUES ('8de076c9-b617-48e6-91e5-7d5b84fe0764', 30, now(), now() + INTERVAL '5 days', true);
INSERT INTO order_service.users(id, email_confirmed, rank, created_at, deleted_at, updated_at, deleted, first_name, email, password) VALUES ('cde4914f-a885-4940-b92f-c48358c95e20', true, 2, now(), null, now(), false, 'daniel', 'd.251@gmail.com', 'wordpass');
INSERT INTO order_service.addresses(id, city, country, street_name, building_number, postal_code, created_at, updated_at, deleted_at, deleted) VALUES ('d4f41c91-fea4-488b-9c46-39624080b555', 'Kiev', 'Ukraine', 'Shevchenka', 5, '087142', now(), now(), null, false);

