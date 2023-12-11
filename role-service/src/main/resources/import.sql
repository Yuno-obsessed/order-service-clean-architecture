INSERT INTO role_service.services(service_name, base_url, port, created_at, active) VALUES ('order-service', null, '8080', now(), true);
INSERT INTO role_service.services(service_name, base_url, port, created_at, active) VALUES ('auth-service', null, '8003', now(), true);
INSERT INTO role_service.services(service_name, base_url, port, created_at, active) VALUES ('user-service', null, '8002', now(), true);
INSERT INTO role_service.services(service_name, base_url, port, created_at, active) VALUES ('mail-service', null, '8081', now(), true);
INSERT INTO role_service.services(service_name, base_url, port, created_at, active) VALUES ('role-service', null, '8004', now(), true);

INSERT INTO role_service.permissions(id, created_at, description, method, roles, service_name, uri, verb) VALUES (0, now(), 'desc', 'createProduct', 'ROLE_ADMIN', 'order-service', 'api/v1/product', 'POST')
