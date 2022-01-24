
INSERT INTO category (id, status, name, icon) VALUES ('1', 'ACTIVE', 'ALISADOS','face');
INSERT INTO category (id, status, name, icon) VALUES ('2', 'ACTIVE', 'PELUQUERIA','airline_seat_flat');
INSERT INTO category (id, status, name, icon) VALUES ('3', 'ACTIVE', 'SPA','content_cut');
INSERT INTO category (id, status, name, icon) VALUES ('4', 'ACTIVE', 'PRODUCTOS','face');


INSERT INTO category (id, status, name, parent_category_id) VALUES ('5', 'ACTIVE', 'ALISADOS','1');


INSERT INTO category (id, status, name, parent_category_id) VALUES ('6', 'ACTIVE', 'COLOR','2');
INSERT INTO category (id, status, name, parent_category_id) VALUES ('7', 'ACTIVE', 'CABELLO','2');
INSERT INTO category (id, status, name, parent_category_id) VALUES ('8', 'ACTIVE', 'GENERAL','2');

INSERT INTO category (id, status, name, parent_category_id) VALUES ('9', 'ACTIVE', 'UÑAS','3');
INSERT INTO category (id, status, name, parent_category_id) VALUES ('10', 'ACTIVE', 'CEJAS','3');
INSERT INTO category (id, status, name, parent_category_id) VALUES ('11', 'ACTIVE', 'DEPILACIONES','3');
INSERT INTO category (id, status, name, parent_category_id) VALUES ('12', 'ACTIVE', 'PESTAÑAS','3');

INSERT INTO category (id, status, name, parent_category_id) VALUES ('13', 'ACTIVE', 'ALISADO','4');
INSERT INTO category (id, status, name, parent_category_id) VALUES ('14', 'ACTIVE', 'OLAPLEX','4');
INSERT INTO category (id, status, name, parent_category_id) VALUES ('15', 'ACTIVE', 'OTROS','4');