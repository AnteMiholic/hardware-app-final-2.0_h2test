insert into hardware (id, name, code, price, type, amount)
values (1, 'Nvidia GeForce GTX 1650 OC','123123123', 1399.99, 'GPU', 10);

insert into hardware (id, name, code, price, type, amount)
values (2, 'i5 9500','123456789', 999.99, 'CPU', 20);

insert into hardware (id, name, code, price, type, amount)
values (3, 'Nvidia GeForce RTX 3090','568455625', 9999.99, 'GPU', 0);

insert into hardware (id, name, code, price, type, amount)
values (4, 'Genious MK-500 Mechanical','9876542', 19.99, 'GPU', 100);

insert into review (title, text, score)
values ('Budget card', 'Not bad for the money', 4);
insert into review (title, text, score)
values ('Bang for buck', 'Much better than AMD', 5);
insert into review (title, text, score)
values ('Want more frames', 'Cant even run Crysis', 1);
insert into review (title, text, score)
values ('Not bad but not good', 'Cant even run Crysis', 1);

insert into user(id, username, password)
values
    (1, 'user', '$2a$12$h0HcS2QDb/7zPASbLa2GoOTSRP6CWK0oX7pCK.dPjkM6L5N4pNovi'),
    (2, 'admin', '$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG'),
    (3, 'updater', '$2a$12$NPMVGef95v6Me2sLZ8KFG.HIGAAUqNNrLKy/gRdPW8vMXVSdOMspW');
insert into authority (id, authority_name)
values
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_USER'),
    (3, 'ROLE_UPDATER');

insert into user_authority (user_id, authority_id)
values
    (1, 2),
    (2, 1),
    (3,3);


insert into HARDWARE_REVIEW (hardware_id, review_id)
values (1, 1);
insert into HARDWARE_REVIEW (hardware_id, review_id)
values (2, 2);
insert into HARDWARE_REVIEW (hardware_id, review_id)
values (1, 3);
