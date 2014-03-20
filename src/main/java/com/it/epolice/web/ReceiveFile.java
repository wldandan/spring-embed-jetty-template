package com.it.epolice.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
@RequestMapping("/it/file/1.jpg")
public class ReceiveFile {
 
	@RequestMapping(method = RequestMethod.PUT)
	public String receive(ModelMap model) {
 
		model.addAttribute("message", "Spring 3 MVC Hello World");

		return "hello";
	}
 
}