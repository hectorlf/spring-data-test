package test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import test.model.jpa.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	Integer countByType(Integer type);

	@Query("select m from Message m where m.subject like ?1%")
	List<Message> findBySubject(String subject);

}
