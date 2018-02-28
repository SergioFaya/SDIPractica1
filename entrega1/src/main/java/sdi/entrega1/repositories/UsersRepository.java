package sdi.entrega1.repositories;

import org.springframework.data.repository.CrudRepository;
import sdi.entrega1.entities.User;

public interface UsersRepository extends CrudRepository<User, Long>{
	
	User findByEmail(String email);

}