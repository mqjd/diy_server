package com.api.process.model;

import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.ExclusiveGateway;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.ParallelGateway;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;

public class DiyProcess {

	private ProcessModel[] models;
	private ProcessConnection[] connections;
	private ProcessInfo processInfo;
	
	public ProcessModel[] getModels() {
		return models;
	}
	public void setModels(ProcessModel[] models) {
		this.models = models;
	}
	public ProcessConnection[] getConnections() {
		return connections;
	}
	public void setConnections(ProcessConnection[] connections) {
		this.connections = connections;
	}
	public ProcessInfo getProcessInfo() {
		return processInfo;
	}
	public void setProcessInfo(ProcessInfo processInfo) {
		this.processInfo = processInfo;
	}
	
	public Process buildProcess() {
		Process process = new Process();
		process.setName(processInfo.getText());
		if(models != null) {
			for (int i = 0; i < models.length; i++) {
				process.addFlowElement(buildModel(models[i]));
			}
			for (int i = 0; i < connections.length; i++) {
				SequenceFlow sequenceFlow = new SequenceFlow();
				sequenceFlow.setSourceRef(connections[i].getSourceId());
				sequenceFlow.setTargetRef(connections[i].getTargetId());
				sequenceFlow.setName(connections[i].getText());
				process.addFlowElement(sequenceFlow);
			}
		}
		return process;
	}
	
	private FlowElement buildModel(ProcessModel model) {
		FlowElement flowElement = null;
		switch (model.getType()) {
			case "start-event":
				StartEvent startEvent = new StartEvent();
				startEvent.setId(model.getId());
				startEvent.setName(model.getText());
				flowElement = startEvent;
				break;
			case "end-event":
				EndEvent endEvent = new EndEvent();
				endEvent.setId(model.getId());
				endEvent.setName(model.getText());
				flowElement = endEvent;
				break;
			case "exclusive":
				ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
				exclusiveGateway.setId(model.getId());
				exclusiveGateway.setName(model.getText());
				flowElement = exclusiveGateway;
				break;
			case "parallel":
				ParallelGateway parallelGateway = new ParallelGateway();
				parallelGateway.setId(model.getId());
				parallelGateway.setName(model.getText());
				flowElement = parallelGateway;
				break;
			case "user-task":
				UserTask userTask = new UserTask();
				userTask.setId(model.getId());
				userTask.setName(model.getText());
				userTask.setAssignee(model.getAssign());
				userTask.setFormKey(model.getFormKey());
				flowElement = userTask;
				break;
			default:
				break;
		}
		return flowElement;
	}
}
