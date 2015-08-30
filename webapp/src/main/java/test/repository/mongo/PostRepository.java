package test.repository.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import test.model.mongo.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findByTitle(String title);

	List<Post> findByCreationDateLessThanEqual(long date);

}
