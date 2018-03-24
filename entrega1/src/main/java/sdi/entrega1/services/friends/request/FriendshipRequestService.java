package sdi.entrega1.services.friends.request;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sdi.entrega1.entities.FriendShipRequest;
import sdi.entrega1.entities.User;
import sdi.entrega1.repositories.FriendShipRequestRepo;
import sdi.entrega1.services.posts.PostsService;

@Service
public class FriendshipRequestService {

	private static final Logger logger = LoggerFactory.getLogger(PostsService.class);

	@Autowired
	private FriendShipRequestRepo friendshipRepo;

	public Page<FriendShipRequest> getAllFriendshipRequests(Pageable pageable) {
		return friendshipRepo.findAll(pageable);
	}

	public boolean addRequest(User origin, User destiny) {
		List<FriendShipRequest> repoRequest = friendshipRepo.isInDb(origin.getEmail(), destiny.getEmail());
		if (repoRequest == null || repoRequest.isEmpty()) {
			friendshipRepo.save(new FriendShipRequest(origin, destiny));
			logger.info("El usuario %s ha enviado una petición de amistad a %s", origin.getEmail(), destiny.getEmail());
			return true;
		}
		logger.info("El usuario %s intentó enviar una petición de amistad a %s pero falló", origin.getEmail(),
				destiny.getEmail());
		return false;
	}

	public Page<FriendShipRequest> getMyFriendshipRequests(Pageable pageable, Principal principal) {
		return friendshipRepo.findAllByUserDestiny(pageable, principal.getName());
	}

	public Page<FriendShipRequest> getMyFriends(Pageable pageable, Principal principal) {
		return friendshipRepo.findFriends(pageable, principal.getName());
	}

	public void acceptRequest(User authenticated, User friend) {
		// friendshipRepo.updateRequest(authenticated.getEmail(),friend.getEmail());
		FriendShipRequest request = friendshipRepo.findByUserSourceAndUserDestiny(authenticated, friend);
		if (request != null) {
			friendshipRepo.delete(request);
		}
		FriendShipRequest inverseRequest = friendshipRepo.findByUserSourceAndUserDestiny(friend, authenticated);
		if (inverseRequest != null) {
			friendshipRepo.delete(inverseRequest);
		}
		request = new FriendShipRequest(authenticated, friend);
		request.setAccepted(true);
		inverseRequest = new FriendShipRequest(friend, authenticated);
		inverseRequest.setAccepted(true);
		friendshipRepo.save(request);
		friendshipRepo.save(inverseRequest);
		logger.info("El usuario %s ha aceptado una petición de amistad de %s", authenticated.getEmail(),
				friend.getEmail());
	}

	public FriendShipRequest existsRequest(User authenticated, User friend) {
		return friendshipRepo.findRequest(authenticated.getEmail(), friend.getEmail());
	}


}
