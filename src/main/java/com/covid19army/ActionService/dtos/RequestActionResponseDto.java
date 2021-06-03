package com.covid19army.ActionService.dtos;

public class RequestActionResponseDto {

private long requestionactionid;
	
	private long requestid;

	
	private long userid;

	
	private long volunteerid;
	
	private String action;
	
	private String volunteername;


	public String getVolunteername() {
		return volunteername;
	}


	public void setVolunteername(String volunteername) {
		this.volunteername = volunteername;
	}


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


	public long getUserid() {
		return userid;
	}


	public void setUserid(long userid) {
		this.userid = userid;
	}


	public long getVolunteerid() {
		return volunteerid;
	}


	public void setVolunteerid(long volunteerid) {
		this.volunteerid = volunteerid;
	}
	
	
}
