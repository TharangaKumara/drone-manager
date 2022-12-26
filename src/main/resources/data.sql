insert into DRONE(model, serial_number, weight_limit, battery_percentage, state)
values ('Lightweight', 'LWD00001', 50.00, 100.0, 'LOADING');
insert into drone(model, serial_number, weight_limit, battery_percentage, state)
values ('Lightweight', 'LWD00002', 100.00, 20.5, 'IDLE');
insert into drone(model, serial_number, weight_limit, battery_percentage, state)
values ('Middleweight', 'MWD00001', 110.00, 50.0, 'IDLE');
insert into drone(model, serial_number, weight_limit, battery_percentage, state)
values ('Middleweight ', 'MWD00002', 150.00, 60.5, 'IDLE');
insert into drone(model, serial_number, weight_limit, battery_percentage, state)
values ('Middleweight', 'MWD00003', 185.00, 40.0, 'LOADED');
insert into drone(model, serial_number, weight_limit, battery_percentage, state)
values ('Cruiserweight', 'CWD00001', 199.99, 90.5, 'IDLE');
insert into drone(model, serial_number, weight_limit, battery_percentage, state)
values ('Cruiserweight', 'CWD00002', 350, 40.0, 'DELIVERING');
insert into drone(model, serial_number, weight_limit, battery_percentage, state)
values ('Cruiserweight', 'CWD00003', 400.50, 20.0, 'LOADED');
insert into drone(model, serial_number, weight_limit, battery_percentage, state)
values ('Heavyweight', 'HWD00001', 450.00, 60.0, 'RETURNING');
insert into drone(model, serial_number, weight_limit, battery_percentage, state)
values ('Heavyweight', 'HWD00002', 500, 80.0, 'LOADING');


insert into MEDICATION(name, weight, code, image)
values ('Medicine_001', 10.0, 'MED_10_001', 'https://www.pexels.com/photo/a-close-up-shot-of-medicine-tablets-7722641/');
insert into medication(name, weight, code, image)
values ('Medicine_002', 20.0, 'MED_10_002', 'https://www.pexels.com/photo/a-close-up-shot-of-medicine-tablets-7722642/');
insert into medication(name, weight, code, image)
values ('Medicine_003', 30.0, 'MED_10_003', 'https://www.pexels.com/photo/a-close-up-shot-of-medicine-tablets-7722643/');
insert into medication(name, weight, code, image)
values ('Medicine_003', 60.0, 'MED_10_003', 'https://www.pexels.com/photo/a-close-up-shot-of-medicine-tablets-7722643/');
insert into medication(name, weight, code, image)
values ('Medicine_004', 150.0, 'MED_10_004', 'https://www.pexels.com/photo/a-close-up-shot-of-medicine-tablets-7722644/');
insert into medication(name, weight, code, image)
values ('Medicine_005', 6.5, 'MED_10_005', 'https://www.pexels.com/photo/a-close-up-shot-of-medicine-tablets-7722646/');
insert into medication(name, weight, code, image)
values ('Medicine_006', 15.0, 'MED_10_006', 'https://www.pexels.com/photo/a-close-up-shot-of-medicine-tablets-7722647/');
insert into medication(name, weight, code, image)
values ('Medicine_007', 25.05, 'MED_10_007', 'https://www.pexels.com/photo/a-close-up-shot-of-medicine-tablets-7722648/');
insert into medication(name, weight, code, image)
values ('Medicine_008', 75.0, 'MED_10_008', 'https://www.pexels.com/photo/a-close-up-shot-of-medicine-tablets-7722649/');
insert into medication(name, weight, code, image)
values ('Medicine_009', 1.0, 'MED_10_009', 'https://www.pexels.com/photo/a-close-up-shot-of-medicine-tablets-7722650/');
insert into medication(name, weight, code, image)
values ('Medicine_010', 505.0, 'MED_10_010', 'https://www.pexels.com/photo/a-close-up-shot-of-medicine-tablets-7722651/');
insert into medication(name, weight, code, image)
values ('Medicine_011', 5.0, 'MED_10_011', 'https://www.pexels.com/photo/a-close-up-shot-of-medicine-tablets-7722652/');
insert into medication(name, weight, code, image)
values ('Medicine_012', 2.0, 'MED_10_012', 'https://www.pexels.com/photo/a-close-up-shot-of-medicine-tablets-7722653/');
insert into medication(name, weight, code, image)
values ('Medicine_013', 8.0, 'MED_10_013', 'https://www.pexels.com/photo/a-close-up-shot-of-medicine-tablets-7722654/');


insert into DRONE_MEDICATION (drone_id, medicine_id, loaded_date_time)
values (1, 1, '2022-12-22 13:25:47');
insert into drone_medication (drone_id, medicine_id, loaded_date_time)
values (1, 2, '2022-12-22 13:25:47');
insert into drone_medication (drone_id, medicine_id, loaded_date_time)
values (1, 13, '2022-12-22 13:25:47');
insert into drone_medication (drone_id, medicine_id, loaded_date_time)
values (5, 2, '2022-12-22 13:25:47');
insert into drone_medication (drone_id, medicine_id, loaded_date_time)
values (5, 4, '2022-12-22 13:25:47');
insert into drone_medication (drone_id, medicine_id, loaded_date_time)
values (5, 1, '2022-12-22 13:25:47');
insert into drone_medication (drone_id, medicine_id, loaded_date_time)
values (5, 10, '2022-12-22 13:25:47');
insert into drone_medication (drone_id, medicine_id, loaded_date_time)
values (5, 14, '2022-12-22 13:25:47');
insert into drone_medication (drone_id, medicine_id, loaded_date_time)
values (7, 7, '2022-12-22 13:25:47');
insert into drone_medication (drone_id, medicine_id, loaded_date_time)
values (7, 8, '2022-12-22 13:25:47');
insert into drone_medication (drone_id, medicine_id, loaded_date_time)
values (7, 9, '2022-12-22 13:25:47');
insert into drone_medication (drone_id, medicine_id, loaded_date_time)
values (8, 4, '2022-12-22 13:25:47');
insert into drone_medication (drone_id, medicine_id, loaded_date_time)
values (8, 9, '2022-12-22 13:25:47');
insert into drone_medication (drone_id, medicine_id, loaded_date_time)
values (8, 5, '2022-12-22 13:25:47');
insert into drone_medication (drone_id, medicine_id, loaded_date_time)
values (10, 5, '2022-12-22 13:25:47');
insert into drone_medication (drone_id, medicine_id, loaded_date_time)
values (10, 5, '2022-12-22 13:25:47');