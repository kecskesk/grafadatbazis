package hu.javafw.menetrend2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import hu.javafw.menetrend2.model.User;

/**
 * Implementation for the user DAO.
 * 
 * @author kkrisz
 */
@Component	
public class UserDAOImpl implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	
	public UserDAOImpl(){}

	@Autowired
	public UserDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(User user) {
		if (user.getId() > 0) {
			// update
			String sql = "UPDATE users SET username=? , password=? WHERE id=?";
			jdbcTemplate.update(sql, 
                    user.getUsername(), 
                    user.getPassword(),
                    user.getId());
			if(user.getRoleText() != null) {
				String r_sql = "UPDATE user_roles SET user_role=? WHERE username=?";
				jdbcTemplate.update(r_sql, 
						user.getRoleText(),
	                    user.getUsername());
			}
			
		} else {
			// insert
			String sql = "INSERT INTO users (username, enabled, password) VALUES (?, 1, ?)";
			jdbcTemplate.update(sql, 
                    user.getUsername(), 
                    user.getPassword());

			if(user.getRoleText() != null) {
				String r_sql = "INSERT INTO user_roles (username, user_role) VALUES (?, ?)";
				jdbcTemplate.update(r_sql,
	                    user.getUsername(), 
						user.getRoleText());
			}
		}		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM users WHERE id=?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public List<User> list() {
		String sql = "SELECT * FROM users";
		Map<String, User> byName = new HashMap<>();
		List<User> listContact = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User aUser = new User();	
				aUser.setId(rs.getInt("id"));
				aUser.setUsername(rs.getString("username"));
				aUser.setPassword(rs.getString("password"));	
				
				byName.put(aUser.getUsername(), aUser);
				
				return aUser;
			}			
		});

		String r_sql = "SELECT * FROM user_roles";		
		jdbcTemplate.query(r_sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				byName.get(rs.getString("username")).setRoleText(rs.getString("user_role"));
			}			
		});		
		return listContact;
	}

	@Override
	public User get(int id) {
		String sql = "SELECT * FROM users WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    
                    String r_sql = "SELECT * FROM user_roles WHERE username='" + user.getUsername()+"'";
            		jdbcTemplate.query(r_sql, new RowCallbackHandler(){
            			@Override
            			public void processRow(ResultSet rs) throws SQLException {
            				user.setRoleText(rs.getString("user_role"));
            			}
            		});
					return user;
				}				
				return null;
			}			
		});
	}
}
