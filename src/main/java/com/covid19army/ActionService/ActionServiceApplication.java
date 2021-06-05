package com.covid19army.ActionService;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.covid19army.core.exceptions.GlobalExceptionHandler;
import com.covid19army.core.extensions.HttpServletRequestExtension;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Import({GlobalExceptionHandler.class})
public class ActionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActionServiceApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public HttpServletRequestExtension httpServletRequestExtenstion() {
		return new HttpServletRequestExtension();
	}

}
