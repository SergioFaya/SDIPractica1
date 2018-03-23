package sdi.entrega1.services.posts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdi.entrega1.entities.Post;
import sdi.entrega1.repositories.PostRepository;

@Service
public class PostsService {

	private static final Logger logger = LoggerFactory.getLogger(PostsService.class);
	
	@Autowired
	private PostRepository postRepo;
	
	public void createPost(Post post) {
		postRepo.save(post);	
		logger.info("El usuario %s ha creado una publicación con título %s", post.getUser().getEmail(), post.getTitle());
	}

	public List<Post> getUserPost(String email) {
		return postRepo.findAllOfUser(email);
	}
}
