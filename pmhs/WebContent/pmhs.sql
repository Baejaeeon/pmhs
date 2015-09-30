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

CREATE TABLE pcInfo(
	p_num NUMBER NOT NULL,
	p_unit VARCHAR2(50) NOT NULL,
	p_department VARCHAR2(50),
	p_lectureroom NUMBER NOT NULL,
	p_isdeclare NUMBER DEFAULT 0,
	p_isreservation NUMBER DEFAULT 0,
	PRIMARY KEY(p_num)
)

INSERT INTO pcInfo VALUES(1,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(2,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(3,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(4,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(5,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(6,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(7,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(8,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(9,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(10,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(11,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(12,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(13,'공과대학','IT 공학부', '504', 1, 0);
INSERT INTO pcInfo VALUES(14,'공과대학','IT 공학부', '504', 0, 1);
INSERT INTO pcInfo VALUES(15,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(16,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(17,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(18,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(19,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(20,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(21,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(22,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(23,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(24,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(25,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(26,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(27,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(28,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(29,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(30,'공과대학','IT 공학부', '504', 0, 0);
INSERT INTO pcInfo VALUES(31,'공과대학','IT 공학부', '504', 0, 0);

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
	PRIMARY KEY(e_declarenum),
	FOREIGN KEY(p_num) REFERENCES pcInfo(p_num)
)

CREATE SEQUENCE errorInfo_seq;
DROP SEQUENCE errorInfo_seq;

CREATE TABLE reservationInfo(
	e_declarenum NUMBER NOT NULL,
	e_subject VARCHAR2(25) NOT NULL,
	e_declaredate TIMESTAMP NOT NULL,
	e_errorsymptom VARCHAR2(15),
	p_num NUMBER NOT NULL,
	m_id VARCHAR2(12) NOT NULL,
	e_name VARCHAR2(12) NOT NULL,
	e_phone VARCHAR2(13) NOT NULL,
	e_time VARCHAR2(10) NOT NULL,
	PRIMARY KEY(e_declarenum),
	FOREIGN KEY(p_num) REFERENCES pcInfo(p_num),
	FOREIGN KEY(m_id) REFERENCES member(m_id)
)


DROP TABLE reservationInfo PURGE;
DROP TABLE errorPCInfo PURGE;
DROP TABLE commentInfo PURGE;
DROP TABLE messageInfo PURGE;
DROP TABLE member PURGE;
DROP TABLE board PURGE;

select * from pcInfo;
select * from RESERVATIONINFO;
select * from messageInfo;
select * from member;
select * from zipcode;
select * from errorPCInfo;
DROP TABLE zipcode PURGE;
DROP TABLE qnaBoard PURGE;
DROP TABLE pcInfo PURGE;
DROP TABLE errorPCInfo PURGE;
DROP TABLE pcInfo PURGE;

select * from qnaBoard;