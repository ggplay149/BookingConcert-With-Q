INSERT INTO concert (title,capacity,reserved_count,price,concert_date) VALUES('PsyConcert',50,50,50000,'20240503');
INSERT INTO concert (title,capacity,reserved_count,price,concert_date) VALUES('PsyConcert',50,0,50000,'20240504');
INSERT INTO concert (title,capacity,reserved_count,price,concert_date) VALUES('OasisConcert',50,50,50000,'20240603');
INSERT INTO concert (title,capacity,reserved_count,price,concert_date) VALUES('OasisConcert',50,45,50000,'20240612');
INSERT INTO concert (title,capacity,reserved_count,price,concert_date) VALUES('MuseConcert',50,23,70000,'20241112');

INSERT INTO reservation (reservation_number,reservation_date,title,seat_num,user_id,final_confirm) VALUES('20241112.5.4','20241112','MuseConcert',4,1,'Y');
INSERT INTO reservation (reservation_number,reservation_date,title,seat_num,user_id,final_confirm) VALUES('20241112.5.1','20241112','MuseConcert',1,2,'Y');
INSERT INTO reservation (reservation_number,reservation_date,title,seat_num,user_id,final_confirm) VALUES('20241112.5.7','20241112','MuseConcert',7,3,'Y');
INSERT INTO reservation (reservation_number,reservation_date,title,seat_num,user_id,final_confirm) VALUES('20241112.5.9','20241112','MuseConcert',9,1,'N');

INSERT INTO users (point) VALUES(100000);
INSERT INTO users (point) VALUES(70000);
INSERT INTO users (point) VALUES(80000);
INSERT INTO users (point) VALUES(2);
