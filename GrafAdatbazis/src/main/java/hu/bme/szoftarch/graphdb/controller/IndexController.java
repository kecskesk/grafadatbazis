package hu.bme.szoftarch.graphdb.controller;

import java.io.IOException;
import java.util.List;

import hu.bme.szoftarch.graphdb.model.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hu.bme.szoftarch.graphdb.model.SearchContext;

/**
 * Controller to the graph search and the index pages.
 * 	
 * @author kkrisz
 */
@Controller
public class IndexController {
	
	@Autowired
	private hu.bme.szoftarch.graphdb.dao.GraphDAO GraphDAO;
	
	@RequestMapping(value={"/", "/index", "/GrafAdatbazis"})
	public ModelAndView index(ModelAndView model) throws IOException{
		model.setViewName("index");	
		Graph graph = new Graph();
		model.addObject("graph", graph);
		return model;
	}
	
	@RequestMapping(value="/searchGraph", method=RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Graph> search(@RequestBody SearchContext searchContext) {
        return GraphDAO.find(searchContext);
    }
}
