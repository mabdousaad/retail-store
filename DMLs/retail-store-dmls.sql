INSERT INTO `retailstore`.`adm_users`(`user_id`,`email`,`join_date`,`user_type`,`username`) VALUES(5,"mabdousaad@gmail.com","2018-12-04 16:40:30",CUSTOMER,"mabdousaad");

INSERT INTO `retailstore`.`tx_bill`(`bill_id`,`issue_date`,`payment_date`,`user_id`) VALUES(1,CURRENT_TIME(),null,5);

INSERT INTO `retailstore`.`tx_bill_item`(`bill_item_id`,`bill_item_type`,`bill_id`,`item_price`) VALUES (1,'GROCERY',1,20);

INSERT INTO `retailstore`.`tx_bill_item`(`bill_item_id`,`bill_item_type`,`bill_id`,`item_price`) VALUES (2,'ELECTRONICS',1,200);

INSERT INTO `retailstore`.`tx_bill_item`(`bill_item_id`,`bill_item_type`,`bill_id`,`item_price`) VALUES (3,'HEALTH',1,150);

INSERT INTO `retailstore`.`tx_bill_item`(`bill_item_id`,`bill_item_type`,`bill_id`,`item_price`) VALUES (4,'TOYS',1,100);

INSERT INTO `retailstore`.`tx_bill_item`(`bill_item_id`,`bill_item_type`,`bill_id`,`item_price`) VALUES (5,'GROCERY',1,300);