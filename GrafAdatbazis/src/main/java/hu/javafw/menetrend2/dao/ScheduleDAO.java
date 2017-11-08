package hu.javafw.menetrend2.dao;

import java.util.List;

import hu.javafw.menetrend2.model.Schedule;
import hu.javafw.menetrend2.model.SearchContext;

/**
 * Data access object for Schedule entity
 * 
 * @author kkrisz
 */
public interface ScheduleDAO {
	
	public void saveOrUpdate(Schedule schedule);
	
	public void delete(int id);
	
	public Schedule get(int id);
	
	public List<Schedule> list();
	
	public List<Schedule> find(SearchContext searchContext);
}
