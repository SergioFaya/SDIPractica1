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
	@JoinColumn(name = "user_source")
	private User user_source;
	
	@ManyToOne
	@JoinColumn(name = "friend")
	private Friend friend;
	
	
	private String message;

	public FriendShipRequest() { 
		
	}
	
	public FriendShipRequest(User origin, String message) {
		this.user_source = origin;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
	
	
}