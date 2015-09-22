package pmhs.web.pcInfo.vo;

import java.sql.Timestamp;

public class ErrorPCInfo {
	private int e_declarenum;
	private Timestamp e_declaredate;
	private String e_errorsymptom;
	private int p_num;
	private int m_num;
	private String m_id;
	private String e_name;
	private String e_phone;
	
	// getter & setter
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
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
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
