package com.covid19army.ActionService.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.covid19army.ActionService.models.RequestAction;

public interface RequestActionRepository  extends PagingAndSortingRepository<RequestAction, Long>{

	public Page<RequestAction> findByRequestid(long requestid, Pageable pageable);
	
	public boolean existsByRequestidAndUserid(long requestid, long userid);
}
