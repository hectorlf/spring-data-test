package test.service;

import java.util.List;

import test.model.mongo.Post;

public interface PostService {

	long countPosts();
	
	List<Post> findLatestPosts();

	void createPost();

}
