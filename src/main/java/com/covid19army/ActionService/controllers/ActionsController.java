package com.covid19army.ActionService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid19army.ActionService.dtos.RequestActionDto;
import com.covid19army.ActionService.dtos.RequestActionResponseDto;
import com.covid19army.ActionService.services.RequestActionService;
import com.covid19army.core.dtos.PagedResponseDto;
import com.covid19army.core.exceptions.NotAuthorizedException;
import com.covid19army.core.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("action")
public class ActionsController {
	
	@Autowired
	RequestActionService _actionService;
	
	@PostMapping
	public long createAction(@RequestBody RequestActionDto actionDto) {
		return _actionService.createRequestAction(actionDto);				
	}
	
	@GetMapping("/{actionid}/request/{requestid}")
	public RequestActionResponseDto getAction(@PathVariable long actionid, @PathVariable long requestid) 
			throws ResourceNotFoundException, NotAuthorizedException {
		return _actionService.getRequestAction(actionid, requestid);
	}
	
	@GetMapping("/request/{requestid}")
	public PagedResponseDto<RequestActionResponseDto> getActionForRequest( @PathVariable long requestid,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size ) 
			throws ResourceNotFoundException, NotAuthorizedException {
		Pageable pageable = PageRequest.of(page, size,Sort.by(Sort.Direction.DESC, "dateCreated"));
		return _actionService.getRequestAction(requestid,pageable);
	}

}
