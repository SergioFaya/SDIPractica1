package sdi.entrega1.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sdi.entrega1.entities.Post;

public interface PostRepository extends CrudRepository<Post,Long>{

	List<Post> findAll();
	List<Post> findAllOfUser();
	List<Post> findAllOfFriend();
}
