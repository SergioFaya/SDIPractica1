package sdi.entrega1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sdi.entrega1.entities.Post;

public interface PostRepository extends CrudRepository<Post,Long>{

	@Query("Select p from Post p where p.user.email = ?1")
	List<Post> findAllOfUser(String email);

}
