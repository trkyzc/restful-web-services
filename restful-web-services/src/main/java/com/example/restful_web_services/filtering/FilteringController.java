package com.example.restful_web_services.filtering;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public SomeBean filtering() {
		
		return new SomeBean("value1","value2","value3");
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> filteringBeans() {
		
		List<SomeBean> someBeans = List.of(new SomeBean("value1","value2","value3"),new SomeBean("value4","value5","value6"));
		
		return someBeans;
	}

}
