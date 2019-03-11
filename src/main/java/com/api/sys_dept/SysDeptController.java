package com.api.sys_dept;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.SysDept;
import com.base.framework.controller.BaseController;

@RestController
@RequestMapping("SysDeptController")
public class SysDeptController extends BaseController{

	@Autowired
	@Qualifier("SysDeptService")
	private SysDeptService sysDeptService;
	
	@RequestMapping("queryAll")
	public Map<String, List<SysDept>> queryAll() {
		List<SysDept> data = sysDeptService.queryAll(null);
		Map<String, List<SysDept>> result = new HashMap<String, List<SysDept>>();
		result.put("tableData", data);
		result.put("treeData", buildTree(data, -100));
		return result;
	}
	
	@RequestMapping("saveDept")
	public void saveDept(SysDept sysDept) {
		if (sysDept.getDeptId() == null) {
			sysDeptService.insert(sysDept);
		}else {
			sysDeptService.update(sysDept);
		}
	}
	
	@RequestMapping("removeDept")
	public void removeDept(SysDept sysDept) {
		sysDeptService.deleteById(sysDept);
	}
	
	private List<SysDept> buildTree(List<SysDept> data, int pid) {
		List<SysDept> result = new ArrayList<SysDept>();
		for (int i = 0; i < data.size(); i++) {
			if(data.get(i).getPDeptId() == pid) {
				SysDept sysDept = data.get(i);
				sysDept.setChildren(buildTree(data, sysDept.getDeptId()));
				result.add(sysDept);
			}
		}
		return result;
	}
}
