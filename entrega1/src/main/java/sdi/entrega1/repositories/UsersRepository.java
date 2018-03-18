package sdi.entrega1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sdi.entrega1.entities.User;

public interface UsersRepository extends CrudRepository<User, Long>{
	
	User findByEmail(String email);
	
	Page<User> findAll(Pageable pageable);
	
	@Query("Select r from User r where r.email <> ?1")
	Page<User> searchAllButUser(Pageable pageable,String email);
	
	@Query("SELECT r FROM User r WHERE (LOWER(r.name) LIKE LOWER(?1) OR " + "LOWER(r.email) LIKE LOWER(?1))")
	Page<User> searchByNombreAndEmail(Pageable pageable, String searchText);

	@Query("Select f.userDestiny from FriendShipRequest f where f.userSource.email = ?1 and f.isAccepted = true")
	Page<User> findAllByUserDestinyAndIsAccepted(Pageable pageable, String email);
}
