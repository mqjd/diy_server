package com.api.diy_form.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.diy_form.model.FormModel;
import com.api.diy_form.service.DiyFormService;
import com.api.model.DiyForm;
import com.base.framework.controller.BaseController;
import com.base.util.JsonUtil;


@RestController
@RequestMapping("DiyFormController")
public class DiyFormController extends BaseController{

	@Autowired
	private DiyFormService diyFormService;
	
	@RequestMapping(value = "getForm",method=RequestMethod.GET)
	public List<DiyForm> getForm(DiyForm diyForm) {
		return diyFormService.getForm(diyForm);
	}
	
	@RequestMapping(value = "saveForm",method=RequestMethod.POST)
	public void saveForm(String formData) {
		FormModel formModel = JsonUtil.fromJson(formData, FormModel.class);
		formModel.setFormContent(formData);
		diyFormService.saveForm(formModel);
	}
	
	@RequestMapping(value = "getFormConfig",method=RequestMethod.GET)
	public FormModel getFormConfig(DiyForm diyForm) {
		 FormModel formModel = diyFormService.getFormConfig(diyForm);
		 return formModel;
	}
	
	@RequestMapping(value = "deleteForm",method=RequestMethod.GET)
	public void deleteForm(Integer formId) {
		 diyFormService.deleteForm(formId);
	}
	
}
