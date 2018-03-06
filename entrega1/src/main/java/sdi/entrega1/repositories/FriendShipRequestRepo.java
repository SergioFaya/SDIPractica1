package sdi.entrega1.repositories;

import org.springframework.data.repository.CrudRepository;

import sdi.entrega1.entities.User;

public interface FriendShipRequestRepo extends CrudRepository<User, Long>{
	//only interested in find all by user

}
