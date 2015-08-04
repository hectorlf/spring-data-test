package test.test.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;

import test.model.Message;
import test.repository.MessageRepository;

public class JpaRepositoryTests extends BaseRepositoryTest {

	@Autowired
	private MessageRepository messageRepository;

	@Test
	public void testInsertion() {
		Message m = new Message();
		m.setSubject("Hi!");
		m.setText("I'm not spam!");
		m.setType(Message.MAIL_MESSAGE_TYPE);
		messageRepository.saveAndFlush(m);
	}

	@Test(expected=JpaSystemException.class)
	public void testConstraintViolation1() {
		Message m = new Message();
		messageRepository.saveAndFlush(m);
	}

}
