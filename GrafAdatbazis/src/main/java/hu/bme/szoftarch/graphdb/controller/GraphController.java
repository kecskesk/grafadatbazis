package hu.bme.szoftarch.graphdb.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import hu.bme.szoftarch.graphdb.dao.GraphDAO;
import hu.bme.szoftarch.graphdb.model.Graph;
import hu.bme.szoftarch.graphdb.parser.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller to the graph manager and new/edit graph pages.
 * 
 * @author kkrisz
 */
@Controller
public class GraphController {

	@Autowired
	private GraphDAO graphDAO;
	
	@RequestMapping(value="/graph")
	public ModelAndView listGraph(ModelAndView model) throws IOException{
		List<Graph> listGraph = graphDAO.list();
		model.addObject("listGraph", listGraph);
		Graph newGraph = new Graph();
		model.addObject("newGraph", newGraph);
		model.setViewName("graph");
		return model;
	}
	
	@RequestMapping(value = "/graph/newGraph", method = RequestMethod.GET)
	public ModelAndView newGraph(ModelAndView model) {
		Graph newGraph = new Graph();
		model.addObject("graph", newGraph);
		model.setViewName("GraphForm");
		return model;
	}

	@RequestMapping(value = "/graph/upload", method = RequestMethod.GET)
	public ModelAndView upload(ModelAndView model) {
		Graph newGraph = new Graph();
		model.addObject("graph", newGraph);
		model.setViewName("Upload");
		return model;
	}

	@RequestMapping(value = "/graph/saveFromFile", method = RequestMethod.POST)
	public String saveFromFile(@RequestParam("file") MultipartFile file) {
        try {
			final Set<Graph> graphs = CsvParser.parse(new InputStreamReader(file.getInputStream()));
			graphs.stream().forEach(graph -> graphDAO.saveOrUpdate(graph));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/graph";
	}

	@RequestMapping(value = "/graph/saveGraph", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView saveGraphAjax(@RequestBody Graph graph) {
		graphDAO.saveOrUpdate(graph);
		return new ModelAndView("redirect:/graph");
	}
	
	@RequestMapping(value = "/graph/saveGraph", method = RequestMethod.POST)
	public ModelAndView saveGraph(@ModelAttribute Graph graph) {
		graphDAO.saveOrUpdate(graph);
		return new ModelAndView("redirect:/graph");
	}
	
	@RequestMapping(value = "/graph/deleteGraph", method = RequestMethod.GET)
	public ModelAndView deleteGraph(HttpServletRequest request) {
		int graphId = Integer.parseInt(request.getParameter("id"));
		graphDAO.delete(graphId);
		return new ModelAndView("redirect:/graph");
	}
	
	@RequestMapping(value = "/graph/editGraph", method = RequestMethod.GET)
	public ModelAndView editGraph(HttpServletRequest request) {
		int graphId = Integer.parseInt(request.getParameter("id"));
		Graph graph = graphDAO.get(graphId);
		ModelAndView model = new ModelAndView("GraphForm");
		model.addObject("graph", graph);
        
		return model;
	}
	
	@RequestMapping(value = "/graph/getGraph", method = RequestMethod.GET)
	@ResponseBody
    public Graph getGraph(HttpServletRequest request) {
		int graphId = Integer.parseInt(request.getParameter("id"));
		return graphDAO.get(graphId);
	}
}
