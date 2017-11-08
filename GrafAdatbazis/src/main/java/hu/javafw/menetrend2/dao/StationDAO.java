package hu.javafw.menetrend2.dao;

import java.util.List;

import hu.javafw.menetrend2.model.Station;

/**
 * Data access object for Station entity
 * 
 * @author kkrisz
 */
public interface StationDAO {
	
	public void saveOrUpdate(Station station);
	
	public void delete(int id);
	
	public Station get(int id);
	
	public List<Station> list();
}
