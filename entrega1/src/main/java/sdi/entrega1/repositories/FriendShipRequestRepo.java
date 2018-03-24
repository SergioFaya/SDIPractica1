package sdi.entrega1.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sdi.entrega1.entities.FriendShipRequest;

public interface FriendShipRequestRepo extends CrudRepository<FriendShipRequest, Long> {

	Page<FriendShipRequest> findAll(Pageable pageable);

	@Query("Select f from FriendShipRequest f where f.userSource.email = ?1 and f.userDestiny.email = ?2")
	List<FriendShipRequest> isInDb(String emailOrigin, String emailDestiny);

	@Query("Select f from FriendShipRequest f where f.userDestiny.email = ?1 and f.isAccepted = false")
	Page<FriendShipRequest> findAllByUserDestiny(Pageable pageable, String email);

	@Query("Select f from FriendShipRequest f where f.userSource.email = ?1 and f.isAccepted = false")
	Page<FriendShipRequest> findAllByUserSource(Pageable pageable, String email);

	@Query("Select f from FriendShipRequest f where f.userSource.email = ?1 and f.isAccepted = true")
	Page<FriendShipRequest> findFriends(Pageable pageable, String email);

	@Modifying
	@Transactional
	@Query("Update FriendShipRequest f SET f.isAccepted = true WHERE (f.userSource.email = ?1 and f.userDestiny.email = ?2)")
	void updateRequest(String emailSource, String emailDestiny);

	@Query("Select f from FriendShipRequest f where f.userSource.email = ?1 and f.userDestiny.email = ?2")
	FriendShipRequest findRequest(String emailSource, String emailDestiny);

}
