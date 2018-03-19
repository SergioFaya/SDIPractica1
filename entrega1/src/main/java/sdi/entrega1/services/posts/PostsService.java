package sdi.entrega1.services.posts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdi.entrega1.entities.Post;
import sdi.entrega1.repositories.PostRepository;

@Service
public class PostsService {

	@Autowired
	private PostRepository postRepo;
	
	public void createPost(Post post) {
		postRepo.save(post);		
	}

	public List<Post> getUserPost(String name) {
		return postRepo.findAll();//TODO:CAmbiar a amigos
	}
	
}
