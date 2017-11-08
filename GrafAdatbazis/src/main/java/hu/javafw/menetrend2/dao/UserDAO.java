package hu.javafw.menetrend2.dao;

import java.util.List;

import hu.javafw.menetrend2.model.User;

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
