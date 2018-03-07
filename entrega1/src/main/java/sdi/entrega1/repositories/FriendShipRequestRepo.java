package sdi.entrega1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import sdi.entrega1.entities.FriendShipRequest;

public interface FriendShipRequestRepo extends CrudRepository<FriendShipRequest, Long>{
	
	
	
	Page<FriendShipRequest> findAll(Pageable pageable);

}
