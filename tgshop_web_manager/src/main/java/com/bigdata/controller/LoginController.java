package com.bigdata.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("login")
public class LoginController {
	@RequestMapping("showName")
	public Map showName(){
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Map map=new HashMap();
		map.put("username", username);
		return map ;
	}
}