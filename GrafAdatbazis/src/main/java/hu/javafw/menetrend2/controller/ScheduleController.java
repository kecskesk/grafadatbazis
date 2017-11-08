package hu.javafw.menetrend2.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hu.javafw.menetrend2.dao.ScheduleDAO;
import hu.javafw.menetrend2.dao.StationDAO;
import hu.javafw.menetrend2.model.Schedule;
import hu.javafw.menetrend2.model.Station;

/**
 * Controller to the schedule manager and new/edit schedule pages.
 * 
 * @author kkrisz
 */
@Controller
public class ScheduleController {
    private final String DATE_PATTERN = "yyyy-MM-dd HH:mm";

	@Autowired
	private ScheduleDAO scheduleDAO;

	@Autowired
	private StationDAO stationDAO;
	
	@RequestMapping(value="/schedule")
	public ModelAndView listSchedule(ModelAndView model) throws IOException{
		List<Schedule> listSchedule = scheduleDAO.list();
		model.addObject("listSchedule", listSchedule);
		model.addObject("datePattern", DATE_PATTERN);
        List<Station> listStation = stationDAO.list();	
		model.addObject("listStation", listStation);
		Schedule newSchedule = new Schedule();
		model.addObject("newSchedule", newSchedule);
		model.setViewName("schedule");		
		return model;
	}
	
	@RequestMapping(value = "/schedule/newSchedule", method = RequestMethod.GET)
	public ModelAndView newSchedule(ModelAndView model) {
		Schedule newSchedule = new Schedule();
		model.addObject("schedule", newSchedule);
        List<Station> listStation = stationDAO.list();	
		model.addObject("listStation", listStation);
		model.setViewName("ScheduleForm");
		return model;
	}
	
	@RequestMapping(value = "/schedule/saveSchedule", method = RequestMethod.POST, 
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView saveScheduleAjax(@RequestBody Schedule schedule) {
		scheduleDAO.saveOrUpdate(schedule);
		return new ModelAndView("redirect:/schedule");
	}
	
	@RequestMapping(value = "/schedule/saveSchedule", method = RequestMethod.POST)
	public ModelAndView saveSchedule(@ModelAttribute Schedule schedule) {
		scheduleDAO.saveOrUpdate(schedule);
		return new ModelAndView("redirect:/schedule");
	}
	
	@RequestMapping(value = "/schedule/deleteSchedule", method = RequestMethod.GET)
	public ModelAndView deleteSchedule(HttpServletRequest request) {
		int scheduleId = Integer.parseInt(request.getParameter("id"));
		scheduleDAO.delete(scheduleId);
		return new ModelAndView("redirect:/schedule");
	}
	
	@RequestMapping(value = "/schedule/editSchedule", method = RequestMethod.GET)
	public ModelAndView editSchedule(HttpServletRequest request) {
		int scheduleId = Integer.parseInt(request.getParameter("id"));
		Schedule schedule = scheduleDAO.get(scheduleId);
		ModelAndView model = new ModelAndView("ScheduleForm");
		model.addObject("schedule", schedule);	
        List<Station> listStation = stationDAO.list();	
		model.addObject("listStation", listStation);
        
		return model;
	}
	
	@RequestMapping(value = "/schedule/getSchedule", method = RequestMethod.GET)
	@ResponseBody
    public Schedule getSchedule(HttpServletRequest request) {
		int scheduleId = Integer.parseInt(request.getParameter("id"));
		return scheduleDAO.get(scheduleId);
	}
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
