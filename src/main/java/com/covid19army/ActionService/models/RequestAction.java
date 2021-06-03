package com.covid19army.ActionService.models;

import java.io.Serializable;
import javax.persistence.*;

import com.covid19army.ActionService.modellisteners.RequestActionModelListener;

import java.util.Date;


/**
 * The persistent class for the requestactions database table.
 * 
 */
@EntityListeners(RequestActionModelListener.class)
@Entity
@Table(name="requestactions")
@NamedQuery(name="RequestAction.findAll", query="SELECT r FROM RequestAction r")
public class RequestAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long requestionactionid;

	private String action;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	private Date dateCreated;

	
	private long requestid;

	
	private long userid;

	
	private long volunteerid;
	
	

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

	public RequestAction() {
	}

	public long getRequestionactionid() {
		return this.requestionactionid;
	}

	public void setRequestionactionid(long requestionactionid) {
		this.requestionactionid = requestionactionid;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


}