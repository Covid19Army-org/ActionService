package com.covid19army.ActionService.modellisteners;

import java.util.Date;

import javax.persistence.PrePersist;

import com.covid19army.ActionService.models.RequestAction;

public class RequestActionModelListener {

	@PrePersist
	public void onCreating(RequestAction requestaction) {
		requestaction.setDateCreated(new Date());
	}
	
}
