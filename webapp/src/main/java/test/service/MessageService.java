package test.service;

import java.util.List;

import test.model.jpa.Message;

public interface MessageService {

	Integer countMailMessages();
	
	List<Message> findImportantMessages();

	void createImportantMessage();

}
