package sdi.entrega1.entities;

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
	private Date date;
	private String message;
	private String title;
	private String photoPath;
	
	
	public Post() {
		super();
	}
	
	public Post(String title, String message) {
		this();
		this.title=title;
		this.message = message;
		this.date = new Date();
	}
		
	public Post(String title, String message, String photoPath ) {
		this(title,message);
		this.photoPath = photoPath;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
		
	
	
}
