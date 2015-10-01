package pmhs.web.pcAdmin.vo;

import java.sql.Timestamp;

public class ReservationInfo {
	private int reservationNum;
	private String subject;
	private Timestamp declareDate;
	private String errorSymptom;
	private String unit;
	private String department;
	private int lectureRoom;
	private int pcNum;
	private String name;
	private String phone;
	private String time;
	
	public int getReservationNum() {
		return reservationNum;
	}
	public void setReservationNum(int reservationNum) {
		this.reservationNum = reservationNum;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getLectureRoom() {
		return lectureRoom;
	}
	public void setLectureRoom(int lectureRoom) {
		this.lectureRoom = lectureRoom;
	}
	public int getPcNum() {
		return pcNum;
	}
	public void setPcNum(int pcNum) {
		this.pcNum = pcNum;
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
