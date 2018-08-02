package com.api.activity.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityService {
	
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private IdentityService identityService;
	
/*	@Autowired
	private ManagementService managementService;*/
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private FormService formService;

	/**
	 * 说明 : 处理任务
	 * @param taskId
	 * @return
	 */
	public void completeTask(String taskId) {
		taskService.complete(taskId);
	}
	
	
	
	/**
	 * 说明 : 处理任务
	 * @param taskId
	 * @param variables
	 * @return
	 */
	public void completeTask(String taskId, Map<String, Object> variables) {
		taskService.complete(taskId, variables);
	}
	
	/**
	 * 说明 : 委托任务
	 * @param taskId
	 * @param userId
	 * @return
	 */
	public void delegateTask(String taskId, String userId) {
		
		Task task = queryTaskById(taskId);
		task.setOwner(task.getAssignee());
		task.setAssignee(userId);
		saveTask(task);
	}
	
	/**
	 * 说明 : 认领任务
	 * @param taskId
	 * @param userId
	 * @return
	 */
	public void claim(String taskId, String userId) {
		taskService.claim(taskId, userId);
	}
	
	/**
	 * 说明 : 取消认领
	 * @param taskId
	 * @return
	 */
	public void unclaim(String taskId) {
		taskService.unclaim(taskId);
	}
	
	/**
	 * 说明 : 获取历史流程实例
	 * @param processInstanceId
	 * @return
	 */
	public HistoricProcessInstance queryHistoricProcessInstanceById(String processInstanceId) {
		HistoricProcessInstance historicProcessInstance = historyService
				.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		return historicProcessInstance;
	}
	
	/**
	 * 说明 : 获取历史流程实例
	 * @param businessKey
	 * @return
	 */
	public HistoricProcessInstance queryHistoricProcessInstanceByKey(String businessKey) {
		HistoricProcessInstance historicProcessInstance = historyService
				.createHistoricProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
		return historicProcessInstance;
	}
	
	/**
	 * 说明 : 获取历史流程实例
	 * @param taskId
	 * @return
	 */
	public HistoricProcessInstance queryHistoricProcessInstanceByTaskId(String taskId) {
		
		HistoricTaskInstance taskInstance = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		HistoricProcessInstance historicProcessInstance = queryHistoricProcessInstanceById(taskInstance.getProcessInstanceId());
		return historicProcessInstance;
	}
	
	/**
	 * 说明 : 获取历史环节信息
	 * @param businessKey
	 * @return
	 */
	public List<HistoricTaskInstance> queryHistoricTaskByBusinessKey(String businessKey) {
		List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
				.processInstanceBusinessKey(businessKey).orderByTaskCreateTime().asc().list();
		return list;
	}
	
	/**
	 * 说明 : 获取历史环节信息
	 * @param businessKey
	 * @param userId
	 * @return
	 */
	public List<HistoricTaskInstance> queryHistoricTaskByBusinessKey(String businessKey,String userId) {
		List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
				.processInstanceBusinessKey(businessKey).taskAssignee(userId).orderByTaskCreateTime().asc().list();
		return list;
	}
	
	/**
	 * 说明 : 获取历史环节信息
	 * @param processInstanceId
	 * @return
	 */
	public List<HistoricTaskInstance> queryHistoricTaskByInstanceId(String processInstanceId) {
		List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceId).orderByTaskCreateTime().asc().list();
		return list;
	}
	
	/**
	 * 说明 : 获取历史环节信息
	 * @param processInstanceId
	 * @param userId
	 * @return
	 */
	public List<HistoricTaskInstance> queryHistoricTaskByInstanceId(String processInstanceId,String userId) {
		List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceId).taskAssignee(userId).orderByTaskCreateTime().asc().list();
		return list;
	}
	
	/**
	 * 说明 : 获取运行环节
	 * @param businessKey
	 * @return
	 */
	public List<Task> queryRunTaskByBusinessKey(String businessKey) {
		List<Task> tasks = taskService.createTaskQuery()
				.processInstanceBusinessKey(businessKey).active().orderByTaskCreateTime().asc().list();
		return tasks;
	}
	
	/**
	 * 说明 : 获取运行环节
	 * @param businessKey
	 * @param userId
	 * @return
	 */
	public List<Task> queryRunTaskByBusinessKey(String businessKey,String userId) {
		List<Task> userTasks = taskService.createTaskQuery().processInstanceBusinessKey(businessKey)
				.taskAssignee(userId).orderByTaskCreateTime().active().asc().list();
		List<Task> candidateTasks = taskService.createTaskQuery().processInstanceBusinessKey(businessKey)
				.taskCandidateUser(userId).active().orderByTaskCreateTime().active().asc().list();
		userTasks.addAll(candidateTasks);
		return userTasks;
	}
	
	/**
	 * 说明 : 获取运行环节
	 * @param instanceId
	 * @return
	 */
	public List<Task> queryRunTaskByInstanceId(String instanceId) {
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(instanceId)
				.active().orderByTaskCreateTime().asc().list();
		return tasks;
	}
	
	/**
	 * 说明 : 获取运行环节
	 * @param instanceId
	 * @param userId
	 * @return
	 */
	public List<Task> queryRunTaskByInstanceId(String instanceId,String userId) {
		List<Task> userTasks = taskService.createTaskQuery().processInstanceId(instanceId)
				.taskAssignee(userId).orderByTaskCreateTime().active().asc().list();
		List<Task> candidateTasks = taskService.createTaskQuery().processInstanceId(instanceId)
				.taskCandidateUser(userId).active().orderByTaskCreateTime().active().asc().list();
		userTasks.addAll(candidateTasks);
		return userTasks;
	}
	
	/**
	 * 说明 : 获取运行环节
	 * @param userId
	 * @return
	 */
	public List<Task> queryRunTaskByUserId(String userId) {
		List<Task> userTasks = taskService.createTaskQuery()
				.taskAssignee(userId).orderByTaskCreateTime().active().asc().list();
		List<Task> candidateTasks = taskService.createTaskQuery()
				.taskCandidateUser(userId).active().orderByTaskCreateTime().active().asc().list();
		userTasks.addAll(candidateTasks);
		return userTasks;
	}
	
	public void saveTask(Task task){
		taskService.saveTask(task);
	}
	
	/**
	 * 说明 : 获取运行环节
	 * @param taskId
	 * @return
	 */
	public Task queryTaskById(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).active().singleResult();
		return task;
	}
	
	/**
	 * 说明 : 获取发起环节表单
	 * @param processDefinitionId
	 * @return
	 */
	public String queryStartFormKey(String processDefinitionId) {
		String startFormKey = formService.getStartFormKey(processDefinitionId);
		return startFormKey;
	}
	
	/**
	 * 说明 : 查询表单key
	 * @param taskId
	 * @return
	 */
	public String queryFormKey(String taskId) {
		Task task = queryTaskById(taskId);
		String formKey = task.getFormKey();
		return formKey;
	}
	
	/**
	 * 说明 : 查询流程变量
	 * @param executionId
	 * @return
	 */
	public Map<String, Object> queryVariables(String executionId) {
		return runtimeService.getVariables(executionId);
	}
	
	/**
	 * 说明 : 设置流程变量
	 * @param executionId
	 * @param variables
	 * returnType：void
	*/
	public void setVariables(String executionId,Map<String, Object> variables) {
		runtimeService.setVariables(executionId, variables);
	}
	
	/**
	 * 说明 : 设置流程变量
	 * @param executionId
	 * @param variableName
	 * @param value
	 * returnType：void
	*/
	public void setVariable(String executionId, String variableName, Object value) {
		runtimeService.setVariable(executionId, variableName, value);
	}
	
	/**
	 * 说明 : 设置环节变量
	 * @param taskId
	 * @param variableName
	 * @param value
	 * returnType：void
	*/
	public void setTaskVariable(String taskId, String variableName, Object value) {
		taskService.setVariable(taskId, variableName, value);

	}

	/**
	 * 说明 : 设置流程变量
	 * @param executionId
	 * @param variables
	 * returnType：void
	*/
	public void setVariablesLocal(String executionId,Map<String, Object> variables) {
		runtimeService.setVariablesLocal(executionId, variables);
	}
	
	/**
	 * 说明 : 设置流程变量
	 * @param executionId
	 * @param variableName
	 * @param value
	 * returnType：void
	*/
	public void setVariableLocal(String executionId, String variableName, Object value) {
		runtimeService.setVariableLocal(executionId, variableName, value);
	}
	
	/**
	 * 说明 : 发起流程
	 * @param processDefinitionId
	 * @param businessKey
	 * returnType：void
	 * @return 
	*/
	public ProcessInstance startProcessInstanceById(String processDefinitionId,String businessKey) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, businessKey);
		return processInstance;
	}
	
	/**
	 * 说明 : 发起流程
	 * @param processDefinitionId
	 * @param variables
	 * returnType：void
	 * @return 
	*/
	public ProcessInstance startProcessInstanceById(String processDefinitionId,String businessKey, Map<String, Object> variables) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, businessKey, variables);
		return processInstance;
	}
	
	/**
	 * 说明 : 发起流程
	 * @param processDefinitionId
	 * @param businessKey
	 * returnType：void
	 * @return 
	*/
	public ProcessInstance startProcessInstanceById(String processDefinitionId,String businessKey, String userId) {
		identityService.setAuthenticatedUserId(userId);
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, businessKey);
		return processInstance;
	}
	
	/**
	 * 说明 : 发起流程
	 * @param processDefinitionId
	 * @param variables
	 * returnType：void
	 * @return 
	*/
	public ProcessInstance startProcessInstanceById(String processDefinitionId,String businessKey, String userId, Map<String, Object> variables) {
		identityService.setAuthenticatedUserId(userId);
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, businessKey, variables);
		return processInstance;
	}
	
	/**
	 * 说明：根据流程定义ID获取流程定义
	 * @param processDefinitionId
	 * @return
	 * returnType：ProcessDefinition
	*/
	public ProcessDefinition name(String processDefinitionId) {
		ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processDefinitionId);
		return processDefinition;
	}

}
