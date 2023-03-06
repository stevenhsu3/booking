package com.javainuse.dao;

import com.javainuse.model.UserDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<UserDO, Integer> {
	
	UserDO findByUsername(String username);
	
}