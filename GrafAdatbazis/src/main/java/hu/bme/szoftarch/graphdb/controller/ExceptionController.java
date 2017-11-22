package hu.bme.szoftarch.graphdb.controller;

import hu.bme.szoftarch.graphdb.model.Graph;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public ModelAndView globalExceptionHandler(HttpServletRequest req, Exception ex) {
        ModelAndView model = new ModelAndView("redirect:/");
        ex.printStackTrace();
        model.addObject("error", true);
        model.addObject("errorClass", ex.getClass().getName());
        model.addObject("errorMessage", ex.getMessage());
        return model;
    }
}
