package test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.model.Message;
import test.repository.MessageRepository;
import test.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Override
	public Integer countMailMessages() {
		return messageRepository.countByType(Message.MAIL_MESSAGE_TYPE);
	}

	@Override
	public List<Message> findImportantMessages() {
		return messageRepository.findBySubject("Important");
	}

	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@Override
	public void createImportantMessage() {
		Message m = new Message();
		m.setSubject("Important");
		m.setText("Message you need to read nº " + System.currentTimeMillis());
		m.setType(Message.MAIL_MESSAGE_TYPE);
		messageRepository.save(m);
	}

}
