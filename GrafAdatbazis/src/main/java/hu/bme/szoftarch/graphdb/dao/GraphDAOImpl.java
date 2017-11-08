package hu.bme.szoftarch.graphdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import hu.bme.szoftarch.graphdb.model.Graph;
import hu.bme.szoftarch.graphdb.model.Paging;
import hu.bme.szoftarch.graphdb.model.SearchContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Implementation for the graph DAO.
 *
 * @author kkrisz
 */
@Component
public class GraphDAOImpl implements GraphDAO {

    private JdbcTemplate jdbcTemplate;

    public GraphDAOImpl() {
    }

    @Autowired
    public GraphDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static Graph mapRowToGraph(ResultSet rs, int rowNum) throws SQLException {
        return extractGraphFormRow(rs);
    }

    private static Graph extractGraphFormRow(ResultSet rs) throws SQLException {
        Graph graph = new Graph();
        graph.setId(rs.getInt("id"));
        graph.setName(rs.getString("name"));
        graph.setDescriptor(rs.getString("descriptor"));
        return graph;
    }

    @Override
    public void saveOrUpdate(Graph graph) {
        String sql;
        if (graph.getId() > 0) {
            // update
            sql = "UPDATE graph SET name=?, descriptor=? WHERE id=?";
            jdbcTemplate.update(sql,
                    graph.getName(),
                    graph.getDescriptor(),
                    graph.getId());
        } else {
            // insert
            sql = "INSERT INTO graph (name, descriptor) VALUES (?, ?)";
            jdbcTemplate.update(sql,
                    graph.getName(),
                    graph.getDescriptor());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM graph WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Graph> list() {
        String sql = "SELECT * FROM graph";
        List<Graph> listContact = jdbcTemplate.query(sql, GraphDAOImpl::mapRowToGraph);

        return listContact;
    }

    @Override
    public Graph get(int id) {
        String sql = "SELECT * FROM graph WHERE id=" + id;
        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                return extractGraphFormRow(rs);
            }

            return null;
        });
    }

    @Override
    public List<Graph> find(SearchContext searchContext) {
        Graph graph = searchContext.getGraph();
        Paging paging = searchContext.getPaging();

        String sql = "SELECT * FROM graph";
        List<String> conditions = new ArrayList<>();
        if (graph.getName() != null) {
            conditions.add("name LIKE '" + graph.getName() + "%'");
        }
        if (graph.getDescriptor() != null) {
            conditions.add("descriptor LIKE '" + graph.getDescriptor() + "%'");
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

        return jdbcTemplate.query(sql, GraphDAOImpl::mapRowToGraph);
    }
}
