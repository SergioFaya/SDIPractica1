package sdi.entrega1.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sdi.entrega1.entities.FriendShipRequest;

public interface FriendShipRequestRepo extends CrudRepository<FriendShipRequest, Long> {

	Page<FriendShipRequest> findAll(Pageable pageable);
	
	@Query("Select f from FriendShipRequest f where f.userSource.email = ?1 and f.userDestiny.email = ?2")
	List<FriendShipRequest> isInDb(String emailOrigin, String emailDestiny);

	@Query("Select f from FriendShipRequest f where f.userDestiny.email = ?1")
	Page<FriendShipRequest> findAllByUserDestiny(Pageable pageable, String email);
	
	@Query("Select f from FriendShipRequest f where f.userSource.email = ?1 and f.isAccepted = false")
	Page<FriendShipRequest> findAllByUserSource(Pageable pageable, String email);

	@Query("Update FriendShipRequest f SET f.isAccepted = true where f.userSource.email = ?1 and f.userDestiny.email = ?2")
	FriendShipRequest acceptFriendship(String emailOrigin, String emailDestiny);
	

}
