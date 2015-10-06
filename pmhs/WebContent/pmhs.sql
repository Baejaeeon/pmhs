CREATE TABLE member (
	m_id VARCHAR2(12) NOT NULL,
	m_name VARCHAR2(12) NOT NULL,
	m_studentNum NUMBER,
	m_passwd VARCHAR2(12) NOT NULL,
	m_zipcode1 NUMBER NOT NULL,
	m_zipcode2 NUMBER NOT NULL,
	m_address1 VARCHAR2(50) NOT NULL,
	m_address2 VARCHAR2(50) NOT NULL,
	m_birthday DATE NOT NULL,
	m_email VARCHAR2(30),
	m_phone VARCHAR2(13) NOT NULL,
	m_gender VARCHAR2(2) NOT NULL,
	PRIMARY KEY(m_id)
)

ALTER TABLE member MODIFY m_gender VARCHAR2(7);

CREATE SEQUENCE member_seq;

CREATE TABLE zipcode(
	seq NUMBER NOT NULL,
	zipcode VARCHAR2(7) NOT NULL,
	sido VARCHAR2(10),
	gugun VARCHAR2(30),
	dong VARCHAR2(43),
	bungi VARCHAR2(25),
	PRIMARY KEY(seq)
)

CREATE TABLE qnABoard(
	q_num NUMBER NOT NULL,
	q_writer VARCHAR2(12) NOT NULL,
	q_email VARCHAR2(30),
	q_subject VARCHAR2(50) NOT NULL,
	q_passwd VARCHAR2(12),
	q_reg_date TIMESTAMP NOT NULL,
	q_readcount NUMBER DEFAULT 0,
	q_ref NUMBER NOT NULL,
	q_re_step NUMBER NOT NULL,
	q_re_level NUMBER NOT NULL,
	q_content VARCHAR2(400),
	q_ip VARCHAR2(20) NOT NULL,
	PRIMARY KEY(q_num)
)

CREATE SEQUENCE qnABoard_seq;
DROP SEQUENCE qnaboard_seq;

CREATE TABLE noticeBoard(
	n_num NUMBER NOT NULL,
	n_writer VARCHAR2(12) NOT NULL,
	n_email VARCHAR2(30),
	n_subject VARCHAR2(50) NOT NULL,
	n_passwd VARCHAR2(12),
	n_reg_date TIMESTAMP NOT NULL,
	n_readcount NUMBER NOT NULL,
	n_content VARCHAR2(400),
	n_ip VARCHAR2(20) NOT NULL,
	PRIMARY KEY(n_num)
)

CREATE SEQUENCE noticeboard_seq;

CREATE TABLE messageInfo(
	msg_num	NUMBER NOT NULL,
	msg_receiver VARCHAR2(15) NOT NULL,
	msg_title VARCHAR2(30) NOT NULL,
	msg_date TIMESTAMP NOT NULL,
	msg_content VARCHAR2(200) NOT NULL,
	m_id VARCHAR2(12) NOT NULL,
	PRIMARY KEY(msg_num),
	FOREIGN KEY(m_id) REFERENCES member(m_id)
)

CREATE SEQUENCE messageInfo_seq;

CREATE TABLE commentInfo(
	c_seq NUMBER NOT NULL,
	c_content VARCHAR2(400),
	c_reg_date TIMESTAMP NOT NULL,
	q_num NUMBER NOT NULL,
	m_id VARCHAR2(12) NOT NULL,
	FOREIGN KEY(q_num) REFERENCES qnABoard(q_num),
	FOREIGN KEY(m_id) REFERENCES member(m_id)
)

CREATE SEQUENCE comment_seq;

CREATE TABLE pcInfo(
	p_seq NUMBER NOT NULL,
	p_num NUMBER NOT NULL,
	p_unit VARCHAR2(50) NOT NULL,
	p_department VARCHAR2(50),
	p_lectureroom NUMBER NOT NULL,
	p_isdeclare NUMBER DEFAULT 0,
	p_isreservation NUMBER DEFAULT 0,
	PRIMARY KEY(p_seq)
)

INSERT INTO pcInfo VALUES(1,1,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(2,2,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(3,3,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(4,4,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(5,5,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(6,6,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(7,7,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(8,8,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(9,9,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(10,10,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(11,11,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(12,12,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(13,13,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(14,14,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(15,15,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(16,16,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(17,17,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(18,18,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(19,19,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(20,20,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(21,21,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(22,22,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(23,23,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(24,24,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(25,25,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(26,26,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(27,27,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(28,28,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(29,29,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(30,30,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(31,31,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(32,1,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(33,2,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(34,3,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(35,4,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(36,5,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(37,6,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(38,7,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(39,8,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(40,9,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(41,10,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(42,11,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(43,12,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(44,13,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(45,14,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(46,15,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(47,16,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(48,17,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(49,18,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(50,19,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(51,20,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(52,21,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(53,22,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(54,23,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(55,24,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(56,25,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(57,26,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(58,27,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(59,28,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(60,29,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(61,30,'공과 대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(62,31,'공과 대학','IT 공학부', '504', 0, 0);

CREATE TABLE errorPCInfo(
	e_declarenum NUMBER NOT NULL,
	e_declaredate TIMESTAMP NOT NULL,
	e_errorsymptom VARCHAR2(15),
	p_unit VARCHAR2(50) NOT NULL,
	p_department VARCHAR2(50) NOT NULL,
	p_lectureroom NUMBER NOT NULL,
	p_num NUMBER NOT NULL,
	e_name VARCHAR2(12) NOT NULL,
	e_phone VARCHAR2(13) NOT NULL,
	PRIMARY KEY(e_declarenum)
)

CREATE SEQUENCE errorInfo_seq;
DROP SEQUENCE errorInfo_seq;

CREATE TABLE reservationInfo(
	e_reservationnum NUMBER NOT NULL,
	e_subject VARCHAR2(25) NOT NULL,
	e_declaredate TIMESTAMP NOT NULL,
	e_errorsymptom VARCHAR2(15),
	p_unit VARCHAR2(50) NOT NULL,
	p_department VARCHAR2(50) NOT NULL,
	p_lectureroom NUMBER NOT NULL,
	p_num NUMBER NOT NULL,
	e_name VARCHAR2(12) NOT NULL,
	e_phone VARCHAR2(13) NOT NULL,
	e_time VARCHAR2(15) NOT NULL,
	PRIMARY KEY(e_reservationnum)
)

CREATE SEQUENCE reservation_seq;
DROP SEQUENCE reservation_seq;

DROP TABLE reservationInfo PURGE;
DROP TABLE errorPCInfo PURGE;
DROP TABLE commentInfo PURGE;
DROP TABLE messageInfo PURGE;
DROP TABLE member PURGE;
DROP TABLE noticeBoard PURGE;

select * from pcInfo;
select * from RESERVATIONINFO;
select * from messageInfo;
select * from member;
select * from zipcode;
select * from errorPCInfo;
select * from commentInfo;
DROP TABLE zipcode PURGE;
DROP TABLE qnaBoard PURGE;
DROP TABLE pcInfo PURGE;
DROP TABLE errorPCInfo PURGE;
DROP TABLE pcInfo PURGE;

select * from qnaBoard;