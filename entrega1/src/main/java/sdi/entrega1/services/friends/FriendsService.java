package sdi.entrega1.services.friends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sdi.entrega1.entities.Friend;
import sdi.entrega1.repositories.FriendRepo;

@Service
public class FriendsService {

	@Autowired
	private FriendRepo repository;
	
	public Page<Friend> getAllFriends(Pageable pageable) {
		return repository.findAllFriends(pageable);
	}
}
