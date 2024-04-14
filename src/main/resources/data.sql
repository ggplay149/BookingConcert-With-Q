INSERT INTO Concert (concert_title,concert_capacity,concert_reserved_count,concert_price,concert_date) VALUES('PsyConcert',50,2,50000,'20240503');
INSERT INTO Concert (concert_title,concert_capacity,concert_reserved_count,concert_price,concert_date) VALUES('PsyConcert',50,38,50000,'20240504');
INSERT INTO Concert (concert_title,concert_capacity,concert_reserved_count,concert_price,concert_date) VALUES('OasisConcert',50,22,50000,'20240603');
INSERT INTO Concert (concert_title,concert_capacity,concert_reserved_count,concert_price,concert_date) VALUES('OasisConcert',50,50,50000,'20240612');
INSERT INTO Concert (concert_title,concert_capacity,concert_reserved_count,concert_price,concert_date) VALUES('MuseConcert',50,2,70000,'20241112');


INSERT INTO Reservation (reservation_id,reservation_date,reservation_title,reservation_seat_num,reservation_userid,reservation_final_confirm) VALUES('2024111254','20241112','MuseConcert',4,1,'Y');
INSERT INTO Reservation (reservation_id,reservation_date,reservation_title,reservation_seat_num,reservation_userid,reservation_final_confirm) VALUES('2024111251','20241112','MuseConcert',1,2,'Y');
INSERT INTO Reservation (reservation_id,reservation_date,reservation_title,reservation_seat_num,reservation_userid,reservation_final_confirm) VALUES('2024111257','20241112','MuseConcert',7,3,'Y');

INSERT INTO Userinfo (user_point) VALUES(100000);
INSERT INTO Userinfo (user_point) VALUES(70000);
INSERT INTO Userinfo (user_point) VALUES(80000);



