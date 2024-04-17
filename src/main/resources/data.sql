INSERT INTO concert (title,capacity,reserved_count,price,concert_date) VALUES('PsyConcert',50,50,50000,'20240503');
INSERT INTO concert (title,capacity,reserved_count,price,concert_date) VALUES('PsyConcert',50,10,50000,'20240504');
INSERT INTO concert (title,capacity,reserved_count,price,concert_date) VALUES('OasisConcert',50,50,50000,'20240603');
INSERT INTO concert (title,capacity,reserved_count,price,concert_date) VALUES('OasisConcert',50,45,50000,'20240612');
INSERT INTO concert (title,capacity,reserved_count,price,concert_date) VALUES('MuseConcert',50,23,70000,'20241112');


INSERT INTO reservation (reservation_id,reservation_date,title,seat_num,user_id,final_confirm) VALUES('2024111254','20241112','MuseConcert',4,1,'Y');
INSERT INTO reservation (reservation_id,reservation_date,title,seat_num,user_id,final_confirm) VALUES('2024111251','20241112','MuseConcert',1,2,'Y');
INSERT INTO reservation (reservation_id,reservation_date,title,seat_num,user_id,final_confirm) VALUES('2024111257','20241112','MuseConcert',7,3,'Y');

INSERT INTO users (point) VALUES(100000);
INSERT INTO users (point) VALUES(70000);
INSERT INTO users (point) VALUES(80000);
