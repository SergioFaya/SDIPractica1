package sdi.entrega1.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "friendShip_Request")
public class FriendShipRequest {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private User userSource;

	@ManyToOne
	private User userDestiny;

	private boolean isAccepted;

	public FriendShipRequest() {

	}

	public FriendShipRequest(User userSource, User userDestiny) {
		this.userSource = userSource;
		this.userDestiny = userDestiny;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public User getUserSource() {
		return userSource;
	}

	public void setUserSource(User userSource) {
		this.userSource = userSource;
	}

	public User getUserDestiny() {
		return userDestiny;
	}

	public void setUserDestiny(User userDestiny) {
		this.userDestiny = userDestiny;
	}

}