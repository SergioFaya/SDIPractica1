package sdi.entrega1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import sdi.entrega1.entities.Friend;

public interface FriendRepo extends CrudRepository<Friend, Long>{

	Page<Friend> findAll(Pageable pageable);

}
