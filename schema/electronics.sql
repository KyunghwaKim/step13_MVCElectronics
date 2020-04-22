drop table Electronics;

create table Electronics(
	model_num varchar2(15) primary key, --상품번호
	model_name varchar2(20) not null, --상품이름
	price int, --가격
	description varchar2(100), --설명
	password varchar2(20) not null, --비번
	writeday date  not null, --등록일
	readnum int, --조회수
	fname varchar2(50), --파일이름
        fsize int --파일용량
);


insert into Electronics values('NT900X4D-A68','삼성샌스',1300000,'Windows 8','1111',sysdate,0,null,0);
insert into Electronics values('SHV-E250S','Galaxy Note II',1000000,'Wi-Fi bluetooth 4.0','1111',sysdate,0,null,0);
insert into Electronics values('NT900X4D-A99S','삼성샌스',1700000,'Windows 8','1111',sysdate,0,null,0);


SELECT SYS_DATETIME; --시스템 현재날짜와 시분초

SELECT writeday, to_char(writeday, 'YYYY-MM-DD HH:MI:SS AM') FROM Electronics;

 select model_num,model_name,price, description,password , to_char(writeday, 'YYYY-MM-DD HH:MI:SS AM') as writeday ,readnum
 from Electronics ;

commit

SELECT * FROM Electronics ORDER BY writeday DESC