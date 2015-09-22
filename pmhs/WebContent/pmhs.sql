CREATE TABLE member (
	m_num NUMBER NOT NULL,
	m_id VARCHAR2(12) NOT NULL,
	m_name VARCHAR2(12) NOT NULL,
	m_studentNum NUMBER,
	m_passwd VARCHAR2(12) NOT NULL,
	m_zipcode1 NUMBER NOT NULL,
	m_zipcode2 NUMBER NOT NULL,
	m_address1 VARCHAR2(50) NOT NULL,
	m_address2 VARCHAR2(50) NOT NULL,
	m_email VARCHAR2(30),
	m_phone VARCHAR2(13) NOT NULL,
	m_gender VARCHAR2(2) NOT NULL,
	PRIMARY KEY(m_num, m_id)
)

CREATE TABLE zipcode(
	seq NUMBER NOT NULL,
	zipcode VARCHAR2(7) NOT NULL,
	sido VARCHAR2(10),
	gugun VARCHAR2(30),
	dong VARCHAR2(43),
	bungi VARCHAR2(25),
	PRIMARY KEY(seq)
)

CREATE TABLE board(
	b_num NUMBER NOT NULL,
	b_writer VARCHAR2(12) NOT NULL,
	b_subject VARCHAR2(50) NOT NULL,
	b_passwd VARCHAR2(12),
	b_reg_date TIMESTAMP NOT NULL,
	b_readcount NUMBER NOT NULL,
	b_ref NUMBER NOT NULL,
	b_re_level NUMBER NOT NULL,
	b_content VARCHAR2(400),
	b_ip VARCHAR2(20) NOT NULL,
	PRIMARY KEY(b_num)
)

CREATE TABLE messageInfo(
	msg_num	NUMBER NOT NULL,
	msg_receiver VARCHAR2(15) NOT NULL,
	msg_title VARCHAR2(30) NOT NULL,
	msg_date TIMESTAMP NOT NULL,
	msg_content VARCHAR2(200) NOT NULL,
	m_num NUMBER NOT NULL,
	m_id VARCHAR2(12) NOT NULL,
	PRIMARY KEY(msg_num),
	FOREIGN KEY(m_num, m_id) REFERENCES member(m_num, m_id)
)

CREATE TABLE commentInfo(
	c_seq NUMBER NOT NULL,
	c_content VARCHAR2(400),
	c_reg_date TIMESTAMP NOT NULL,
	b_num NUMBER NOT NULL,
	m_num NUMBER NOT NULL,
	m_id VARCHAR2(12) NOT NULL,
	FOREIGN KEY(b_num) REFERENCES board(b_num),
	FOREIGN KEY(m_num, m_id) REFERENCES member(m_num, m_id)
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
	m_num NUMBER NOT NULL,
	m_id VARCHAR2(12) NOT NULL,
	e_name VARCHAR2(12) NOT NULL,
	e_phone VARCHAR2(13) NOT NULL,
	PRIMARY KEY(e_declarenum),
	FOREIGN KEY(p_num) REFERENCES pcInfo(p_num),
	FOREIGN KEY(m_num, m_id) REFERENCES member(m_num, m_id)
)

CREATE TABLE reservationInfo(
	e_declarenum NUMBER NOT NULL,
	e_declaredate TIMESTAMP NOT NULL,
	e_errorsymptom VARCHAR2(15),
	p_num NUMBER NOT NULL,
	m_num NUMBER NOT NULL,
	m_id VARCHAR2(12) NOT NULL,
	e_name VARCHAR2(12) NOT NULL,
	e_phone VARCHAR2(13) NOT NULL,
	PRIMARY KEY(e_declarenum),
	FOREIGN KEY(p_num) REFERENCES pcInfo(p_num),
	FOREIGN KEY(m_num, m_id) REFERENCES member(m_num, m_id)
)


select * from zipcode;
DROP TABLE zipcode PURGE;