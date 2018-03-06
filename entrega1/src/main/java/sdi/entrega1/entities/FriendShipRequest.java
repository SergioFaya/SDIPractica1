package sdi.entrega1.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "friendShipRequest")
public class FriendShipRequest {

	@ManyToOne
	@JoinColumn(name = "user_source")
	private User user_source;
	
	@OneToOne
	@JoinColumn(name = "user_destiny")
	private User user_destiny;
	
	private String message;
	
}