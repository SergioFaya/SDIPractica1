package sdi.entrega1.services.friends.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sdi.entrega1.entities.FriendShipRequest;
import sdi.entrega1.repositories.FriendShipRequestRepo;

@Service
public class FriendshipRequestService {

	@Autowired
	private FriendShipRequestRepo friendshipRepo;
	
	public Page<FriendShipRequest> getAllFriendshipRequests(Pageable pageable) {
		return friendshipRepo.findAll(pageable);
	}
	
	public void addRequest(FriendShipRequest request) {
		friendshipRepo.save(request);
	}
	
}
