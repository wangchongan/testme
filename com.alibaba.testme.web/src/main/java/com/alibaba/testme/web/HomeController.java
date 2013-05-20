package com.alibaba.testme.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.testme.domain.dataobject.UserDO;
import com.alibaba.testme.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired @Qualifier("userService") private UserService userService;
	
	@RequestMapping(value="/userinfo/{id}", method=RequestMethod.GET)
	public ModelAndView showAllFiles(ModelMap modelMap,@PathVariable("id") long id) {
		
		logger.info("Test log.....");
	    UserDO userDO =	userService.findById(id);
	    modelMap.put("userName", userDO.getUserName());
	    
	    
	    /*UserDO userDO2 = new UserDO();
	    userDO2.setUserName("xiaopenzi");
	    userService.add(userDO2);*/
	    
		return new ModelAndView("user_info", modelMap);
	}
	
}
