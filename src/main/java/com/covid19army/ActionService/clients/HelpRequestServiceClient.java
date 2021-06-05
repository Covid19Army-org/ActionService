package com.covid19army.ActionService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="HelpRequestService", url = "${app.client.helprequestservice.url:}")
public interface HelpRequestServiceClient {

	@GetMapping("/helprequest/owner/{requestid}")
	public long getHelpRequestOwner(@RequestHeader("X-Auth-User") long userid, @PathVariable long requestid);
}
