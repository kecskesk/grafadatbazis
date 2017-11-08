package hu.bme.szoftarch.graphdb.dao;

import java.util.List;

import hu.bme.szoftarch.graphdb.model.User;

/**
 * Data access object for User entity
 * 
 * @author kkrisz
 */
public interface UserDAO {
	
	public void saveOrUpdate(User user);
	
	public void delete(int id);
	
	public User get(int id);
	
	public List<User> list();
}
