package com.abcd.efg.components;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class RequestController {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestController.class);
	private RequestRepository requestRepository;
	
	@Autowired
	public RequestController (RequestRepository requestRepository)
	{
		this.requestRepository = requestRepository;
	}
	
	
	@RequestMapping(value = "/requests", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Request> requests(Locale locale, Model model) 
	{
		logger.info("---------------Locale logging for curiosity", locale);
		List<Request> result = requestRepository.findRequests("Request1");
		model.addAttribute(requestRepository.findRequests("Request1"));
		return result;
	}
}
