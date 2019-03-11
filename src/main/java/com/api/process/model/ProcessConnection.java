package com.api.process.model;

public class ProcessConnection {
	private String id;
	private String sourceId;
	private String targetId;
	private String sourceUuid;
	private String targetUuid;
	private String text;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getSourceUuid() {
		return sourceUuid;
	}
	public void setSourceUuid(String sourceUuid) {
		this.sourceUuid = sourceUuid;
	}
	public String getTargetUuid() {
		return targetUuid;
	}
	public void setTargetUuid(String targetUuid) {
		this.targetUuid = targetUuid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
