package pmhs.web.message.vo;

import java.sql.Timestamp;

public class MessageVO {
	private int messageNum;
	private String messageWriter;
	private String messageReceiver;
	private String Title;
	private Timestamp messageReg_date;
	private String messageContent;
	
	public MessageVO() {
		// TODO Auto-generated constructor stub
	}
	
	public int getMessageNum() {
		return messageNum;
	}
	public void setMessageNum(int messageNum) {
		this.messageNum = messageNum;
	}
	public String getMessageWriter() {
		return messageWriter;
	}
	public void setMessageWriter(String messageWriter) {
		this.messageWriter = messageWriter;
	}
	public String getMessageReceiver() {
		return messageReceiver;
	}
	public void setMessageReceiver(String messageReceiver) {
		this.messageReceiver = messageReceiver;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public Timestamp getMessageReg_date() {
		return messageReg_date;
	}
	public void setMessageReg_date(Timestamp messageReg_date) {
		this.messageReg_date = messageReg_date;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	
}
