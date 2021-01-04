package de.hirthe.ffw.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.hirthe.ffw.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	
	List<User> findByLastname(String lastname);

	//List<User> findByLastnameFirstname(String lastnamefirstname);

}
