package sdi.entrega1.services.friends.request;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sdi.entrega1.entities.FriendShipRequest;
import sdi.entrega1.entities.User;
import sdi.entrega1.repositories.FriendShipRequestRepo;

@Service
public class FriendshipRequestService {

	@Autowired
	private FriendShipRequestRepo friendshipRepo;
	
	public Page<FriendShipRequest> getAllFriendshipRequests(Pageable pageable) {
		return friendshipRepo.findAll(pageable);
	}
	
	public boolean addRequest(User origin, User destiny) {
		List<FriendShipRequest> repoRequest = friendshipRepo.isInDb(origin.getEmail(),destiny.getEmail());
		if(repoRequest == null || repoRequest.isEmpty()) {
			friendshipRepo.save(new FriendShipRequest(origin, destiny) );
			return true;
		}
		return false;
	}

	public Page<FriendShipRequest> getMyFriendshipRequests(Pageable pageable, Principal principal) {
		return friendshipRepo.findAllByUserDestiny(pageable,principal.getName());
	}

	public void acceptRequest(User authenticated, User friend) {
		//TODO:LO del return 
		friendshipRepo.acceptFriendship(authenticated.getEmail(), friend.getEmail());
	}

	
	
}
