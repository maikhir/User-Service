package de.hirthe.ffw.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private UUID id;
	private String lastname;
	private String firstname;
	
	public User() {
		super();
	}
	
	public User(String firstname, String lastname) {
		this.lastname=lastname;
		this.firstname=firstname;
		this.id=UUID.randomUUID();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String toString() {
		return "User [ID: "+id+", lastname=" + lastname + ", firstname=" + firstname + "]";
	}	
}
