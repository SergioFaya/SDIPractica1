package sdi.entrega1.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private User user;
	private String message;
	private String title;
	private String photoPath;
	private String date;

	public Post() {
		super();
	}

	public Post(String title, String message) {
		this();
		this.title = title;
		this.message = message;
		DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss ");
		this.date = hourdateFormat.format(new Date());
		
	}

	// TEST
	public Post(String title, String message, User user) {
		this(title,message);
		this.user = user;
	}

	// TEST
	public Post(String title, String message, User user, String photopath) {
		this(title,message,user);
		this.photoPath = photopath;
	}

	public Post(String title, String message, String photoPath) {
		this(title, message);
		this.photoPath = photoPath;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}