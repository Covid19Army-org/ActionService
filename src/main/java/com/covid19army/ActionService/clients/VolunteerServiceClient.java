package com.covid19army.ActionService.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.covid19army.ActionService.dtos.VolunteerResponseDto;



@FeignClient(name="VolunteerService", url = "${app.client.tokenservice.url:}")
public interface VolunteerServiceClient {

	@PostMapping("/volunteer/searchbyids")
	public List<VolunteerResponseDto> searchByVolunteerId(@RequestBody List<Long> volunteerIds );
}