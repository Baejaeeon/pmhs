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
	p_lectureimage VARCHAR2(12),
	PRIMARY KEY(p_num)
)

CREATE TABLE errorPCInfo(
	e_declarenum NUMBER NOT NULL,
	e_declaredate TIMESTAMP NOT NULL,
	e_errorsymptom VARCHAR2(15),
	p_num NUMBER NOT NULL,
	m_id VARCHAR2(12) NOT NULL,
	e_name VARCHAR2(12) NOT NULL,
	e_phone VARCHAR2(13) NOT NULL,
	PRIMARY KEY(e_declarenum),
	FOREIGN KEY(p_num) REFERENCES pcInfo(p_num),
	FOREIGN KEY(m_id) REFERENCES member(m_id)
)

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
DROP TABLE zipcode PURGE;
DROP TABLE qnaBoard PURGE;

select * from qnaBoard;