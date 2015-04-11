package test.service;

import java.util.List;

import test.model.Message;

public interface MessageService {

	Integer countMailMessages();
	
	List<Message> findImportantMessages();

}
