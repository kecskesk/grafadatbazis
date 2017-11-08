package hu.javafw.menetrend2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import hu.javafw.menetrend2.model.Station;

/**
 * Implementation for the station DAO.
 * 
 * @author kkrisz
 */
@Component
public class StationDAOImpl implements StationDAO {

	private JdbcTemplate jdbcTemplate;

	public StationDAOImpl(){}

	@Autowired
	public StationDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Station station) {
		if (station.getId() > 0) {
			// update
			String sql = "UPDATE station SET station_name=? WHERE id=?";
			jdbcTemplate.update(sql, station.getStationName(), station.getId());
		} else {
			// insert
			String sql = "INSERT INTO station (station_name) VALUES (?)";
			jdbcTemplate.update(sql, station.getStationName());
		}
		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM station WHERE id=?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Station> list() {
		String sql = "SELECT * FROM station";
		List<Station> listContact = jdbcTemplate.query(sql, new RowMapper<Station>() {

			@Override
			public Station mapRow(ResultSet rs, int rowNum) throws SQLException {
				Station aStation = new Station();
	
				aStation.setId(rs.getInt("id"));
				aStation.setStationName(rs.getString("station_name"));
				
				return aStation;
			}
			
		});
		
		return listContact;
	}

	@Override
	public Station get(int id) {
		String sql = "SELECT * FROM station WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Station>() {

			@Override
			public Station extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Station station = new Station();
					station.setId(rs.getInt("id"));
					station.setStationName(rs.getString("station_name"));
					return station;
				}
				
				return null;
			}
			
		});
	}

}
