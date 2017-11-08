package hu.bme.szoftarch.graphdb.dao;

import java.util.List;

import hu.bme.szoftarch.graphdb.model.Graph;
import hu.bme.szoftarch.graphdb.model.SearchContext;

/**
 * Data access object for graph entity
 * 
 * @author kkrisz
 */
public interface GraphDAO {
	
	public void saveOrUpdate(Graph graph);
	
	public void delete(int id);
	
	public Graph get(int id);
	
	public List<Graph> list();
	
	public List<Graph> find(SearchContext searchContext);
}
