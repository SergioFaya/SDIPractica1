package sdi.entrega1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sdi.entrega1.entities.User;

public interface UsersRepository extends CrudRepository<User, Long>{
	
	User findByEmail(String email);
	
	Page<User> findAll(Pageable pageable);
	
	@Query("SELECT r FROM User r WHERE (LOWER(r.name) LIKE LOWER(?1) OR " + "LOWER(r.email) LIKE LOWER(?1))")
	Page<User> searchByNombreAndEmail(Pageable pageable, String searchText);

}
