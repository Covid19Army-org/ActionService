package com.covid19army.ActionService.services;




import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.covid19army.ActionService.clients.HelpRequestServiceClient;
import com.covid19army.ActionService.clients.VolunteerServiceClient;
import com.covid19army.ActionService.dtos.RequestActionDto;
import com.covid19army.ActionService.dtos.RequestActionResponseDto;
import com.covid19army.ActionService.dtos.VolunteerResponseDto;
import com.covid19army.ActionService.models.RequestAction;
import com.covid19army.ActionService.repositories.RequestActionRepository;
import com.covid19army.core.dtos.PagedResponseDto;
import com.covid19army.core.exceptions.NotAuthorizedException;
import com.covid19army.core.exceptions.ResourceNotFoundException;
import com.covid19army.core.extensions.HttpServletRequestExtension;

@Service
public class RequestActionService {

	@Autowired
	RequestActionRepository _requestActionRepository;
	
	@Autowired
	ModelMapper _modelMapper;
	
	@Autowired
	HttpServletRequestExtension _requestExtension;
	
	@Autowired
	VolunteerServiceClient _VolunteerServiceClient;
	
	@Autowired
	HelpRequestServiceClient _helpRequestServiceClient;
	
	public long createRequestAction(RequestActionDto requestActionDto) {
		var newModel = _modelMapper.map(requestActionDto, RequestAction.class);
		newModel.setUserid(Long.parseLong(_requestExtension.getAuthenticatedUser()));
		_requestActionRepository.save(newModel);
		return newModel.getRequestionactionid();
	}
	
	public RequestActionResponseDto getRequestAction(long requestActionId, long requestId) 
			throws ResourceNotFoundException, NotAuthorizedException {
		
		
		var requestaction = _requestActionRepository.findById(requestActionId);
		if(requestaction.isPresent()) {
			var requestactionmodel = requestaction.get();
			var authuserid = Long.parseLong(_requestExtension.getAuthenticatedUser());
			var owner = _helpRequestServiceClient.getHelpRequestOwner(authuserid, requestId);
			if(owner != authuserid && !_requestActionRepository.existsByRequestidAndUserid(requestId, authuserid))
				throw new NotAuthorizedException();
			var responseDto = _modelMapper.map(requestactionmodel, RequestActionResponseDto.class);
			List<Long> volunteerId = new ArrayList<>();
			volunteerId.add(requestactionmodel.getVolunteerid());
			
			var volunteerList = _VolunteerServiceClient.searchByVolunteerId(volunteerId);
			if(volunteerList.size() > 0) {
				var volunteer = volunteerList.get(0);
				responseDto.setVolunteername(volunteer.getName());
			}
			
			return responseDto;
		}
		throw new ResourceNotFoundException("Action not found");
		
	}
	
	public PagedResponseDto<RequestActionResponseDto> getRequestAction(long requestId, Pageable pageable) 
			throws NotAuthorizedException {
		
		//TODO: validate if requestid belongs to auth user
		var authuserid = Long.parseLong(_requestExtension.getAuthenticatedUser());
		var owner = _helpRequestServiceClient.getHelpRequestOwner(authuserid, requestId);
		
		if(owner != authuserid && !_requestActionRepository.existsByRequestidAndUserid(requestId, authuserid))
			throw new NotAuthorizedException();
		
		var page = _requestActionRepository.findByRequestid(requestId, pageable);
		
		
		var volunteerIds = page.getContent().stream().map(RequestAction::getVolunteerid)
				.distinct().collect(Collectors.toList());
		
		var volunteerList = _VolunteerServiceClient.searchByVolunteerId(volunteerIds);		
		Function<Long, VolunteerResponseDto>  filterVolunteerById = (volunteerid) -> volunteerList.stream()
				.filter(x->x.getVolunteerid() == volunteerid).findFirst().get();
						
		var dto = page.getContent().stream()				
				.map(a->_modelMapper.map(a, RequestActionResponseDto.class))
				.map(d -> {
					var v = filterVolunteerById.apply(d.getVolunteerid());
					d.setVolunteername(v.getName());
						return d;
				})
				.collect(Collectors.toList());
		
		PagedResponseDto<RequestActionResponseDto> responseData = new PagedResponseDto<>();
		responseData.setCurrentPage(page.getNumber());
		responseData.setTotalItems(page.getTotalElements());
		responseData.setTotalPages(page.getTotalPages());
		responseData.setData(dto);
		
		return responseData;
		
		
	}
	
	private void validateRequestActionAccess(RequestAction action) {
		
		//if(authuserid != action.getUserid())
		//return true;
	}
	
}
