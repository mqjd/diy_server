package com.api.comb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.comb.service.CombService;
import com.api.model.CombParam;

@RestController
@RequestMapping("CombController")
public class CombController {

	@Autowired
	private CombService combService;
	
	@RequestMapping("queryComb")
	public List<Map<String, Object>> queryComb(CombParam param) {
		return combService.queryComb(param);

	}
	
}
