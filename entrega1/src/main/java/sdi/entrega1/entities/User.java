package sdi.entrega1.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;
	private String password;
	@Transient
	private String passwordConfirm;
	private String role;
	
	@OneToMany(mappedBy = "userSource")
	private Set<FriendShipRequest> sentRequests;
	
	@OneToMany(mappedBy = "userDestiny")
	private Set<FriendShipRequest> receivedRequests;
	
	public User() {

	}

	public User(String email, String name, String lastName) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

	public Set<FriendShipRequest> getSentRequests() {
		return sentRequests;
	}

	public void setSentRequests(Set<FriendShipRequest> sentRequests) {
		this.sentRequests = sentRequests;
	}

	public Set<FriendShipRequest> getReceivedRequests() {
		return receivedRequests;
	}

	public void setReceivedRequests(Set<FriendShipRequest> receivedRequests) {
		this.receivedRequests = receivedRequests;
	}

}
