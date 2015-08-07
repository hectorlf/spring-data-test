package test.service;

import java.util.List;

import test.model.Post;

public interface PostService {

	long countPosts();
	
	List<Post> findLatestPosts();

}
