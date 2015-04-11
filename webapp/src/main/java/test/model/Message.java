package test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="messages")
public class Message extends PersistentObject {
	
	public static final Integer MAIL_MESSAGE_TYPE = Integer.valueOf(1);
	public static final Integer SMS_MESSAGE_TYPE = Integer.valueOf(2);

	@Column(name="msg_type",nullable=false)
	private Integer type;

	private String subject;

	@Column(nullable=false)
	private String text;

	// getters & setters

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

}