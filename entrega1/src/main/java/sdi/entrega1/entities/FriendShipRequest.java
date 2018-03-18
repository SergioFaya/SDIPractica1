package sdi.entrega1.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FriendShip_Request")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userDestiny == null) ? 0 : userDestiny.hashCode());
		result = prime * result + ((userSource == null) ? 0 : userSource.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FriendShipRequest other = (FriendShipRequest) obj;
		if (userDestiny == null) {
			if (other.userDestiny != null)
				return false;
		} else if (!userDestiny.equals(other.userDestiny))
			return false;
		if (userSource == null) {
			if (other.userSource != null)
				return false;
		} else if (!userSource.equals(other.userSource))
			return false;
		return true;
	}

	
}