package pmhs.web.pcInfo.vo;

import java.sql.Timestamp;

public class ErrorPCInfo {
	private int e_declarenum;
	private Timestamp e_declaredate;
	private String e_errorsymptom;
	private String e_unit;
	private String e_department;
	private int e_lectureRoom;
	private int p_num;
	private String e_name;
	private String e_phone;
	
	public int getE_declarenum() {
		return e_declarenum;
	}
	public void setE_declarenum(int e_declarenum) {
		this.e_declarenum = e_declarenum;
	}
	public Timestamp getE_declaredate() {
		return e_declaredate;
	}
	public void setE_declaredate(Timestamp e_declaredate) {
		this.e_declaredate = e_declaredate;
	}
	public String getE_errorsymptom() {
		return e_errorsymptom;
	}
	public void setE_errorsymptom(String e_errorsymptom) {
		this.e_errorsymptom = e_errorsymptom;
	}
	public String getE_unit() {
		return e_unit;
	}
	public void setE_unit(String e_unit) {
		this.e_unit = e_unit;
	}
	public String getE_department() {
		return e_department;
	}
	public void setE_department(String e_department) {
		this.e_department = e_department;
	}
	public int getE_lectureRoom() {
		return e_lectureRoom;
	}
	public void setE_lectureRoom(int e_lectureRoom) {
		this.e_lectureRoom = e_lectureRoom;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public String getE_name() {
		return e_name;
	}
	public void setE_name(String e_name) {
		this.e_name = e_name;
	}
	public String getE_phone() {
		return e_phone;
	}
	public void setE_phone(String e_phone) {
		this.e_phone = e_phone;
	}
	
}
