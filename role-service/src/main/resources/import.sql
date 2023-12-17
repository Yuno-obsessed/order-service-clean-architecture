INSERT INTO role_service.services(service_name, base_url, port, created_at, active) VALUES ('order-service', null, '8080', now(), true);
INSERT INTO role_service.services(service_name, base_url, port, created_at, active) VALUES ('auth-service', null, '8003', now(), true);
INSERT INTO role_service.services(service_name, base_url, port, created_at, active) VALUES ('user-service', null, '8002', now(), true);
INSERT INTO role_service.services(service_name, base_url, port, created_at, active) VALUES ('mail-service', null, '8081', now(), true);
INSERT INTO role_service.services(service_name, base_url, port, created_at, active) VALUES ('role-service', null, '8004', now(), true);

INSERT INTO role_service.permissions(id, created_at, description, method, roles, service_name, uri, verb) VALUES (0, now(), 'desc', 'createProduct', 'ROLE_ADMIN', 'order-service', 'api/v1/product', 'POST');
INSERT INTO role_service.permissions(id, created_at, description, method, roles, service_name, uri, verb) VALUES (1, now(), 'desc', 'updateProduct', 'ROLE_ADMIN', 'order-service', 'api/v1/product', 'PUT');
INSERT INTO role_service.permissions(id, created_at, description, method, roles, service_name, uri, verb) VALUES (2, now(), 'desc', 'deleteProduct', 'ROLE_ADMIN', 'order-service', 'api/v1/product', 'DELETE');
INSERT INTO role_service.permissions(id, created_at, description, method, roles, service_name, uri, verb) VALUES (3, now(), 'desc', 'getProductByID', 'ROLE_ADMIN,NONE', 'order-service', 'api/v1/product', 'GET');
INSERT INTO role_service.permissions(id, created_at, description, method, roles, service_name, uri, verb) VALUES (4, now(), 'desc', 'getAllProducts', 'ROLE_ADMIN,NONE', 'order-service', 'api/v1/product/search', 'GET');
INSERT INTO role_service.permissions(id, created_at, description, method, roles, service_name, uri, verb) VALUES (5, now(), 'desc', 'addRating', 'ROLE_ADMIN', 'order-service', 'api/v1/product/statistics/addrate', 'PUT');
INSERT INTO role_service.permissions(id, created_at, description, method, roles, service_name, uri, verb) VALUES (6, now(), 'desc', 'addToWishList', 'ROLE_ADMIN', 'order-service', 'api/v1/product/statistics/addtowishlist', 'PUT');
INSERT INTO role_service.permissions(id, created_at, description, method, roles, service_name, uri, verb) VALUES (7, now(), 'desc', 'uploadProductPhotos', 'ROLE_ADMIN', 'order-service', 'api/v1/product/upload', 'POST');
INSERT INTO role_service.permissions(id, created_at, description, method, roles, service_name, uri, verb) VALUES (8, now(), 'desc', 'getAllImages', 'ROLE_ADMIN,NONE', 'order-service', 'api/v1/product/images', 'GET');