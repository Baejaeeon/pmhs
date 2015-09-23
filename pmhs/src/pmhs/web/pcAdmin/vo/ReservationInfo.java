package pmhs.web.pcAdmin.vo;

import java.sql.Timestamp;

public class ReservationInfo {
	private int declareNum;
	private String subject;
	private Timestamp declareDate;
	private String errorSymptom;
	private int num;
	private String id;
	private String name;
	private String phone;
	private String time;
	
	public int getDeclareNum() {
		return declareNum;
	}
	public void setDeclareNum(int declareNum) {
		this.declareNum = declareNum;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Timestamp getDeclareDate() {
		return declareDate;
	}
	public void setDeclareDate(Timestamp declareDate) {
		this.declareDate = declareDate;
	}
	public String getErrorSymptom() {
		return errorSymptom;
	}
	public void setErrorSymptom(String errorSymptom) {
		this.errorSymptom = errorSymptom;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
