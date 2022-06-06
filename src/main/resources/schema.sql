create table if not exists hardware (
                                        id identity,
                                        name varchar(30) not null,
                                        code varchar(10) not null unique,
                                        price  decimal(10,2)  not null,
                                        type varchar(10) not null,
                                        amount int not null
);

create table if not exists review (
                                      id identity,
                                      title varchar(100) not null,
                                      text varchar(255) not null,
                                      score int not null
);

create table if not exists hardware_review (
                                               id identity,
                                               hardware_id bigint,
                                               review_id bigint,
                                               constraint fk_hardware foreign key (hardware_id) references hardware(id),
                                               constraint fk_review foreign key (review_id) references review(id)
);

create table if not exists user (
                                    id identity,
                                    username varchar(100) not null unique,
                                    password varchar(1000) not null
);

create table if not exists authority (
                                         id identity,
                                         authority_name varchar(100) not null unique
);

create table if not exists user_authority (
                                              user_id bigint not null,
                                              authority_id bigint not null,
                                              constraint fk_user foreign key (user_id) references user(id),
                                              constraint fk_authority foreign key (authority_id) references authority(id)
);