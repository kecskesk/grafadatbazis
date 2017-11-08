package hu.javafw.menetrend2.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hu.javafw.menetrend2.dao.ScheduleDAO;
import hu.javafw.menetrend2.dao.StationDAO;
import hu.javafw.menetrend2.model.Schedule;
import hu.javafw.menetrend2.model.SearchContext;
import hu.javafw.menetrend2.model.Station;

/**
 * Controller to the schedule search and the index pages.
 * 	
 * @author kkrisz
 */
@Controller
public class IndexController {
    private final String DATE_PATTERN = "yyyy-MM-dd HH:mm";
	
	@Autowired
	private ScheduleDAO scheduleDAO;

	@Autowired
	private StationDAO stationDAO;
	
	@RequestMapping(value={"/", "/index"})
	public ModelAndView index(ModelAndView model) throws IOException{
		model.setViewName("index");	
		Schedule schedule = new Schedule();
		model.addObject("schedule", schedule);	
        List<Station> listStation = stationDAO.list();	
		model.addObject("listStation", listStation);
		return model;
	}
	
	@RequestMapping(value="/searchSchedule", method=RequestMethod.POST, 
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Schedule> search(@RequestBody SearchContext searchContext) {
        return scheduleDAO.find(searchContext);
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
