mrb
===

mrb



db change
===
ALTER TABLE `tbmodelimg` ADD `linkto` VARCHAR(512) NOT NULL ;
ALTER TABLE `tbproject` ADD `phone` VARCHAR(32) NOT NULL ;


create view vvreview as (select ur.vid as vid,count(1) AS cnt from tbureview ur, tbuser user where ur.uid = user.uid group by ur.vid )



create view vvplay as (select up.vid as vid,count(1) AS cnt from tbuplay up, tbuser user where up.uid = user.uid group by up.vid )


ALTER TABLE `tbpcategory` ADD `idx` INT NOT NULL ;

