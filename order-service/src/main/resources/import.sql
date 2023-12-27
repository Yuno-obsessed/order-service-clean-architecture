INSERT INTO order_service.product_types (type_name, prefix) VALUES ('Electronics', 'ELC');
INSERT INTO order_service.product_subtypes (subtype_id, subtype_name, subtype_prefix, type_name) VALUES (1, 'Computers', 'CMP', 'Electronics');
INSERT INTO order_service.discounts(id, percent, started_at, expired_at, active) VALUES ('8de076c9-b617-48e6-91e5-7d5b84fe0764', 30, now(), now() + INTERVAL '5 days', true);
INSERT INTO order_service.addresses(id, user_id, city, country, street_name, building_number, postal_code, created_at, updated_at, deleted_at, deleted) VALUES ('d4f41c91-fea4-488b-9c46-39624080b555', '8ff09696-0401-41db-a108-8fb04004ad5a', 'Kiev', 'Ukraine', 'Shevchenka', 5, '087142', now(), now(), null, false);

