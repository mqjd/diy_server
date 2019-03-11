package com.api.process;

import org.activiti.bpmn.model.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.activity.service.ActivityService;
import com.api.process.model.DiyProcess;
import com.base.framework.controller.BaseController;
import com.base.util.JsonUtil;

@RestController
@RequestMapping("ProcessManageController")
public class ProcessManageController extends BaseController{
	
	@Autowired
	private ActivityService activityService;

	@RequestMapping(value = "deploy",method=RequestMethod.POST)
	public void deploy(String param) {
		DiyProcess diyProcess = JsonUtil.fromJson(param, DiyProcess.class);
		Process process = diyProcess.buildProcess();
		process.setId("xxx1");
		activityService.deploy(process);
	}
	
}
