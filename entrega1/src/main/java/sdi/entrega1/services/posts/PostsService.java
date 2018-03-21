package sdi.entrega1.services.posts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdi.entrega1.entities.Post;
import sdi.entrega1.repositories.PostRepository;
import sdi.entrega1.repositories.UsersRepository;

@Service
public class PostsService {

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UsersRepository usersRepo;
	
	public void createPost(Post post) {
		postRepo.save(post);		
	}

	public List<Post> getUserPost(String email) {
		return postRepo.findAllOfUser(email);
	}
		
	public List<Post> getUserPost(Long id) {
		return postRepo.findAllOfUser(usersRepo.findOne(id).getEmail());
	}
}
