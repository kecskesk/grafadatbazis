package hu.javafw.menetrend2.dao;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import hu.javafw.menetrend2.model.Paging;
import hu.javafw.menetrend2.model.Schedule;
import hu.javafw.menetrend2.model.SearchContext;

/**
 * Implementation for the schedule DAO.
 * 
 * @author kkrisz
 */
@Component
public class ScheduleDAOImpl implements ScheduleDAO {
    
    @Autowired
    public StationDAO stationDAO;

	private JdbcTemplate jdbcTemplate;
	
	public ScheduleDAOImpl(){}

	@Autowired
	public ScheduleDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Schedule schedule) {
		if (schedule.getId() > 0) {
			// update
			String sql = "UPDATE schedule SET origin=?, destination=?, start_time=?, end_time=? WHERE id=?";
			jdbcTemplate.update(sql, 
                    schedule.getOriginId(),
                    schedule.getDestinationId(),
                    schedule.getStartTime(),
                    schedule.getEndTime(), 
                    schedule.getId());
		} else {
			// insert
			String sql = "INSERT INTO schedule (origin, destination, start_time, end_time) VALUES (?, ?, ?, ?)";
			jdbcTemplate.update(sql,
                    schedule.getOriginId(),
                    schedule.getDestinationId(),
                    schedule.getStartTime(),
                    schedule.getEndTime());
		}
		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM schedule WHERE id=?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Schedule> list() {
		String sql = "SELECT * FROM schedule";
		List<Schedule> listContact = jdbcTemplate.query(sql, new RowMapper<Schedule>() {

			@Override
			public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
				Schedule aSchedule = new Schedule();
	
				aSchedule.setId(rs.getInt("id"));
				aSchedule.setOriginId(rs.getInt("origin"));
				aSchedule.setDestinationId(rs.getInt("destination"));
                aSchedule.setOrigin(stationDAO.get(rs.getInt("origin")));
                aSchedule.setDestination(stationDAO.get(rs.getInt("destination")));
                
                Timestamp startTimeStamp = rs.getTimestamp("start_time");
                if (startTimeStamp != null) {
                    aSchedule.setStartTime(new Date(startTimeStamp.getTime()));
                }
                
                Timestamp endTimeStamp = rs.getTimestamp("end_time");
                if (endTimeStamp != null) {
                    aSchedule.setEndTime(new Date(endTimeStamp.getTime()));
                }
                
				return aSchedule;
			}
			
		});
		
		return listContact;
	}

	@Override
	public Schedule get(int id) {
		String sql = "SELECT * FROM schedule WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Schedule>() {

			@Override
			public Schedule extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Schedule schedule = new Schedule();
					schedule.setId(rs.getInt("id"));
                    schedule.setOriginId(rs.getInt("origin"));
                    schedule.setDestinationId(rs.getInt("destination"));
                    schedule.setOrigin(stationDAO.get(rs.getInt("origin")));
                    schedule.setDestination(stationDAO.get(rs.getInt("destination")));     
                
                    Timestamp startTimeStamp = rs.getTimestamp("start_time");
                    if (startTimeStamp != null) {
                        schedule.setStartTime(new Date(startTimeStamp.getTime()));
                    }

                    Timestamp endTimeStamp = rs.getTimestamp("end_time");
                    if (endTimeStamp != null) {
                        schedule.setEndTime(new Date(endTimeStamp.getTime()));
                    }                    
					return schedule;
				}
				
				return null;
			}			
		});
	}

    @Override
    public List<Schedule> find(SearchContext searchContext) {
        Schedule schedule = searchContext.getSchedule();
        Paging paging = searchContext.getPaging();
        
        String sql = "SELECT * FROM schedule";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> conditions = new ArrayList<>();
        if (schedule.getOriginId() > 0) {
            conditions.add("origin = " + schedule.getOriginId());
        }
        if (schedule.getDestinationId() > 0) {
            conditions.add("destination = " + schedule.getDestinationId());
        }
        if (schedule.getStartTime() != null) {
            conditions.add("start_time LIKE '" + sdf.format(schedule.getStartTime()) + "%'");
        }
        if (schedule.getEndTime() != null) {
            conditions.add("end_time LIKE '" + sdf.format(schedule.getEndTime()) + "%'");
        }  
        
        if (conditions.size() > 0) {
            sql += " WHERE ";
        }
        
        for (String c : conditions) {
            sql += c;
            if (conditions.size() > conditions.indexOf(c) + 1) {
                sql += " AND ";
            }
        }
        
        sql += " LIMIT " + paging.getFirst() + ',' + paging.getMax();
        
		List<Schedule> listContact = jdbcTemplate.query(sql, new RowMapper<Schedule>() {

			@Override
			public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
				Schedule aSchedule = new Schedule();
	
				aSchedule.setId(rs.getInt("id"));
				aSchedule.setOriginId(rs.getInt("origin"));
				aSchedule.setDestinationId(rs.getInt("destination"));
                aSchedule.setOrigin(stationDAO.get(rs.getInt("origin")));
                aSchedule.setDestination(stationDAO.get(rs.getInt("destination")));
                
                Timestamp startTimeStamp = rs.getTimestamp("start_time");
                if (startTimeStamp != null) {
                    aSchedule.setStartTime(new Date(startTimeStamp.getTime()));
                }
                
                Timestamp endTimeStamp = rs.getTimestamp("end_time");
                if (endTimeStamp != null) {
                    aSchedule.setEndTime(new Date(endTimeStamp.getTime()));
                }
                
				return aSchedule;
			}
			
		});
		
		return listContact;
    }
}
