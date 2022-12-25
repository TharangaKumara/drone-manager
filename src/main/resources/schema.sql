DROP TABLE IF EXISTS `DRONE_MEDICATION`;
DROP TABLE IF EXISTS `DRONE_BATTERY_HISTORY`;
DROP TABLE IF EXISTS `MEDICATION`;
DROP TABLE IF EXISTS `DRONE`;

create table DRONE
(
    drone_id           int auto_increment,
    serial_number      varchar(100) not null,
    model              varchar(30) not null,
    weight_limit       float not null,
    battery_percentage float not null,
    state              varchar(30) not null,
    primary key (drone_id)
);

create table MEDICATION
(
    medicine_id int auto_increment,
    name        varchar(200) not null,
    weight      float not null,
    code        varchar(100) not null,
    image       varchar(1000) not null,
    primary key (medicine_id)
);

create table DRONE_MEDICATION
(
    id                 int auto_increment,
    drone_id           int not null,
    medicine_id        int not null,
    loaded_date_time   datetime not null,
    unloaded_date_time datetime,
    foreign key (drone_id) REFERENCES DRONE (drone_id),
    foreign key (medicine_id) REFERENCES MEDICATION (medicine_id),
    primary key (id)
);

create table DRONE_BATTERY_HISTORY
(
    log_id             integer auto_increment,
    drone_id           int not null,
    battery_percentage float not null,
    logged_date_time   datetime,
    primary key (log_id)
);