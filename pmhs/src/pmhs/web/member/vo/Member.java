package pmhs.web.member.vo;

public class Member {
	private int m_num;
	private String m_id;
	private String m_name;
	private int m_studentNum;
	private String m_passwd;
	private int m_zipcode1;
	private int m_zipcode2;
	private String m_address1;
	private String m_address2;
	private String m_email;
	private String m_phone;
	private String m_gender;

	public Member(int m_num, String m_id, String m_name, int m_studentNum, String m_passwd, int m_zipcode1,
			int m_zipcode2, String m_address1, String m_address2, String m_email, String m_phone, String m_gender) {
		super();
		this.m_num = m_num;
		this.m_id = m_id;
		this.m_name = m_name;
		this.m_studentNum = m_studentNum;
		this.m_passwd = m_passwd;
		this.m_zipcode1 = m_zipcode1;
		this.m_zipcode2 = m_zipcode2;
		this.m_address1 = m_address1;
		this.m_address2 = m_address2;
		this.m_email = m_email;
		this.m_phone = m_phone;
		this.m_gender = m_gender;
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

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public int getM_studentNum() {
		return m_studentNum;
	}

	public void setM_studentNum(int m_studentNum) {
		this.m_studentNum = m_studentNum;
	}

	public String getM_passwd() {
		return m_passwd;
	}

	public void setM_passwd(String m_passwd) {
		this.m_passwd = m_passwd;
	}

	public int getM_zipcode1() {
		return m_zipcode1;
	}

	public void setM_zipcode1(int m_zipcode1) {
		this.m_zipcode1 = m_zipcode1;
	}

	public int getM_zipcode2() {
		return m_zipcode2;
	}

	public void setM_zipcode2(int m_zipcode2) {
		this.m_zipcode2 = m_zipcode2;
	}

	public String getM_address1() {
		return m_address1;
	}

	public void setM_address1(String m_address1) {
		this.m_address1 = m_address1;
	}

	public String getM_address2() {
		return m_address2;
	}

	public void setM_address2(String m_address2) {
		this.m_address2 = m_address2;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getM_phone() {
		return m_phone;
	}

	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}

	public String getM_gender() {
		return m_gender;
	}

	public void setM_gender(String m_gender) {
		this.m_gender = m_gender;
	}

}
