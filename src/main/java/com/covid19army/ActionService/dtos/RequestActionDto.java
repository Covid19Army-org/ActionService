package com.covid19army.ActionService.dtos;

public class RequestActionDto {
	private long requestionactionid;
	
	private long requestid;
	
	private long volunteerid;
	
	private String action;


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public long getRequestionactionid() {
		return requestionactionid;
	}


	public void setRequestionactionid(long requestionactionid) {
		this.requestionactionid = requestionactionid;
	}


	public long getRequestid() {
		return requestid;
	}


	public void setRequestid(long requestid) {
		this.requestid = requestid;
	}

	public long getVolunteerid() {
		return volunteerid;
	}


	public void setVolunteerid(long volunteerid) {
		this.volunteerid = volunteerid;
	}
	
	
	
}
