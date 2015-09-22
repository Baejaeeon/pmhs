package pmhs.web.pcInfo.vo;

public class PCInfo {
	private int p_num;
	private String p_unit;
	private String p_department;
	private int lectureroom;
	private String lectureImage;
	
	// getter & setter
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public String getP_unit() {
		return p_unit;
	}
	public void setP_unit(String p_unit) {
		this.p_unit = p_unit;
	}
	public String getP_department() {
		return p_department;
	}
	public void setP_department(String p_department) {
		this.p_department = p_department;
	}
	public int getLectureroom() {
		return lectureroom;
	}
	public void setLectureroom(int lectureroom) {
		this.lectureroom = lectureroom;
	}
	public String getLectureImage() {
		return lectureImage;
	}
	public void setLectureImage(String lectureImage) {
		this.lectureImage = lectureImage;
	}
}
