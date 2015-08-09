package test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.model.Post;
import test.repository.PostRepository;
import test.service.PostService;

@Service("postService")
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public long countPosts() {
		return postRepository.count();
	}

	@Override
	public List<Post> findLatestPosts() {
		return postRepository.findByCreationDateLessThanEqual(System.currentTimeMillis() - 30*24*60*60*1000);
	}

	@Override
	public void createPost() {
		Post p = new Post();
		p.setContent("Blablabla mumble mumble mumble my my my kthxbye");
		p.setCreationDate(System.currentTimeMillis());
		p.setTitle("Post nº" + System.currentTimeMillis());
		postRepository.save(p);
	}

}
