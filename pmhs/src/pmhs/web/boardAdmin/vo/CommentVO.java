package pmhs.web.boardAdmin.vo;

import java.sql.Date;
import java.sql.Timestamp;


public class CommentVO {
	
	private int c_num;
	private String c_writer;
	private String c_content;
	private Timestamp c_reg_date;
	
	public CommentVO(int c_num, String c_writer, String c_content, Timestamp c_reg_date) {
		super();
		this.c_num = c_num;
		this.c_writer = c_writer;
		this.c_content = c_content;
		this.c_reg_date = c_reg_date;
	}
	
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	public String getC_writer() {
		return c_writer;
	}
	public void setC_writer(String c_writer) {
		this.c_writer = c_writer;
	}
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public Timestamp getC_reg_date() {
		return c_reg_date;
	}
	public void setC_reg_date(Timestamp c_reg_date) {
		this.c_reg_date = c_reg_date;
	}	
}
