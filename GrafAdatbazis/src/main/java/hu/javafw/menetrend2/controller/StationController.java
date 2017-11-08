package hu.javafw.menetrend2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import hu.javafw.menetrend2.dao.StationDAO;
import hu.javafw.menetrend2.model.Station;

/**
 * Controller to the station manager and new/edit station pages.
 * 
 * @author kkrisz
 */
@Controller
public class StationController {

	@Autowired
	private StationDAO stationDAO;
	
	@RequestMapping(value="/station")
	public ModelAndView listStation(ModelAndView model) throws IOException{
		List<Station> listStation = stationDAO.list();
		model.addObject("listStation", listStation);
		model.setViewName("station");
		
		return model;
	}
	
	@RequestMapping(value = "/station/newStation", method = RequestMethod.GET)
	public ModelAndView newStation(ModelAndView model) {
		Station newStation = new Station();
		model.addObject("station", newStation);
		model.setViewName("StationForm");
		return model;
	}
	
	@RequestMapping(value = "/station/saveStation", method = RequestMethod.POST)
	public ModelAndView saveStation(@ModelAttribute Station station) {
		stationDAO.saveOrUpdate(station);		
		return new ModelAndView("redirect:/station");
	}
	
	@RequestMapping(value = "/station/deleteStation", method = RequestMethod.GET)
	public ModelAndView deleteStation(HttpServletRequest request) {
		int stationId = Integer.parseInt(request.getParameter("id"));
		stationDAO.delete(stationId);
		return new ModelAndView("redirect:/station");
	}
	
	@RequestMapping(value = "/station/editStation", method = RequestMethod.GET)
	public ModelAndView editStation(HttpServletRequest request) {
		int stationId = Integer.parseInt(request.getParameter("id"));
		Station station = stationDAO.get(stationId);
		ModelAndView model = new ModelAndView("StationForm");
		model.addObject("station", station);
		
		return model;
	}
}
