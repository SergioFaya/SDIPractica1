package sdi.entrega1.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "friendShipRequest")
public class FriendShipRequest {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user_source")
	private User user_source;
	
	@ManyToOne
	@JoinColumn(name = "received_requests")
	private User user_destiny;
	
	private String message;

	public FriendShipRequest() { 
		
	}
	
	public FriendShipRequest(User origin, User destiny, String message) {
		this.user_source = origin;
		this.user_destiny = destiny;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
	
	
}